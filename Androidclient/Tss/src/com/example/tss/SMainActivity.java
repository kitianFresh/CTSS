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
	
	public static SelectedTopicViewOfStudent stvs;//����С����Ϣ��
	public static int gid;
	public static Student student;//ע����LoginActivity��finish�����еĳ�Ա�������ᱻ����
	
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
					pd=ProgressDialog.show(SMainActivity.this, "","���ڼ��ء���������", true,false);
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
						Toast.makeText(SMainActivity.this, "������Ϣ�ɹ�!",
							     Toast.LENGTH_SHORT).show();
					}
				});
			 }
			 else{
				 runOnUiThread(new Runnable() {
						public void run() {
							pd.dismiss();
							Toast.makeText(SMainActivity.this, "������Ϣʧ��!",
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
						Toast.makeText(SMainActivity.this, "���ĳɹ�!",
							     Toast.LENGTH_LONG).show();
					   }
					}); 
				new LoadPersonInfoAsyncTask().execute();//ͬ��student����
			}
			else{
				runOnUiThread(new Runnable() {
					public void run() {
							Toast.makeText(SMainActivity.this, "����ʧ��!",Toast.LENGTH_LONG).show();
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
	 //��ѡ
	 class UndoSelectAsyncTask extends AsyncTask{
			@Override
			protected Object doInBackground(Object... arg0) {
				if(WebServiceUtil.undoSelect(SMainActivity.gid, stvs.getEid())){
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(SMainActivity.this, "��ѡ�ɹ�!",
								     Toast.LENGTH_LONG).show();
						   }
						});  
					new LoadSelectInfoAsyncTask().execute();//ͬ��stvs
				}
				else{
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(SMainActivity.this, "��ѡʧ��!",
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
		 new AlertDialog.Builder(this).setTitle("ȷ�ϸ�����") 
		    .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() { 
		 
		        @Override 
		        public void onClick(DialogInterface dialog, int which) { 
		        // �����ȷ�ϡ���Ĳ��� 
		        	new UpdatePersonInfoAsyncTask().execute();
		 
		        } 
		    }) 
		    .setNegativeButton("����", new DialogInterface.OnClickListener() { 
		 
		        @Override 
		        public void onClick(DialogInterface dialog, int which) { 
		        // ��������ء���Ĳ���;
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
			
		/*����tabhost��tab*/
		   TabHost tabhost = (TabHost) findViewById(R.id.s_tabHost);
		   tabhost.setup(); // tab������������Ļ� tabhost����һ������ ��Ҫһ������
		   TabHost.TabSpec tab_1 = tabhost.newTabSpec("tab_1");// �����string ����һ��tag
		   // ���ǵ�һ����Ʒ
		   tab_1.setContent(R.id.s_tab1); // ��Ʒ 1 ����Ķ�����ô�� �Ϳ���Ӧ�� ������ô����
		   tab_1.setIndicator("����鿴"); // ��һ����Ʒ�������ı�ǩ Ҳ����tab�ı���
		   tabhost.addTab(tab_1);// Ȼ����Ʒ 1 �Ž�ȥ
		  
		   TabHost.TabSpec tab_2 = tabhost.newTabSpec("tab_2");
		   tab_2.setContent(R.id.s_tab2);// ��Ʒ 2 �Ĳ�������
		   tab_2.setIndicator("��ѡ����");// ��Ʒ 2 �ı���
		   tabhost.addTab(tab_2);// Ȼ�����Ʒ 2
		   
		   TabHost.TabSpec tab_3 = tabhost.newTabSpec("tab_3");
		   tab_3.setContent(R.id.s_tab3);// ��Ʒ 2 �Ĳ�������
		   tab_3.setIndicator("��������");// ��Ʒ 2 �ı���
		   tabhost.addTab(tab_3);// Ȼ�����Ʒ 2
		   
		   Button btupdate = (Button)findViewById(R.id.s_button_update);
		   
		   tabhost.setCurrentTab(0); // ������������óɵ�ǰ�鿴�ĺ���
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
		/*ΪListView���ü����������Item����¼�������������ϸ��Ϣ����S_TopicInfoActivity,�������ݴ�����*/
		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
				Map<?, ?> myMap=(Map<?, ?>)adapter.getItem(position);//��ȡ�����Item��map
				Bundle bundle=new Bundle();//�������ݴ�����bundle
				Set<?> entries = myMap.entrySet( ); 
				if(entries != null) {
						Iterator<?> iterator = entries.iterator( );
						while(iterator.hasNext()) {
							Map.Entry entry =(Map.Entry)iterator.next( );
							bundle.putString(entry.getKey( ).toString(), entry.getValue().toString());//��bundle��ֵ
							//System.out.println(entry.getKey( )+":"+entry.getValue());
						}
			 		}
				Intent intent=new Intent();
				intent = intent.setClass(SMainActivity.this, STopicDetailActivity.class);
				intent.putExtras(bundle);  
				startActivity(intent);
				}
			});
	
		//����˳��Ҫ��
		new LoadTopicsAsyncTask().execute(); //��ʼ��SMainActivity��ʱ���ȼ���һ�ο�����Ϣ
		new LoadGroupIdAsyncTask().execute();//�õ�С��ID
		new LoadSelectInfoAsyncTask().execute();//��ʼ��stvs
		
		if(LoginActivity.student.isSisleader()){
			LinearLayout linear_reset=(LinearLayout)findViewById(R.id.s_linearlayout_reset);
			Button button_reset=new Button(this);
			button_reset.setText("��ѡ");
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
								Toast.makeText(SMainActivity.this, "����δѡ�⣬����ѡ�⣡",
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
			new AlertDialog.Builder(this).setTitle("ȷ���˳���") 
		    .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() { 
		 
		        @Override 
		        public void onClick(DialogInterface dialog, int which) { 
		        // �����ȷ�ϡ���Ĳ��� 
		        SMainActivity.this.finish(); 
		 
		        } 
		    }) 
		    .setNegativeButton("����", new DialogInterface.OnClickListener() { 
		 
		        @Override 
		        public void onClick(DialogInterface dialog, int which) { 
		        // ��������ء���Ĳ���,���ﲻ����û���κβ��� 
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
	
	 /*��tab1���ؿ�����Ϣ��ʹ��������SimpleAdapter�������ݺ�ListView���֮���ӳ��*/	
	   /*
	    * public SimpleAdapter (Context context, List<? extends Map<String, ?>> data,
	    *  int resource, String[] from, int[] to)
	    *   context   ����SimpleAdapter�����ŵ���ͼ�������ġ�
	    *   data    һ��Map���б����б��е�ÿ����Ŀ��Ӧ�б��е�һ�У�Ӧ�ð���������from��ָ������Ŀ
	    *   resource    һ�������б���Ŀ����ͼ���ֵ���ԴΨһ��ʶ�������ļ�������Ӧ������Щ��to�ж����˵����ơ�
	    *   from       һ��������ӵ�Map�Ϲ���ÿһ����Ŀ�������Ƶ��б�
	    *   to    Ӧ���ڲ���from��ʾ�е���ͼ����ЩӦ��ȫ��TextView�����б��������N��ͼ�ǴӲ���from�������N�л�ȡ��ֵ��
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
					
					Toast.makeText(SMainActivity.this, "������Ϣʧ��!",
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
