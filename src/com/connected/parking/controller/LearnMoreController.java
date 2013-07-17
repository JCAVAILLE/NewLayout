package com.connected.parking.controller;
  
import com.connected.parking.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class LearnMoreController extends Activity{

	//PullToRefreshListView mPullRefreshListView;
	ListView list_view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.learn_more);
	}
	
	
	
}
