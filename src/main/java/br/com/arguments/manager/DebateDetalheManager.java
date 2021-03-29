package br.com.arguments.manager;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.arguments.entity.DebateCursoEntity;
import br.com.arguments.entity.DebateEntity;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.UsuarioEntity;
import br.com.arguments.service.DebateService;
import br.com.arguments.util.jsf.SessionUtil;

@ManagedBean
@ViewScoped
public class DebateDetalheManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private LoginEntity login;

	private UsuarioEntity user;
	
	private DebateEntity debate;
	
	private List<DebateCursoEntity> listaDebate;
	
	private int qtdComentarios;
	
	private int qtdUsuarios;
	
	@EJB
	private DebateService debateService;
	
	@PostConstruct
	public void init() {
		login = (LoginEntity) SessionUtil.getParam("UserLoged");
		user = login.getIdUsuario();
		debate = (DebateEntity) SessionUtil.getParam("DebateSelecionado");
		qtdComentarios = 0;
		qtdUsuarios = 0;
		
		if(debate != null){
			atualizaInit();
		}
		
	}
	
	private void atualizaInit(){
		listaDebate = debateService.findAllDebatesByDebate(debate);
		qtdComentarios = listaDebate.size();
		
		HashMap<String, Integer> map = new HashMap<>();
		
		for(DebateCursoEntity item : listaDebate){
			
			if(!map.containsKey(item.getIdUsuarioEntity().getId().toString())){
				map.put(item.getIdUsuarioEntity().getId().toString(), new Integer(1));
			}
			
		}
		qtdUsuarios = new ArrayList<>(map.values()).size();
	}
	
	public void comentarDetalhe(String comentario) {
		
		DebateCursoEntity entity = debateService.insertComentarioDebate(user,debate,comentario);
		
		if(debate != null){
			atualizaInit();
		}
		
	}
	
	public String convertDateToString(Date data) {
		if (data != null) {
			SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
			return formatado.format(data);
		}
		return null;
	}
	
	public String convertDateToStringHora(Date data) {
		if (data != null) {
			SimpleDateFormat formatado = new SimpleDateFormat("HH:mm");
			return formatado.format(data);
		}
		return null;
	}
	
	public String convertDateToStringFull(Date data) {
		if (data != null) {
			SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return formatado.format(data);
		}
		return null;
	}
	
	
	/** GETTERS E SETTERS */

	public LoginEntity getLogin() {
		return login;
	}

	public void setLogin(LoginEntity login) {
		this.login = login;
	}

	public UsuarioEntity getUser() {
		return user;
	}

	public void setUser(UsuarioEntity user) {
		this.user = user;
	}

	public DebateEntity getDebate() {
		return debate;
	}

	public void setDebate(DebateEntity debate) {
		this.debate = debate;
	}

	public List<DebateCursoEntity> getListaDebate() {
		return listaDebate;
	}

	public void setListaDebate(List<DebateCursoEntity> listaDebate) {
		this.listaDebate = listaDebate;
	}

	public int getQtdComentarios() {
		return qtdComentarios;
	}

	public void setQtdComentarios(int qtdComentarios) {
		this.qtdComentarios = qtdComentarios;
	}

	public int getQtdUsuarios() {
		return qtdUsuarios;
	}

	public void setQtdUsuarios(int qtdUsuarios) {
		this.qtdUsuarios = qtdUsuarios;
	}

}
