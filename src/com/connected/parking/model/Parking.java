package com.connected.parking.model;

import java.io.Serializable;

public class Parking implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1174653775724721568L;

	private String ID = null;
	private String Type = null;
	private double lat = 0;
	private double lon = 0;
	private String location = null;
	private int rate = 0;
	private long timestamp = 0;
	private long last_data_updated = 0;
	private int sum = 0;
	private int availbility = 0;
	private int payment_type = 0; //0 credit card, 1 coins, 2 smart phone
	private int facility_type = 0; //0 camera , 1 sensor
	
    /*id:   String
    type: String, (type of the car parking, for example, outdoor, indoor, onstreet)
    geo:  Array  [lat,lng]
    location: String
    rate: Number
    timestamp: Timestamp
    last_data_updated: Timestamp
    sum: Number
    availbility: Number
    payment_option: Array (credit card, coins, smart phone)
    facility: Array (camera, sensor)*/
	
	public Parking(){
		
	}
	
	public void setParkingID(String id) {
		this.ID = id;
	}

	public String getParkingname() {
		return ID;
	}

	public void setParkingType(String type) {
		this.Type = type;
	}

	public String getParkingType() {
		return Type;
	}

	public double getParkingLon() {
		return lon;
	}

	public void setParkingLon(double lon) {
		this.lon = lon;
	}
	
	public double getParkingLat() {
		return lat;
	}

	public void setParkingLat(double lat) {
		this.lat = lat;
	}

	public String getParkingLocation() {
		return location;
	}

	public void setParkingLocation(String location) {
		this.location = location;
	}

	public int  getParkingRate() {
		return rate;
	}

	public void setParkingRate(int rate) {
		this.rate = rate;
	}
	
	public long  getParkingTimeStamp() {
		return timestamp;
	}

	public void setParkingTimeStamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public long  getParkingUpdateTimeStamp() {
		return last_data_updated;
	}

	public void setParkingUpdateTimeStamp(long update_timestamp) {
		this.last_data_updated = update_timestamp;
	}
	
	public int  getParkingSum() {
		return sum;
	}

	public void setParkingTimeStamp(int s) {
		this.sum = s;
	}
	
	public int  getParkingAvailbility() {
		return availbility;
	}

	public void setParkingAvailbility(int a) {
		this.availbility = a;
	}
	
	public int  getParkingPaymentType() {
		return payment_type;
	}

	public void setParkingPaymentType(int p) {
		this.payment_type = p;
	}
	
	public int  getParkingFacilityType() {
		return facility_type;
	}

	public void setParkingFacilityType(int f) {
		this.facility_type = f;
	}
	 
	
}
