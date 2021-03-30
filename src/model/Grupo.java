package model;

import java.io.Serializable;

public class Grupo implements Serializable{

	private String grupo;
	private Time time;
	
	public Grupo() {
		super();
	}

	public Grupo(String grupo, Time time) {
		this.grupo = grupo;
		this.time = time;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}
	
	
	
}
