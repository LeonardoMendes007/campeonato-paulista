package model;

import java.io.Serializable;
import java.util.Calendar;

public class Jogo implements Serializable {

	private String timeA;
	private String timeB;
	private Integer golsA;
	private Integer golsB;
	private String data;

	public Jogo() {

	}

	public Jogo(String timeA, String timeB, Integer golsA, Integer golsB, String data) {
		this.timeA = timeA;
		this.timeB = timeB;
		this.golsA = golsA;
		this.golsB = golsB;
		this.data = data;
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

	public Integer getGolsA() {
		return golsA;
	}

	public void setGolsA(Integer golsA) {
		this.golsA = golsA;
	}

	public Integer getGolsB() {
		return golsB;
	}

	public void setGolsB(Integer golsB) {
		this.golsB = golsB;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	
	
}
