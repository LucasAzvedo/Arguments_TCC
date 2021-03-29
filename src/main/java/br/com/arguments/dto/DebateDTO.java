package br.com.arguments.dto;

import java.sql.Timestamp;

import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.UsuarioEntity;

public class DebateDTO {
	
	private Long id;
	
	private String nomeDebate;
	
	private String temaDebate;
	
	private CursosEntity idCursos;
	
	private int status; // 1-ativo 0-terminado
	
	private String dataCriacao;
	
	private Timestamp dataCriacaoStamp;
	
	private String dataFechamento;
	
	private Timestamp dataFechamentoStamp;
	
	private String assunto;
	
	private UsuarioEntity usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDebate() {
		return nomeDebate;
	}

	public void setNomeDebate(String nomeDebate) {
		this.nomeDebate = nomeDebate;
	}

	public String getTemaDebate() {
		return temaDebate;
	}

	public void setTemaDebate(String temaDebate) {
		this.temaDebate = temaDebate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(String dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Timestamp getDataCriacaoStamp() {
		return dataCriacaoStamp;
	}

	public void setDataCriacaoStamp(Timestamp dataCriacaoStamp) {
		this.dataCriacaoStamp = dataCriacaoStamp;
	}

	public Timestamp getDataFechamentoStamp() {
		return dataFechamentoStamp;
	}

	public void setDataFechamentoStamp(Timestamp dataFechamentoStamp) {
		this.dataFechamentoStamp = dataFechamentoStamp;
	}

	public CursosEntity getIdCursos() {
		return idCursos;
	}

	public void setIdCursos(CursosEntity idCursos) {
		this.idCursos = idCursos;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	

}
