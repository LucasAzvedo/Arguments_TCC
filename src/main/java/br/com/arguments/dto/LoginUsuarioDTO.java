package br.com.arguments.dto;

import br.com.arguments.entity.InstituicaoCursosEntity;
import br.com.arguments.entity.TipoUsuarioEntity;

public class LoginUsuarioDTO {

	private String nome;
	
	private String sobrenome;
	
	private String email;
	
	private Long ra;
	
	private String senha;
	
	private String confirmacaoSenha;
	
	private InstituicaoCursosEntity instituicaoCurso;
	
	private TipoUsuarioEntity tipoUsuario;
	
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}
	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
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
