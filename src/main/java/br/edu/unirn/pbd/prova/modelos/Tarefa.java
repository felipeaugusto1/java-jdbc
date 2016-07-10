package br.edu.unirn.pbd.prova.modelos;

import java.util.Date;

public class Tarefa {

	private int id;
	private String titulo;
	private String descricao;
	private Date dataCriacao;
	private Date dataFechamento;
	private int porcentagem;
	private Projeto projeto;
	private Usuario usuarioResponsavel;
	private Usuario usuarioAbertura;
	private Usuario usuarioFechamento;

	public Tarefa() {
		super();
	}

	public Tarefa(String titulo, String descricao, Date dataCriacao, Date dataFechamento, int porcentagem,
			Projeto projeto, Usuario usuarioResponsavel, Usuario usuarioAbertura, Usuario usuarioFechamento) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataCriacao = dataCriacao;
		this.dataFechamento = dataFechamento;
		this.porcentagem = porcentagem;
		this.projeto = projeto;
		this.usuarioResponsavel = usuarioResponsavel;
		this.usuarioAbertura = usuarioAbertura;
		this.usuarioFechamento = usuarioFechamento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public int getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(int porcentagem) {
		this.porcentagem = porcentagem;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Usuario getUsuarioAbertura() {
		return usuarioAbertura;
	}

	public void setUsuarioAbertura(Usuario usuarioAbertura) {
		this.usuarioAbertura = usuarioAbertura;
	}

	public Usuario getUsuarioFechamento() {
		return usuarioFechamento;
	}

	public void setUsuarioFechamento(Usuario usuarioFechamento) {
		this.usuarioFechamento = usuarioFechamento;
	}

	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	public String isConcluida() {
		if (porcentagem == 100)
			return "Sim";
		return "Não";
	}

	@Override
	public String toString() {
		return "Tarefa [titulo=" + titulo + ", descricao=" + descricao + ", dataCriacao=" + dataCriacao
				+ ", porcentagem=" + porcentagem + "%, projeto=" + projeto + ", usuarioResponsavel=" + usuarioResponsavel
				+ ", usuarioAbertura=" + usuarioAbertura + ", usuarioFechamento=" + usuarioFechamento + "]";
	}

}
