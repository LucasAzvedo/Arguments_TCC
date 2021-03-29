package br.com.arguments.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name="DEBATE")
public class DebateEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_DEBATE")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NOME_DEBATE")
	private String nome;
	
	@Column(name="TEMA_DEBATE")
	private String tema;
	
	@Column(name="ASSUNTO_DEBATE")
	private String assunto;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_CURSO")
	private CursosEntity idCurso;
	
	@Column(name="DATA_ABERTURA")
	private Timestamp dataAbertura;
	
	@Column(name="DATA_FECHAMENTO")
	private Timestamp dataFechamento;
	
	@Column(name = "DATA_CRIACAO")
    private Timestamp dataCriacao;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_USUARIO")
	private UsuarioEntity idUsuarioEntity;
	
	@Column(name="STATUS") // 1-Criado 2-Finalizado
	private int status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public Timestamp getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Timestamp dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Timestamp getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Timestamp dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public UsuarioEntity getIdUsuarioEntity() {
		return idUsuarioEntity;
	}

	public void setIdUsuarioEntity(UsuarioEntity idUsuarioEntity) {
		this.idUsuarioEntity = idUsuarioEntity;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public CursosEntity getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(CursosEntity idCurso) {
		this.idCurso = idCurso;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Timestamp getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Timestamp dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
}
