package br.edu.unirn.pbd.prova.modelos;

import java.util.Date;

public class Projeto {

	private int id;
	private String descricao;
	private Date dataInicio;
	private Date dataFim;

	public Projeto() {
		super();
	}

	public Projeto(String descricao, Date dataInicio, Date dataFim) {
		super();
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	@Override
	public String toString() {
		return descricao;
	}

}
