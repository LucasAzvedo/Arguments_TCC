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
@Table(name="GRUPOS")
public class GruposEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_GRUPO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NOME_GRUPO")
	private String nome;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_CURSO")
	private CursosEntity curso;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_INSTITUICAO", nullable=true)
	private InstituicaoEntity instituicao;
	
	@Column(name="TIPO_GRUPO")
	private int tipoGrupo; // 1 - privado; 2 - publico
	
	@Column(name="QTD_MAX_MEMBROS")
	private int qtdMaximaMembros;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_USUARIO")
	private UsuarioEntity usuario;
	
    @Column(name = "DATA_CRIACAO")
    private Timestamp dataCriacao;
    
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

	public CursosEntity getCurso() {
		return curso;
	}

	public void setCurso(CursosEntity curso) {
		this.curso = curso;
	}

	public int getTipoGrupo() {
		return tipoGrupo;
	}

	public void setTipoGrupo(int tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	public int getQtdMaximaMembros() {
		return qtdMaximaMembros;
	}

	public void setQtdMaximaMembros(int qtdMaximaMembros) {
		this.qtdMaximaMembros = qtdMaximaMembros;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public Timestamp getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Timestamp dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public InstituicaoEntity getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoEntity instituicao) {
		this.instituicao = instituicao;
	}

}
