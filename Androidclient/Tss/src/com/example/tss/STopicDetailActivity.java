package com.example.tss;

import java.io.File;

import com.example.tss.R;

import util.WebServiceUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class STopicDetailActivity extends Activity {
	Bundle exercise;
	String FilePath=null;
	private static final String LOG_TAG = null;
	private ProgressDialog pd;
	class DownloadAsyncTask extends AsyncTask{
		
		@Override
		protected Object doInBackground(Object... arg0) {
			 FilePath = WebServiceUtil.TssFileDir+"/"+exercise.getString("E_title")+".pdf";
			if(!isExternalStorageWritable()){
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(STopicDetailActivity.this, "�ڴ治��!",
							     Toast.LENGTH_SHORT).show();

					   }
					});//Toast����ֱ���ڷ����߳���ʹ�ã�����ͨ��handler��UIˢ�µ�������Ϣ������UI����ʾToast������UI���߳�
			}
			else{
				runOnUiThread(new Runnable() {
					public void run() {
						pd=ProgressDialog.show(STopicDetailActivity.this, FilePath,"�������ء���������", true,false);
					   }
					});
				
				if(WebServiceUtil.downloadFile(exercise.getString("E_filepath"),exercise.getString("E_title"))){
					runOnUiThread(new Runnable() {
							public void run() {
								pd.dismiss();
								Toast.makeText(STopicDetailActivity.this, "���سɹ�!",
									     Toast.LENGTH_LONG).show();	
							   }
							});    	
				}
				else{
					runOnUiThread(new Runnable() {
							public void run() {
								pd.dismiss();
								Toast.makeText(STopicDetailActivity.this, "����ʧ��!",
									     Toast.LENGTH_LONG).show();
							   }
							});    	
				}
			}
			return null;
		}
		
	}
	
	class SelectTopicAsyncTask extends AsyncTask{

		@Override
		protected Object doInBackground(Object... params) {
			if(WebServiceUtil.selectTopic(SMainActivity.gid, Integer.valueOf(exercise.getString("E_id")))){
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(STopicDetailActivity.this, "ѡ��ɹ�!",
							     Toast.LENGTH_LONG).show();
					   }
					});    	
			}
			else{
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(STopicDetailActivity.this, "ѡ��ʧ��!",
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
			 SMainActivity.stvs = WebServiceUtil.getSelectedTopicInfo(LoginActivity.student.getSid());
			return null;
		}
	 }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_s_topicdetail);
		
		Intent intent=this.getIntent();
		exercise=intent.getExtras();
		
		
		TextView textview0=(TextView)findViewById(R.id.s_topicdetail_E_title);
		TextView textview1=(TextView)findViewById(R.id.s_topicdetail_E_teacher);
		TextView textview2=(TextView)findViewById(R.id.s_topicdetail_E_level);
		TextView textview3=(TextView)findViewById(R.id.s_topicdetail_E_sum);
		TextView textview4=(TextView)findViewById(R.id.s_topicdetail_E_leftcount);
		TextView textview5=(TextView)findViewById(R.id.s_topicdetail_E_phone);
		TextView textview6=(TextView)findViewById(R.id.s_topicdetail_E_summary);
		
		textview0.setText(exercise.getString("E_title"));
		textview1.setText(exercise.getString("E_teacher"));
		textview2.setText(exercise.getString("E_level"));
		textview3.setText(exercise.getString("E_sum"));
		textview4.setText(exercise.getString("E_leftcount"));
		textview5.setText(exercise.getString("T_phone"));
		textview6.setText(exercise.getString("E_summary"));
		
		Button btdownload = (Button)findViewById(R.id.s_topicdetail_downloadFile);
		Button btopenfile = (Button)findViewById(R.id.s_topicdetail_openFile);
		btdownload.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
					new DownloadAsyncTask().execute();
					
				}
			});
		btopenfile.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setAction(android.content.Intent.ACTION_VIEW);
				
				if(FilePath==null){
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(STopicDetailActivity.this, "�ļ������ڣ���������",
								     Toast.LENGTH_LONG).show();
						   }
						});    	
				}
				else{
					File file = new File(FilePath);
					intent.setDataAndType(Uri.fromFile(file), "application/pdf");
					startActivity(intent);
				}
				
				
			}
		});
		if(LoginActivity.student.isSisleader()){
			LinearLayout linear_button=(LinearLayout)findViewById(R.id.s_topicdetail_layoutbutton);
			Button button_submit=new Button(this);
			button_submit.setText("ѡ��");
			linear_button.addView(button_submit);
			
			button_submit.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					new AlertDialog.Builder(STopicDetailActivity.this).setTitle("ȷ��ѡ������") 
				    .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() { 
				 
				        @Override 
				        public void onClick(DialogInterface dialog, int which) { 
				        // �����ȷ�ϡ���Ĳ��� 
				        	if(SMainActivity.stvs!=null){
				        		runOnUiThread(new Runnable() {
									public void run() {
										Toast.makeText(STopicDetailActivity.this, "���Ѿ�ѡ�⣬������ѡ!",
											     Toast.LENGTH_LONG).show();
									   }
									});   
				        	}
				        	else{
					        	new SelectTopicAsyncTask().execute();
					        	new LoadSelectInfoAsyncTask().execute();//ѡ������stvs
				        	}

				 
				        } 
				    }) 
				    .setNegativeButton("����", new DialogInterface.OnClickListener() { 
				 
				        @Override 
				        public void onClick(DialogInterface dialog, int which) { 
				        // ��������ء���Ĳ���,���ﲻ����û���κβ��� 
				        } 
				    }).show();
				}
			});
		}
	}
	
	    
	private boolean isExternalStorageWritable() {
	        String state = Environment.getExternalStorageState();
	        if (Environment.MEDIA_MOUNTED.equals(state)) {
	            return true;
	        }
	        return false;
	 }

 /* Checks if external storage is available to at least read */
	private boolean isExternalStorageReadable() {
	        String state = Environment.getExternalStorageState();
	        if (Environment.MEDIA_MOUNTED.equals(state) ||
	            Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	            return true;
	        }
	        return false;
	}

}
