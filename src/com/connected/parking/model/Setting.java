package com.connected.parking.model;
 

public class Setting {

	static boolean isLoginWithTwitter;
	static boolean isLoginWithFacebook;
	static boolean isPublishOnTime;
	static boolean isMobileNotification;
	
	
	public static void setLoginWithTwitter(boolean a){
		 isLoginWithTwitter = a;
	}
	
	public boolean isLoginWithTwitter(){
		return this.isLoginWithTwitter;
	}
	
	public static void setLoginWithFacebook(boolean b){
		isLoginWithFacebook = b;
	}
	
	public boolean isLoginWithFacebook(){
		return this.isLoginWithFacebook;
	}
	
	public static void setPublicOnTimeline(boolean c){
		isPublishOnTime = c;
	}
	
	public boolean isPublicOnTimeline(){
		return this.isPublishOnTime;
	}
	
	public static void setMobileNotification(boolean d){
		isMobileNotification = d;
	}
	
	public boolean isMobileNotification(){
		return this.isPublishOnTime;
	}
}
