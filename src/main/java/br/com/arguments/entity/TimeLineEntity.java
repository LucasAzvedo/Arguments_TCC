package br.com.arguments.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TIME_LINE")
public class TimeLineEntity implements Serializable {

	@Id
	@Column(name="ID_TIME_LINE")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="TIPO_CONTEUDO")
	private int tipoConteudo;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_TIPO_CONTEUDO_EVENTO")
	private TipoConteudoEventoEntity idTipoConteudoEvento;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_TIPO_CONTEUDO_DEBATE")
	private TipoConteudoDebateEntity idTipoConteudoDebate;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_TIPO_CONTEUDO_GRUPO")
	private TipoConteudoGrupoEntity idTipoConteudoGrupo;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_TIPO_CONTEUDO_TRABALHO")
	private TipoConteudoTrabalhoEntity idTipoConteudoTrabalho;
	
	@Column(name="ID_CURSO", nullable=true)
	private int idCurso;
	
    @Column(name = "DATA_CRIACAO")
    private Timestamp dataCriacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTipoConteudo() {
		return tipoConteudo;
	}

	public void setTipoConteudo(int tipoConteudo) {
		this.tipoConteudo = tipoConteudo;
	}

	public int getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public Timestamp getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Timestamp dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public TipoConteudoEventoEntity getIdTipoConteudoEvento() {
		return idTipoConteudoEvento;
	}

	public void setIdTipoConteudoEvento(TipoConteudoEventoEntity idTipoConteudoEvento) {
		this.idTipoConteudoEvento = idTipoConteudoEvento;
	}

	public TipoConteudoDebateEntity getIdTipoConteudoDebate() {
		return idTipoConteudoDebate;
	}

	public void setIdTipoConteudoDebate(TipoConteudoDebateEntity idTipoConteudoDebate) {
		this.idTipoConteudoDebate = idTipoConteudoDebate;
	}

	public TipoConteudoGrupoEntity getIdTipoConteudoGrupo() {
		return idTipoConteudoGrupo;
	}

	public void setIdTipoConteudoGrupo(TipoConteudoGrupoEntity idTipoConteudoGrupo) {
		this.idTipoConteudoGrupo = idTipoConteudoGrupo;
	}

	public TipoConteudoTrabalhoEntity getIdTipoConteudoTrabalho() {
		return idTipoConteudoTrabalho;
	}

	public void setIdTipoConteudoTrabalho(TipoConteudoTrabalhoEntity idTipoConteudoTrabalho) {
		this.idTipoConteudoTrabalho = idTipoConteudoTrabalho;
	}
	
}
