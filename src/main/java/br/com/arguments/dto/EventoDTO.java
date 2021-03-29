package br.com.arguments.dto;

import java.sql.Timestamp;
import java.util.Date;

import br.com.arguments.entity.CursosEntity;

public class EventoDTO {
	
	private int id;
	
	private String nome;
	
	private String descricao;
	
	private int numImagem;
	
	private boolean imagem;
	
	private boolean ativo;
	
    private String dataInicio;
    
    private Timestamp dataInicioStamp;
	
    private Date dataCriacao;
    
    private CursosEntity curso;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNumImagem() {
		return numImagem;
	}

	public void setNumImagem(int numImagem) {
		this.numImagem = numImagem;
	}

	public boolean isImagem() {
		return imagem;
	}

	public void setImagem(boolean imagem) {
		this.imagem = imagem;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public CursosEntity getCurso() {
		return curso;
	}

	public void setCurso(CursosEntity curso) {
		this.curso = curso;
	}

	public Timestamp getDataInicioStamp() {
		return dataInicioStamp;
	}

	public void setDataInicioStamp(Timestamp dataInicioStamp) {
		this.dataInicioStamp = dataInicioStamp;
	}
	
}
