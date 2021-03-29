package br.com.arguments.dto;

import br.com.arguments.entity.CursosEntity;

public class TrabalhoDTO {
	
	private int id;
	
	private String nome;
	
	private String descricao;
	
	private boolean ativo;
	
	private String  nomearq;
	
	private String caminho;
	
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public CursosEntity getCurso() {
		return curso;
	}

	public void setCurso(CursosEntity curso) {
		this.curso = curso;
	}

	public String getNomearq() {
		return nomearq;
	}

	public void setNomearq(String nomearq) {
		this.nomearq = nomearq;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
}
