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
@Table(name = "TRABALHO_USUARIO")
public class TrabalhoUsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_TRABALHO_USUARIO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_USUARIO")
	private UsuarioEntity usuario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TRABALHO")
	private TrabalhoEntity trabalho;

	@Column(name = "CURTIR")
	private Boolean curtir;

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

	public TrabalhoEntity getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(TrabalhoEntity trabalho) {
		this.trabalho = trabalho;
	}

	public Boolean getCurtir() {
		return curtir;
	}

	public void setCurtir(Boolean curtir) {
		this.curtir = curtir;
	}

}
