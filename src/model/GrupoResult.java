package model;

import java.io.Serializable;

public class GrupoResult implements Serializable{

	private String nome_time;
	private Integer num_jogos_disputados;
	private Integer vitorias;
	private Integer empates;
	private Integer derrotas;
	private Integer gols_marcados;
	private Integer gols_sofridos;
	private Integer saldo_gols;
	private Integer pontos;
	
	public String getNome_time() {
		return nome_time;
	}
	public void setNome_time(String nome_time) {
		this.nome_time = nome_time;
	}
	public Integer getNum_jogos_disputados() {
		return num_jogos_disputados;
	}
	public void setNum_jogos_disputados(Integer num_jogos_disputados) {
		this.num_jogos_disputados = num_jogos_disputados;
	}
	public Integer getVitorias() {
		return vitorias;
	}
	public void setVitorias(Integer vitorias) {
		this.vitorias = vitorias;
	}
	public Integer getEmpates() {
		return empates;
	}
	public void setEmpates(Integer empates) {
		this.empates = empates;
	}
	public Integer getDerrotas() {
		return derrotas;
	}
	public void setDerrotas(Integer derrotas) {
		this.derrotas = derrotas;
	}
	public Integer getGols_marcados() {
		return gols_marcados;
	}
	public void setGols_marcados(Integer gols_marcados) {
		this.gols_marcados = gols_marcados;
	}
	public Integer getGols_sofridos() {
		return gols_sofridos;
	}
	public void setGols_sofridos(Integer gols_sofridos) {
		this.gols_sofridos = gols_sofridos;
	}
	public Integer getSaldo_gols() {
		return saldo_gols;
	}
	public void setSaldo_gols(Integer saldo_gols) {
		this.saldo_gols = saldo_gols;
	}
	public Integer getPontos() {
		return pontos;
	}
	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}
	
	
}
