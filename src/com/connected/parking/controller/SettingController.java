package com.connected.parking.controller;
 

import com.connected.parking.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.TextView;

import com.connected.parking.model.Setting;

public class SettingController extends Activity{

	TextView about_btn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting_controller);
		
		/*MenuHelper.setContentViewAndSlideMenu(this, R.layout.settings, R.string.menu_settings);
		ToggleButton autoDownloadFiles = (ToggleButton) findViewById(R.id.settings_auto_download_files);
		autoDownloadFiles.setChecked(Settings.isAutoDownloadFiles());*/
		
		about_btn = (TextView)findViewById(R.id.about);
		about_btn.setOnTouchListener(new OnTouchListener() { 
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SettingController.this, AboutController.class);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_from_left);
				return false;
			}
		});
	}
	  
	public void onToggleClicked(View v) {
	    
	    switch(v.getId()) {
	    	case R.id.login_with_twitter:
	    		CheckBox cb1 = (CheckBox) findViewById(R.id.login_with_twitter);
	    		boolean a = cb1.isChecked();
	    		Setting.setLoginWithTwitter(a);
	    		break;

	    	case R.id.login_with_facebook:
	    		CheckBox cb2 = (CheckBox) findViewById(R.id.login_with_facebook);
	    		boolean b = cb2.isChecked();
	    		Setting.setLoginWithFacebook(b);
	    		break;
	    		
	    	case R.id.public_on_timeline:
	    		CheckBox cb3 = (CheckBox) findViewById(R.id.public_on_timeline);
	    		boolean c = cb3.isChecked();
	    		Setting.setPublicOnTimeline(c);
	    		break;
	    		
	    	case R.id.mobile_notification: 
	    		CheckBox cb4 = (CheckBox) findViewById(R.id.mobile_notification);
	    		boolean d = cb4.isChecked();
	    		Setting.setMobileNotification(d);
	    		break;
	    	default:
	    		break;
	    }
	}

}

