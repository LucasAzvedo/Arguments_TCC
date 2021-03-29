package br.com.arguments.dto;

import br.com.arguments.entity.InstituicaoCursosEntity;
import br.com.arguments.entity.TipoUsuarioEntity;

public class UsuarioDTO {
	
	private Long id;
	
	private String nome;
	
	private String sobrenome;
	
	private String email;
	
	private Long ra;
	
	private String baseFile;
	
	private InstituicaoCursosEntity instituicaoCurso;
	
	private TipoUsuarioEntity tipoUsuario;

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

	public String getBaseFile() {
		return baseFile;
	}

	public void setBaseFile(String baseFile) {
		this.baseFile = baseFile;
	}

	public InstituicaoCursosEntity getInstituicaoCurso() {
		return instituicaoCurso;
	}

	public void setInstituicaoCurso(InstituicaoCursosEntity instituicaoCurso) {
		this.instituicaoCurso = instituicaoCurso;
	}

	public TipoUsuarioEntity getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuarioEntity tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
