package com.connected.parking.controller;
 

import com.connected.parking.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class SettingController extends Activity{

	TextView about_btn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting_controller);
		
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

	
}
