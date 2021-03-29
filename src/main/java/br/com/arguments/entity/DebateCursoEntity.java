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
@Table(name="DEBATE_CURSO")
public class DebateCursoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_DEBATE_CURSO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_DEBATE")
	private DebateEntity idDebateEntity;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_USUARIO")
	private UsuarioEntity idUsuarioEntity;
	
	@Column(name="COMENTARIO")
	private String comentario;
	
	@Column(name = "DATA_CRIACAO")
    private Timestamp dataCriacao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DebateEntity getIdDebateEntity() {
		return idDebateEntity;
	}

	public void setIdDebateEntity(DebateEntity idDebateEntity) {
		this.idDebateEntity = idDebateEntity;
	}

	public UsuarioEntity getIdUsuarioEntity() {
		return idUsuarioEntity;
	}

	public void setIdUsuarioEntity(UsuarioEntity idUsuarioEntity) {
		this.idUsuarioEntity = idUsuarioEntity;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Timestamp getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Timestamp dataCriacao) {
		this.dataCriacao = dataCriacao;
	}	



	
}
