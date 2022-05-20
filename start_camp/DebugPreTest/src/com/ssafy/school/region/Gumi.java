package com.ssafy.school.region;

public class Gumi extends Region {
	private String koreanName;
	private int roomTotalCount;
	private int roomEmptyCount;

	public Gumi() {
	}

	public Gumi(String name, int studentCount, String koreanName, int roomTotalCount, int roomEmptyCount) {
		super(name, studentCount);
		setKoreanName(koreanName);
		setRoomTotalCount(roomTotalCount);
		setRoomEmptyCount(roomEmptyCount);
	}

	public String getKoreanName() {
		return koreanName;
	}

	public void setKoreanName(String koreanName) {
		this.koreanName = koreanName;
	}

	public int getRoomTotalCount() {
		return roomTotalCount;
	}

	public void setRoomTotalCount(int roomTotalCount) {
		this.roomTotalCount = roomTotalCount;
	}

	public int getRoomEmptyCount() {
		return roomEmptyCount;
	}

	public void setRoomEmptyCount(int roomEmptyCount) {
		this.roomEmptyCount = roomEmptyCount;
	}

	@Override
	public String toString() {
		return "Gumi [koreanName=" + koreanName + ", roomTotalCount=" + roomTotalCount + ", roomEmptyCount="
				+ roomEmptyCount + "]";
	}
}
