package com.example.tss;

import util.WebServiceUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.example.tss.R;
import com.example.tss.STopicDetailActivity.SelectTopicAsyncTask;

import entity.SelectedTopicViewOfStudent;
import entity.Student;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("unchecked")
public class SMainActivity extends Activity {
	
	public static SelectedTopicViewOfStudent stvs;//包含小组信息等
	public static int gid;
	public static Student student;//注意在LoginActivity被finish后，其中的成员变量都会被销毁
	
    private ListView listview=null;
	private List<Map<String, Object>> exercises= null;
	private SimpleAdapter adapter=null;
	private ProgressDialog pd;
	EditText etsid;
	EditText etsname;
	EditText etsclass;
	EditText etsphone;
	EditText etsqq;
	RadioButton rbisleaderY;
	RadioButton rbisleaderN;
	TextView tvtopic;
	TextView tvleadername;
	TextView tvleaderphone;
	TextView tvleaderqq;
	
	 class LoadGroupIdAsyncTask extends AsyncTask{
		 @Override
		protected Object doInBackground(Object... arg0) {
			gid = WebServiceUtil.getGroupId(LoginActivity.uid);
			//System.out.println("LoginActivity.uid:"+LoginActivity.uid+"------"+gid);
			return null;
		}
	 }
	 class LoadTopicsAsyncTask extends AsyncTask<Object, Object, Object>{

		protected Object doInBackground(Object... params)  
	    {  
			runOnUiThread(new Runnable() {
				public void run() {
					pd=ProgressDialog.show(SMainActivity.this, "","正在加载。。。。。", true,false);
				   }
				});
			//List<Map<String, Object>> exercises = new ArrayList<Map<String, Object>>();
			 exercises = WebServiceUtil.getAllExercises();
			/* for(int i = 0; i <exercises.size(); i++)  
		        {  
				 Map<String, Object> map = new HashMap<String,Object>();
		            map = exercises.get(i);  
		            System.out.println(map);  
		        }  */
			 if(exercises!=null){
				 listview.post(new Runnable() {
					
					@Override
					public void run() {
						showTopicInfo();
						pd.dismiss();
						Toast.makeText(SMainActivity.this, "加载信息成功!",
							     Toast.LENGTH_SHORT).show();
					}
				});
			 }
			 else{
				 runOnUiThread(new Runnable() {
						public void run() {
							pd.dismiss();
							Toast.makeText(SMainActivity.this, "加载信息失败!",
								     Toast.LENGTH_LONG).show();
						   }
						}); 
			 }
			return null;
			
	    }
	 }
	 
	
	 
	 class LoadSelectInfoAsyncTask extends AsyncTask{
		 @Override
		protected Object doInBackground(Object... arg0) {
			 stvs = WebServiceUtil.getSelectedTopicInfo(LoginActivity.student.getSid());
			 if(stvs!=null){
				 runOnUiThread(new Runnable() {
						public void run() {
							tvtopic.setText(stvs.getEtitle());
							tvleadername.setText(stvs.getLeadername());
							tvleaderphone.setText(stvs.getLeaderphone());
							tvleaderqq.setText(stvs.getLeaderqq());
						   }
						});    	
			 }
			 else{
				 runOnUiThread(new Runnable() {
						public void run() {
							tvtopic.setText("");
							tvleadername.setText("");
							tvleaderphone.setText("");
							tvleaderqq.setText("");
						   }
						});    	
			 }
			return null;
		}
	 }
	 
	 class LoadPersonInfoAsyncTask extends AsyncTask{

		@Override
		protected Object doInBackground(Object... params) {
			LoginActivity.student = WebServiceUtil.getStudentInfoBySid(LoginActivity.uid);
			return null;
		}
		 
	 }
	 
