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
@Table(name="TIPO_CONTEUDO_TRABALHO")
public class TipoConteudoTrabalhoEntity implements Serializable{
	
	@Id
	@Column(name="ID_TIPO_CONTEUDO_TRABALHO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="NOME_TRABALHO")
	private String nome;
	
	@Column(name="DESCRICAO", nullable=true)
	private String descricao;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="NUM_CURSO")
	private CursosEntity numCurso;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_TRABALHO")
    private TrabalhoEntity trabalho;

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

	public TrabalhoEntity getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(TrabalhoEntity trabalho) {
		this.trabalho = trabalho;
	}

}
