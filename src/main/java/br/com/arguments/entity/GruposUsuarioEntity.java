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
@Table(name="GRUPOS_USUARIO")
public class GruposUsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_GRUPO_USUARIO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_USUARIO")
	private UsuarioEntity usuario;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_GRUPOS")
	private GruposEntity grupo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public GruposEntity getGrupo() {
		return grupo;
	}

	public void setGrupo(GruposEntity grupo) {
		this.grupo = grupo;
	}

}
