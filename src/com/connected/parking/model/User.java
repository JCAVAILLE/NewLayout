package com.connected.parking.model;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;

import android.content.Context;
import android.util.Log;

public class User implements Serializable {
	 
	private static final long serialVersionUID = 1L; 
	private static final String saveFileName = "user";
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String profilePictureURL;

	
	public User() {
		
	}
	
	/**
	 * Saves the user to storage through serialization
	 */
	public void save() {
		FileOutputStream fos;
		try {
			fos = Session.getContext().openFileOutput(saveFileName, Context.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(this);
			os.close();
		 	Log.d("User", "Successfully saved user to disk");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the user from storage
	 * @return a new user if successful, null if not
	 */
	public static User load() {
		FileInputStream fis;
		try {
			fis = Session.getContext().openFileInput(saveFileName);

			ObjectInputStream is = new ObjectInputStream(fis);
			User user = (User) is.readObject();
			is.close();
			Log.d("User", "User restored successfully");
			return user;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// Failed to find the user
		return null;
	}
	

	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfilePictureURL() {
		return profilePictureURL;
	}

	public void setProfilePictureURL(String profilePictureURL) {
		this.profilePictureURL = profilePictureURL;
	}


}
