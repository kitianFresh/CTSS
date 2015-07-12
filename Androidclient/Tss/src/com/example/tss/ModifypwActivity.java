package com.example.tss;

import util.WebServiceUtil;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifypwActivity extends Activity {
	
	EditText etOldpw ;
	EditText etNewpw ;
	Button  btConfirm ;
	
	class ModifypwAsyncTask extends AsyncTask{

		@Override
		protected Object doInBackground(Object... params)  
        {  
		
			if(WebServiceUtil.login(LoginActivity.uid,etOldpw.getText().toString())){
				if(WebServiceUtil.modifyPassword(LoginActivity.uid, etNewpw.getText().toString())){
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(ModifypwActivity.this, "������ĳɹ���",
								     Toast.LENGTH_LONG).show();
						   }
						});
					Intent intent=new Intent();
		            intent = intent.setClass(ModifypwActivity.this, SMainActivity.class); 
		            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//���������½�һ��SMainActivityʵ������������ԭ�����Ǹ�
		            startActivity(intent);
		            ModifypwActivity.this.finish();
				}
			}
				
			else{
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(ModifypwActivity.this, "ԭʼ���벻��ȷ����ȷ��",
							     Toast.LENGTH_LONG).show();
					   }
					});
			}
			return null;
        }
    }
    
	public void confirmClick(View view){
		new ModifypwAsyncTask().execute();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modifypw);
		etOldpw = (EditText)findViewById(R.id.editText_oldpw);
		etNewpw = (EditText)findViewById(R.id.editText_newpw);
		btConfirm = (Button)findViewById(R.id.button_confirm);
	}


}
