package com.ssafy.ssafygo.model.dto;

public class StoreReview {
	private int id;
	private String content;
	private int score;
	private int storeUid;
	
	public StoreReview() {}
	
	public StoreReview(int id, String content, int score, int storeUid) {
		this.id = id;
		this.content = content;
		this.score = score;
		this.storeUid = storeUid;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getStoreUid() {
		return storeUid;
	}
	public void setStoreUid(int storeUid) {
		this.storeUid = storeUid;
	}
}
