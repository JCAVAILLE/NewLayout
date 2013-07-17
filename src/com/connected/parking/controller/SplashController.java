package com.connected.parking.controller;

import com.connected.parking.R; 
import com.connected.parking.utils.AppStatus;
import com.connected.parking.views.GestureDetectorViewFlipper; 

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashController extends Activity {

	private GestureDetectorViewFlipper viewFlipper = null;
	private GestureDetector mGestureDetector = null;
	
	private int currentPos = 0;
	private static final int FLING_MIN_DISTANCE = 50;
	private static final int FLING_MIN_VELOCITY = 0;
	
	Button login_btn = null;
	Button signin_btn = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash_controller);
		
		boolean online = AppStatus.isOnline(this); 
		Toast.makeText(SplashController.this, String.valueOf(online), 1000).show();
		
		String hasCon = AppStatus.getConnectionType(this);
		Toast.makeText(SplashController.this, hasCon, 1000).show();
		
		viewFlipper = (GestureDetectorViewFlipper)findViewById(R.id.viewflipper);
	    mGestureDetector = new GestureDetector(new GDListener());
	    viewFlipper.setGestureDetector(mGestureDetector);
	        
        login_btn = (Button)findViewById(R.id.login);
        signin_btn = (Button)findViewById(R.id.signin);
         
        show_selected(currentPos);
        login_btn.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent login = new Intent(SplashController.this, LoginController.class);
				startActivityForResult(login, 0);
				//SplashActivity.this.finish();
			}
		}); 
		
        signin_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent signin = new Intent(SplashController.this, RegisterController.class);
				startActivityForResult(signin, 0);
			}
		});
 
		/*Thread splashThread = new Thread() {
			@Override
			public void run() {
				try {
					int waited = 0;
	                while (waited < 3000) {
	                	sleep(100);
	                    waited += 100;
	                }
                } catch (InterruptedException e) {
                	// do nothing
	            } finally { 
	            	finish();
		            Intent i = new Intent();
	                i.setClassName("com.connected.parking", "com.connected.parking.TicketlineActivity");
	                //i.putExtra("class", "Home");
			        startActivity(i);
		        }
            }
        };
 
        splashThread.start();*/
    }
	
	
	//==============================================================
	//
	//==============================================================
    
	private void showNextView(){
		
		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));		
		viewFlipper.showNext();
		currentPos++;
		if(currentPos == viewFlipper.getChildCount()){
			currentPos = 0;
			show_selected(currentPos);
			show_noselected(viewFlipper.getChildCount()-1);
			//login_btn.setEnabled(true);
	        //signin_btn.setEnabled(true);
		}else{
			show_selected(currentPos);
			show_noselected(currentPos-1);
			//login_btn.setEnabled(true);
	        //signin_btn.setEnabled(true);
		}
	}
	
	private void showPreviousView(){
		
		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
		viewFlipper.showPrevious();
		
		currentPos--;
		if(currentPos == -1){
			currentPos = viewFlipper.getChildCount()-1;
			show_selected(currentPos);
			show_noselected(0);
			//login_btn.setEnabled(true);
	        //signin_btn.setEnabled(true);
		}else{
			show_selected(currentPos);
			show_noselected(currentPos+1);
			//login_btn.setEnabled(true);
	        //signin_btn.setEnabled(true);
		}
	}
	  
	private void show_selected(int id){ 
		
		int[] ratioId = {R.id.radio_bar01, R.id.radio_bar02, R.id.radio_bar03,
				R.id.radio_bar04, R.id.radio_bar05};
		ImageView img = (ImageView)findViewById(ratioId[id]);
		img.setSelected(true);
	}
	
	private void show_noselected(int id){
		
		int[] ratioId = {R.id.radio_bar01, R.id.radio_bar02, R.id.radio_bar03,
				R.id.radio_bar04, R.id.radio_bar05};
		ImageView img = (ImageView)findViewById(ratioId[id]);
		img.setSelected(false);
	}
	
	/////////////////////////////////////////////////////////////////////////////
	
	
	/*
	 * 自定义OnGestureListener类
	 */
	private class GDListener implements  android.view.GestureDetector.OnGestureListener{

		@Override
		public boolean onDown(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			if (e1.getX() - e2.getX()> FLING_MIN_DISTANCE && 
					Math.abs(velocityX) > FLING_MIN_VELOCITY) {
				Log.e("fling", "left");
				showNextView();
				return true;
			} else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE && 
					Math.abs(velocityX) > FLING_MIN_VELOCITY){
				Log.e("fling", "right");
				showPreviousView();
				return true;
			}
			return false;
		}

		@Override
		public void onLongPress(MotionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
				float arg3) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onShowPress(MotionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean onSingleTapUp(MotionEvent arg0) {
			// TODO Auto-generated method stub
			return false;
		}
 
	}
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_controller, menu);
		return true;
	}

}
