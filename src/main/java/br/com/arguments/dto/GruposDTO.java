package br.com.arguments.dto;

import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.InstituicaoEntity;

public class GruposDTO {

	private Long id;

	private String nomeGrupo;
	
	private String descricao;

	private CursosEntity curso;
	
	private InstituicaoEntity instituicao;

	private int qtdMaximaMembros;
	
	private int tipoGrupo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public CursosEntity getCurso() {
		return curso;
	}

	public int getQtdMaximaMembros() {
		return qtdMaximaMembros;
	}

	public void setCurso(CursosEntity curso) {
		this.curso = curso;
	}

	public void setQtdMaximaMembros(int qtdMaximaMembros) {
		this.qtdMaximaMembros = qtdMaximaMembros;
	}

	public int getTipoGrupo() {
		return tipoGrupo;
	}

	public void setTipoGrupo(int tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public InstituicaoEntity getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoEntity instituicao) {
		this.instituicao = instituicao;
	}

}