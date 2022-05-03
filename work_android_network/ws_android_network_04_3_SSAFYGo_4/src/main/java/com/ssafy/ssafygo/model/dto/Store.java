package com.ssafy.ssafygo.model.dto;


public class Store {
	private int id;
	private String name;
	private String tel;
	private String imgUrl;
	private double lat;
	private double lng;
	
	public Store() {}
	public Store(int id, String name, String tel, String imgUrl, double lat, double lng) {
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.imgUrl = imgUrl;
		this.lat = lat;
		this.lng = lng;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	
	
	
}