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
@Table(name="TIPO_CONTEUDO_DEBATE")
public class TipoConteudoDebateEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TIPO_CONTEUDO_DEBATE")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_DEBATE")
    private DebateEntity debate;
	
	@Column(name="NOME_DEBATE")
	private String nome;
	
	@Column(name="TEMA_DEBATE")
	private String tema;
	
	@Column(name="DATA_ABERTURA")
	private Timestamp dataAbertura;
	
	@Column(name="DATA_FECHAMENTO")
	private Timestamp dataFechamento;
	
//    @ManyToOne(fetch=FetchType.EAGER)
//    @JoinColumn(name="ID_USUARIO")
//    private UsuarioEntity usuario;

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

//	public UsuarioEntity getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(UsuarioEntity usuario) {
//		this.usuario = usuario;
//	}

	public DebateEntity getDebate() {
		return debate;
	}

	public void setDebate(DebateEntity debate) {
		this.debate = debate;
	}
	
}
