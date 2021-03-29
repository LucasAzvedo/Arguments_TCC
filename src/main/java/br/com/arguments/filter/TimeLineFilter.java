package br.com.arguments.filter;

import java.sql.Timestamp;
import java.util.List;

import br.com.arguments.entity.TipoConteudoDebateEntity;
import br.com.arguments.entity.TipoConteudoEventoEntity;
import br.com.arguments.entity.TipoConteudoGrupoEntity;
import br.com.arguments.entity.TipoConteudoTrabalhoEntity;

public class TimeLineFilter {
	
	private Timestamp data;
	
	private List<TipoConteudoEventoEntity> listaEvento;
	
	private List<TipoConteudoDebateEntity> listaDebate;
	
	private List<TipoConteudoGrupoEntity> listaGrupo;

	private List<TipoConteudoTrabalhoEntity> listaTrabalho;
	
	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public List<TipoConteudoEventoEntity> getListaEvento() {
		return listaEvento;
	}

	public void setListaEvento(List<TipoConteudoEventoEntity> listaEvento) {
		this.listaEvento = listaEvento;
	}

	public List<TipoConteudoDebateEntity> getListaDebate() {
		return listaDebate;
	}

	public void setListaDebate(List<TipoConteudoDebateEntity> listaDebate) {
		this.listaDebate = listaDebate;
	}

	public List<TipoConteudoGrupoEntity> getListaGrupo() {
		return listaGrupo;
	}

	public void setListaGrupo(List<TipoConteudoGrupoEntity> listaGrupo) {
		this.listaGrupo = listaGrupo;
	}

	public List<TipoConteudoTrabalhoEntity> getListaTrabalho() {
		return listaTrabalho;
	}

	public void setListaTrabalho(List<TipoConteudoTrabalhoEntity> listaTrabalho) {
		this.listaTrabalho = listaTrabalho;
	}

}
