package com.connected.parking.controller;
 
import java.io.File;

import com.connected.parking.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.connected.parking.utils.Tools;
import com.connected.parking.views.GestureDetectorViewFlipper;

public class ProfileController extends Activity{

	private GestureDetectorViewFlipper viewFlipper = null;
	private GestureDetector mGestureDetector = null;
	
	private int currentPos = 0;
	private static final int FLING_MIN_DISTANCE = 50;
	private static final int FLING_MIN_VELOCITY = 0;
	
	//=================================================
	//userprofile
	//=================================================
	private ImageView user_imge = null;
	private String[] items = new String[] { "选择本地图片", "拍照" };
	/* 头像名称 */
	private static final String IMAGE_FILE_NAME = "faceImage.jpg";

	/* 请求码 */
	private static final int IMAGE_REQUEST_CODE = 0;
	private static final int CAMERA_REQUEST_CODE = 1;
	private static final int RESULT_REQUEST_CODE = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_profile_controller);
		
		viewFlipper = (GestureDetectorViewFlipper)findViewById(R.id.viewflipper1);
	    mGestureDetector = new GestureDetector(new GDListener());
	    viewFlipper.setGestureDetector(mGestureDetector); 
	    show_selected(currentPos);
	    
        user_imge = (ImageView)findViewById(R.id.user_image);
	}
	
	public void ChangeImage(View view){
		Toast.makeText(ProfileController.this, "Change Image", 1000).show();
		
		new AlertDialog.Builder(this)
		.setTitle("设置头像")
		.setItems(items, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					Intent intentFromGallery = new Intent();
					intentFromGallery.setType("image/*"); // 设置文件类型
					intentFromGallery
							.setAction(Intent.ACTION_GET_CONTENT);
					startActivityForResult(intentFromGallery, IMAGE_REQUEST_CODE);
					break;
					
				case 1:
					Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					// 判断存储卡是否可以用，可用进行存储
					if (Tools.hasSdcard()) {

						intentFromCapture.putExtra(
								MediaStore.EXTRA_OUTPUT,
								Uri.fromFile(new File(Environment .getExternalStorageDirectory(), IMAGE_FILE_NAME)));
					}

					startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
					break;
				}
			}
		})
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).show();
		
	}
 
	

    ///////////////////////////////////////////////////////////////////////////////////////////
	private void show_selected(int id){ 
		
		int[] ratioId = {R.id.user_search_bg, R.id.user_status_bg, R.id.user_profile_bg};
		ImageView image = (ImageView)findViewById(ratioId[id]);
		image.setSelected(true);
	}
	
	private void show_noselected(int id){
		
		int[] ratioId = {R.id.user_search_bg, R.id.user_status_bg, R.id.user_profile_bg};
		ImageView image = (ImageView)findViewById(ratioId[id]);
		image.setSelected(false);;
	} 
	
	
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
	
	private void showNextView(){
		
		viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
		viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));		
		viewFlipper.showNext();
		currentPos++;
		if(currentPos == viewFlipper.getChildCount()){
			currentPos = 0;
			show_selected(currentPos);
			show_noselected(viewFlipper.getChildCount()-1); 
		}else{
			show_selected(currentPos);
			show_noselected(currentPos-1); 
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
		}else{
			show_selected(currentPos);
			show_noselected(currentPos+1); 
		}
	}
 
	///////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//结果码不等于取消时候
		if (resultCode != RESULT_CANCELED) {

			switch (requestCode) {
			case IMAGE_REQUEST_CODE:
				startPhotoZoom(data.getData());
				break;
			case CAMERA_REQUEST_CODE:
				if (Tools.hasSdcard()) {
					File tempFile = new File(Environment.getExternalStorageDirectory() + IMAGE_FILE_NAME);
					startPhotoZoom(Uri.fromFile(tempFile));
				} else {
					
					Toast.makeText(ProfileController.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
				}

				break;
			case RESULT_REQUEST_CODE:
				if (data != null) {
					getImageToView(data);
				}
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data); 
	}
	
	/**
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 设置裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 320);
		intent.putExtra("outputY", 320);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 2);
	}

	/**
	 * 保存裁剪之后的图片数据
	 * 
	 * @param picdata
	 */
	private void getImageToView(Intent data) {
		Bundle extras = data.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");
			Drawable drawable = new BitmapDrawable(photo);
			user_imge.setImageDrawable(drawable);
		}
	}
  
}
