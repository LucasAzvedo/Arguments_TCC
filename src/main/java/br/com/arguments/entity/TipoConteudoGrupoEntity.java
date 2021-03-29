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
@Table(name="TIPO_CONTEUDO_GRUPO")
public class TipoConteudoGrupoEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TIPO_CONTEUDO_GRUPO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_GRUPO")
    private GruposEntity grupo;
	
	@Column(name="NOME_GRUPO")
	private String nomeGrupo;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_CURSO")
	private CursosEntity curso;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_INSTITUICAO", nullable=true)
	private InstituicaoEntity instituicao;
	
	@Column(name="DESCRICAO_GRUPO")
	private String descricaoGrupo;
	
	@Column(name="TIPO_GRUPO")
	private int tipoGrupo; // 1 - privado; 2 - publico
	
	@Column(name="QTD_MAX_MEMBROS")
	private int qtdMaximaMembros;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_USUARIO")
	private UsuarioEntity usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GruposEntity getGrupo() {
		return grupo;
	}

	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public CursosEntity getCurso() {
		return curso;
	}

	public InstituicaoEntity getInstituicao() {
		return instituicao;
	}

	public String getDescricaoGrupo() {
		return descricaoGrupo;
	}

	public int getTipoGrupo() {
		return tipoGrupo;
	}

	public int getQtdMaximaMembros() {
		return qtdMaximaMembros;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setGrupo(GruposEntity grupo) {
		this.grupo = grupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}

	public void setCurso(CursosEntity curso) {
		this.curso = curso;
	}

	public void setInstituicao(InstituicaoEntity instituicao) {
		this.instituicao = instituicao;
	}

	public void setDescricaoGrupo(String descricaoGrupo) {
		this.descricaoGrupo = descricaoGrupo;
	}

	public void setTipoGrupo(int tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	public void setQtdMaximaMembros(int qtdMaximaMembros) {
		this.qtdMaximaMembros = qtdMaximaMembros;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

}
