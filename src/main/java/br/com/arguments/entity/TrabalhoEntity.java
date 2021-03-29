package br.com.arguments.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TRABALHOS")
public class TrabalhoEntity implements Serializable{
	
	@Id
	@Column(name="ID_TRABALHO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="NOME_TRABALHO")
	private String nome;
	
	@Column(name="DESCRICAO", nullable=true)
	private String descricao;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="NUM_CURSO")
	private CursosEntity numCurso;
	
	@Column(name="ATIVO")
	private boolean ativo;
	
	@Column(name="CAMINHO")
	private String caminho;
	
	@Column(name="NOME_ARQUIVO")
	private String nomearq;

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

	public CursosEntity getNumCurso() {
		return numCurso;
	}

	public void setNumCurso(CursosEntity numCurso) {
		this.numCurso = numCurso;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getNomearq() {
		return nomearq;
	}

	public void setNomearq(String nomearq) {
		this.nomearq = nomearq;
	}

}
