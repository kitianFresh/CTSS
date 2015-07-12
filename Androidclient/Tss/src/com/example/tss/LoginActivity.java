package com.example.tss;
import util.*;

import java.io.File;
import com.example.tss.R;

import entity.Student;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.KITKAT) public class LoginActivity extends Activity {
	
	public static String uid;//��¼�û�id�Ա����û���¼���ȡ�������Ϣ
    public static Student student;//��ȡѧ����Ϣ//���ڸ���activity֮�乲������
    
	private static final String LOG_TAG = null;
    EditText userid ;  
    EditText password ;  
    private ProgressDialog pd;
    class LoginAsyncTask extends AsyncTask{

		@Override
		protected Object doInBackground(Object... params)  
        {  
			runOnUiThread(new Runnable() {
				public void run() {
					pd= ProgressDialog.show(LoginActivity.this, "", "���ڵ�½....");
				   }
				});    	
			if(WebServiceUtil.login(userid.getText().toString(),password.getText().toString())){
				uid = userid.getText().toString();
				student = WebServiceUtil.getStudentInfoBySid(uid);//��ʼ��student����Ϣ   	
				
		        //System.out.println("�û���ID��"+uid);
				runOnUiThread(new Runnable() {
					public void run() {
						pd= ProgressDialog.show(LoginActivity.this, "", "��½�ɹ�");
						pd.dismiss();
					   }
					});
				
	            Intent intent=new Intent();
	            intent = intent.setClass(LoginActivity.this, SMainActivity.class); 
	            startActivity(intent);
	            LoginActivity.this.finish();
			}
				
			else{
				runOnUiThread(new Runnable() {
					public void run() {
						pd.dismiss();
						Toast.makeText(LoginActivity.this, "��¼ʧ��",
							     Toast.LENGTH_LONG).show();
					   }
					});    	
			}
			return null;
        }
    }
    
 
    
    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory. 
    	if(!isExternalStorageWritable()){
    		System.out.println("�ڴ治��");
    		return null;
    	}
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), albumName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
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
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         userid = (EditText)findViewById(R.id.editText_uid);  
         password = (EditText)findViewById(R.id.editText_password);  
    }
    
    public void loginclick(View view){  
    	new LoginAsyncTask().execute();

    }  
      
}
