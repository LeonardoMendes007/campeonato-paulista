package model;

import java.io.Serializable;
import java.util.Calendar;

public class Jogo implements Serializable {

	private Integer codigoTimeA;
	private Integer codigoTimeB;
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

	public Integer getCodigoTimeA() {
		return codigoTimeA;
	}

	public void setCodigoTimeA(Integer codigoTimeA) {
		this.codigoTimeA = codigoTimeA;
	}

	public Integer getCodigoTimeB() {
		return codigoTimeB;
	}

	public void setCodigoTimeB(Integer codigoTimeB) {
		this.codigoTimeB = codigoTimeB;
	}

	@Override
	public String toString() {
		return "Jogo [timeA=" + timeA + ", timeB=" + timeB + ", golsA=" + golsA + ", golsB=" + golsB + ", data=" + data
				+ "]";
	}
	
	

	
	
}