	 class UpdatePersonInfoAsyncTask extends AsyncTask{
		 @Override
		protected Object doInBackground(Object... params) {
			LoginActivity.student.setSname(etsname.getText().toString());
			LoginActivity.student.setSclass(etsclass.getText().toString());
			LoginActivity.student.setSphone(etsphone.getText().toString());
			LoginActivity.student.setSqq(etsqq.getText().toString());
			if(WebServiceUtil.updateStudentInfo(LoginActivity.student)){
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(SMainActivity.this, "更改成功!",
							     Toast.LENGTH_LONG).show();
					   }
					}); 
				new LoadPersonInfoAsyncTask().execute();//同步student数据
			}
			else{
				runOnUiThread(new Runnable() {
					public void run() {
							Toast.makeText(SMainActivity.this, "更改失败!",Toast.LENGTH_LONG).show();
								 etsid.setText(LoginActivity.student.getSid());
								 etsname.setText(LoginActivity.student.getSname());
								 etsclass.setText(LoginActivity.student.getSclass());
								 etsphone.setText(LoginActivity.student.getSphone());
								 etsqq.setText(LoginActivity.student.getSqq());
								 if(LoginActivity.student.isSisleader()){
									 rbisleaderY.setChecked(true);
								 }
								 else{
									 rbisleaderN.setChecked(true);
								 }
					   }
					});  
			}
			return null;
		}
	 }
	 //退选
	 class UndoSelectAsyncTask extends AsyncTask{
			@Override
			protected Object doInBackground(Object... arg0) {
				if(WebServiceUtil.undoSelect(SMainActivity.gid, stvs.getEid())){
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(SMainActivity.this, "退选成功!",
								     Toast.LENGTH_LONG).show();
						   }
						});  
					new LoadSelectInfoAsyncTask().execute();//同步stvs
				}
				else{
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(SMainActivity.this, "退选失败!",
								     Toast.LENGTH_LONG).show();
						   }
						});   
				}
				return null;
			}
		}
	 
	 public void refreshTopicsClick(View view){  
	    new LoadTopicsAsyncTask().execute();
	 }  
	 public void updatePersonInfoClick(View view){
		 new AlertDialog.Builder(this).setTitle("确认更改吗？") 
		    .setPositiveButton("确定", new DialogInterface.OnClickListener() { 
		 
		        @Override 
		        public void onClick(DialogInterface dialog, int which) { 
		        // 点击“确认”后的操作 
		        	new UpdatePersonInfoAsyncTask().execute();
		 
		        } 
		    }) 
		    .setNegativeButton("返回", new DialogInterface.OnClickListener() { 
		 
		        @Override 
		        public void onClick(DialogInterface dialog, int which) { 
		        // 点击“返回”后的操作;
		        	runOnUiThread(new Runnable(){
						@Override
						public void run() {
							 etsid.setText(LoginActivity.student.getSid());
							 etsname.setText(LoginActivity.student.getSname());
							 etsclass.setText(LoginActivity.student.getSclass());
							 etsphone.setText(LoginActivity.student.getSphone());
							 etsqq.setText(LoginActivity.student.getSqq());
							 if(LoginActivity.student.isSisleader()){
								 rbisleaderY.setChecked(true);
							 }
							 else{
								 rbisleaderN.setChecked(true);
							 }
						}
						
					});
		        } 
		    }).show(); 
		
	 }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_s_main);
			 
		     tvtopic =(TextView)findViewById(R.id.s_textview_grouptopic);
		     tvleadername = (TextView)findViewById(R.id.s_textview_leadername);
		     tvleaderphone = (TextView)findViewById(R.id.s_textview_leaderphone);
		     tvleaderqq = (TextView)findViewById(R.id.s_textview_leaderqq);
		     
			 etsid = (EditText)findViewById(R.id.s_edittext_sid);
			 etsname = (EditText)findViewById(R.id.s_edittext_sname);
			 etsclass = (EditText)findViewById(R.id.s_edittext_sclass);
			 etsphone = (EditText)findViewById(R.id.s_edittext_sphone);
			 etsqq = (EditText)findViewById(R.id.s_edittext_sqq);
			 rbisleaderY = (RadioButton)findViewById(R.id.s_isleader_radio0);
			 rbisleaderN = (RadioButton)findViewById(R.id.s_isleader_radio1);
			
		/*设置tabhost的tab*/
		   TabHost tabhost = (TabHost) findViewById(R.id.s_tabHost);
		   tabhost.setup(); // tab的内容是杂物的话 tabhost就是一个盒子 先要一个盒子
		   TabHost.TabSpec tab_1 = tabhost.newTabSpec("tab_1");// 里面的string 就是一个tag
		   // 这是第一个物品
		   tab_1.setContent(R.id.s_tab1); // 物品 1 里面的东西怎么放 就看对应的 布局怎么安排
		   tab_1.setIndicator("课题查看"); // 第一个物品上面贴的标签 也就是tab的标题
		   tabhost.addTab(tab_1);// 然后将物品 1 放进去
		  
		   TabHost.TabSpec tab_2 = tabhost.newTabSpec("tab_2");
		   tab_2.setContent(R.id.s_tab2);// 物品 2 的布局设置
		   tab_2.setIndicator("已选课题");// 物品 2 的标题
		   tabhost.addTab(tab_2);// 然后放物品 2
		   
		   TabHost.TabSpec tab_3 = tabhost.newTabSpec("tab_3");
		   tab_3.setContent(R.id.s_tab3);// 物品 2 的布局设置
		   tab_3.setIndicator("个人中心");// 物品 2 的标题
		   tabhost.addTab(tab_3);// 然后放物品 2
		   
		   Button btupdate = (Button)findViewById(R.id.s_button_update);
		   
		   tabhost.setCurrentTab(0); // 将这个盒子设置成当前查看的盒子
		   tabhost.setOnTabChangedListener(new OnTabChangeListener(){
			   @Override
			public void onTabChanged(String tabTag) {
				// TODO Auto-generated method stub
				switch(tabTag){
				case "tab_1":
					new LoadTopicsAsyncTask().execute();
					break;
				case "tab_2":
					showSelectInfo();
					break;
				case "tab_3":
					showPersonInfo();
				}
			}
		   });
		
		listview =(ListView)findViewById(R.id.s_topic_listView);
		/*为ListView设置监听器，获得Item点击事件，启动课题详细信息窗口S_TopicInfoActivity,并将数据传给他*/
		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
				Map<?, ?> myMap=(Map<?, ?>)adapter.getItem(position);//获取被点击Item的map
				Bundle bundle=new Bundle();//构造数据传输类bundle
				Set<?> entries = myMap.entrySet( ); 
				if(entries != null) {
						Iterator<?> iterator = entries.iterator( );
						while(iterator.hasNext()) {
							Map.Entry entry =(Map.Entry)iterator.next( );
							bundle.putString(entry.getKey( ).toString(), entry.getValue().toString());//给bundle赋值
							//System.out.println(entry.getKey( )+":"+entry.getValue());
						}
			 		}
				Intent intent=new Intent();
				intent = intent.setClass(SMainActivity.this, STopicDetailActivity.class);
				intent.putExtras(bundle);  
				startActivity(intent);
				}
			});
	
		//三者顺序不要换
		new LoadTopicsAsyncTask().execute(); //初始化SMainActivity的时候先加载一次课题信息
		new LoadGroupIdAsyncTask().execute();//得到小组ID
		new LoadSelectInfoAsyncTask().execute();//初始化stvs
		
		if(LoginActivity.student.isSisleader()){
			LinearLayout linear_reset=(LinearLayout)findViewById(R.id.s_linearlayout_reset);
			Button button_reset=new Button(this);
			button_reset.setText("退选");
			linear_reset.addView(button_reset);	
			
			button_reset.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					if(stvs!=null){
						new UndoSelectAsyncTask().execute();
						new LoadSelectInfoAsyncTask().execute();
					}
					else{
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(SMainActivity.this, "您还未选题，请先选题！",
									     Toast.LENGTH_LONG).show();
							   }
							});   
					}
				}
				
			});
		}
		
		 
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch(id){
		case R.id.action_exit:
			new AlertDialog.Builder(this).setTitle("确认退出吗？") 
		    .setPositiveButton("确定", new DialogInterface.OnClickListener() { 
		 
		        @Override 
		        public void onClick(DialogInterface dialog, int which) { 
		        // 点击“确认”后的操作 
		        SMainActivity.this.finish(); 
		 
		        } 
		    }) 
		    .setNegativeButton("返回", new DialogInterface.OnClickListener() { 
		 
		        @Override 
		        public void onClick(DialogInterface dialog, int which) { 
		        // 点击“返回”后的操作,这里不设置没有任何操作 
		        } 
		    }).show(); 
			break;
		case R.id.action_addmember:
			return true;
		case R.id.action_modifypassword:
			Intent intent=new Intent();
            intent = intent.setClass(SMainActivity.this,ModifypwActivity.class); 
            startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	 /*给tab1加载课题信息，使用适配器SimpleAdapter进行数据和ListView组件之间的映射*/	
	   /*
	    * public SimpleAdapter (Context context, List<? extends Map<String, ?>> data,
	    *  int resource, String[] from, int[] to)
	    *   context   关联SimpleAdapter运行着的视图的上下文。
	    *   data    一个Map的列表。在列表中的每个条目对应列表中的一行，应该包含所有在from中指定的条目
	    *   resource    一个定义列表项目的视图布局的资源唯一标识。布局文件将至少应包含哪些在to中定义了的名称。
	    *   from       一个将被添加到Map上关联每一个项目的列名称的列表
	    *   to    应该在参数from显示列的视图。这些应该全是TextView。在列表中最初的N视图是从参数from中最初的N列获取的值。
	    */
	
	private void showSelectInfo(){
		 if(stvs!=null){
				tvtopic.setText(stvs.getEtitle());
				tvleadername.setText(stvs.getLeadername());
				tvleaderphone.setText(stvs.getLeaderphone());
				tvleaderqq.setText(stvs.getLeaderqq());
				 	
		 }
		 else{
				tvtopic.setText("");
				tvleadername.setText("");
				tvleaderphone.setText("");
				tvleaderqq.setText(""); 	
		 }
	}
	
	private void showPersonInfo(){
				if(LoginActivity.student!=null){
					etsid.setText(LoginActivity.student.getSid());
					etsname.setText(LoginActivity.student.getSname());
					etsclass.setText(LoginActivity.student.getSclass());
					etsphone.setText(LoginActivity.student.getSphone());
					etsqq.setText(LoginActivity.student.getSqq());
					if(LoginActivity.student.isSisleader()){
						rbisleaderY.setChecked(true);
					}
					else{
						rbisleaderN.setChecked(true);
					}
				}
				else{
					
					Toast.makeText(SMainActivity.this, "加载信息失败!",
								  Toast.LENGTH_LONG).show();
				}  	
	}
	
	private void showTopicInfo(){
		 adapter = new SimpleAdapter(this,exercises,R.layout.listitem_style,
	                new String[]{"E_title","E_level","E_leftcount"},
	                new int[]{R.id.s_exerciseitem_E_title,R.id.s_exerciseitem_E_level,R.id.s_exerciseitem_E_leftcount});
		 listview.setAdapter(adapter);
		}
}
