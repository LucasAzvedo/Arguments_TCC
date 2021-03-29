package br.com.arguments.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="USUARIO")
public class UsuarioEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_USUARIO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_TIPO_USUARIO")
	private TipoUsuarioEntity tipoUsuario;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_INSTITUICAO_CURSOS")
	private InstituicaoCursosEntity idInstituicaoCursos;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="SOBRENOME")
	private String sobrenome;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="RA")
	private Long ra;
	
	@Lob 
	@Column(name = "BASE_FILE", nullable = true) 
	private String baseFile;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoUsuarioEntity getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuarioEntity tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getRa() {
		return ra;
	}

	public void setRa(Long ra) {
		this.ra = ra;
	}

	public InstituicaoCursosEntity getIdInstituicaoCursos() {
		return idInstituicaoCursos;
	}

	public void setIdInstituicaoCursos(InstituicaoCursosEntity idInstituicaoCursos) {
		this.idInstituicaoCursos = idInstituicaoCursos;
	}

	public String getBaseFile() {
		return baseFile;
	}

	public void setBaseFile(String baseFile) {
		this.baseFile = baseFile;
	}
	
}
