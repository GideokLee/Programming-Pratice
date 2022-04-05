package com.ssafy.springtest.dto;

public class CountryStatusDto {
	
	private int seq;
	
	private String cname;
	
	private int patient;
	
	private String rcode;
	
	
	public CountryStatusDto() {

	}
	public CountryStatusDto(int seq, String cname, int patient, String rcode) {
		super();
		this.seq = seq;
		this.cname = cname;
		this.patient = patient;
		this.rcode = rcode;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getPatient() {
		return patient;
	}
	public void setPatient(int patient) {
		this.patient = patient;
	}
	public String getRcode() {
		return rcode;
	}
	public void setRcode(String rcode) {
		this.rcode = rcode;
	}
	
	@Override
	public String toString() {
		return "CountryStatusDto [seq=" + seq + ", sname=" + cname + ", patient=" + patient + ", rcode=" + rcode + "]";
	}
}
