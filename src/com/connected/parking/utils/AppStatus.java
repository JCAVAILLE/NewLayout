package com.connected.parking.utils;

import com.connected.parking.model.Session;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class AppStatus {
	
	public AppStatus(){
		
	}
	
	public static String getConnectionType(Context context) { 
		
		String haveConnectedWifi = null; 
		String haveConnectedMobile = null; 
		 
	    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo(); 
	    for (NetworkInfo networkInfo : netInfo) { 
	        if (networkInfo.getTypeName().equalsIgnoreCase("WIFI")) 
	            if (networkInfo.isAvailable() && networkInfo.isConnected()) 
	                haveConnectedWifi = "WIFI"; 
	        if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE")) 
	            if (networkInfo.isAvailable() && networkInfo.isConnected()) 
	                haveConnectedMobile = "MOBILE"; 
	    } 
	    return (haveConnectedWifi != null) ? haveConnectedWifi : haveConnectedMobile; 
	}
	 
    public static boolean isOnline() { 
    	
    	boolean haveConnectedWifi = false; 
	    boolean haveConnectedMobile = false;
	    
	    ConnectivityManager connectivityManager = (ConnectivityManager) Session.getContext().getSystemService(Context.CONNECTIVITY_SERVICE); 
		NetworkInfo[] netInfo = connectivityManager.getAllNetworkInfo(); 
	    for (NetworkInfo networkInfo : netInfo) { 
	        if (networkInfo.getTypeName().equalsIgnoreCase("WIFI")) 
	            if (networkInfo.isAvailable() && networkInfo.isConnected()) 
	                haveConnectedWifi = true; 
	        if (networkInfo.getTypeName().equalsIgnoreCase("MOBILE")) 
	            if (networkInfo.isAvailable() && networkInfo.isConnected()) 
	                haveConnectedMobile = true; 
	    } 
	    
	    boolean connection = haveConnectedWifi || haveConnectedMobile;

	    Log.d("AppStatus", Boolean.toString(connection));
	    return connection; 
    } 
    
    public static void setChangeOrientation(Activity a) {
    	if(isTablet(a)) {
    		a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    	}
    }
    
    public static boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }


}
