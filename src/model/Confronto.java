package model;

import java.io.Serializable;

public class Confronto implements Serializable{

	private String timeA;
	private String timeB;
	
	public Confronto(String timeA, String timeB) {
		super();
		this.timeA = timeA;
		this.timeB = timeB;
	}
	
	public Confronto() {
		super();
	}

	public String getTimeA() {
		return timeA;
	}
	public void setTimeA(String timeA) {
		this.timeA = timeA;
	}
	public String getTimeB() {
		return timeB;
	}
	public void setTimeB(String timeB) {
		this.timeB = timeB;
	}
	
	
	
	
}