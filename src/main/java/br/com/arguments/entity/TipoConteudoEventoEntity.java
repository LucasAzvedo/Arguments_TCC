package br.com.arguments.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

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
@Table(name="TIPO_CONTEUDO_EVENTO")
public class TipoConteudoEventoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID_TIPO_CONTEUDO_EVENTO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="NOME_EVENTO")
	private String nome;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_EVENTO")
	private EventoEntity evento;
	
	@Column(name="DESCRICAO", nullable=true)
	private String descricao;
	
    @Column(name = "DATA_EVENTO")
    private Timestamp dataInicio;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="ID_USUARIO")
    private UsuarioEntity usuario;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setDataInicio(Timestamp dataInicio) {
		this.dataInicio = dataInicio;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public EventoEntity getEvento() {
		return evento;
	}

	public void setEvento(EventoEntity evento) {
		this.evento = evento;
	}

}
