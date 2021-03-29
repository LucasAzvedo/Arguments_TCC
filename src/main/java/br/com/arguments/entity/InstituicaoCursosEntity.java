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
@Table(name="INSTITUICAO_CURSOS")
public class InstituicaoCursosEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_INSTITUICAO_CURSOS")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_INSTITUICAO")
	private InstituicaoEntity idInstituicaoEntity;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_CURSOS")
	private CursosEntity idCursosEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InstituicaoEntity getIdInstituicaoEntity() {
		return idInstituicaoEntity;
	}

	public void setIdInstituicaoEntity(InstituicaoEntity idInstituicaoEntity) {
		this.idInstituicaoEntity = idInstituicaoEntity;
	}

	public CursosEntity getIdCursosEntity() {
		return idCursosEntity;
	}

	public void setIdCursosEntity(CursosEntity idCursosEntity) {
		this.idCursosEntity = idCursosEntity;
	}
	
}
