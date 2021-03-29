package br.com.arguments.manager;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.arguments.service.CadastroService;
import br.com.arguments.service.LoginService;
import br.com.arguments.service.TipoUsuarioService;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.TipoUsuarioEntity;
import br.com.arguments.util.jsf.SessionUtil;

@ManagedBean
@SessionScoped
public class TemplateManager implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(TemplateManager.class.getName());
	
	private static final String ERRO_01 = "ERRO";
	
	private static final String ERRO_02 = "ERRO INESPERADO";

	@EJB
	private LoginService loginService;
	
	@EJB
	private CadastroService cadastroService;
	
	@EJB
	private TipoUsuarioService tipoUsuarioService;
	
	private LoginEntity user;
	
	private String nome;
	
	private String tipoUsuario;
	
	@PostConstruct
	public void init(){
		user = (LoginEntity) SessionUtil.getParam("UserLoged");
		nome = user.getIdUsuario().getNome() + " " + user.getIdUsuario().getSobrenome();
//		tipoUsuario = findTipoUsuario(user.getIdUsuario().getTipoUsuario().getId());
		tipoUsuario = user.getIdUsuario().getTipoUsuario().getNome();
//		this.isProfessor = isProfessor();
	}
	
//	private String findTipoUsuario(Long id){
//		TipoUsuarioEntity tp = new TipoUsuarioEntity();
//		tp = tipoUsuarioService.findId(id);
//		return tp.getNome();
//	}
	
	public String signOut(){
		SessionUtil.setParam("UserLoged", null);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login.xhtml?faces-redirect=true";
	}
	
	public boolean getIsProfessor(){
		if((user.getIdUsuario().getTipoUsuario().getId() == 2) ||
				(user.getIdUsuario().getTipoUsuario().getId() == 3)){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getIsEspecialista(){
		if(user.getIdUsuario().getTipoUsuario().getId() == 3){
			return true;
		} else {
			return false;
		}
	}
	
	/** GETTERS E SETTERS */

	public CadastroService getCadastroService() {
		return cadastroService;
	}

	public void setCadastroService(CadastroService cadastroService) {
		this.cadastroService = cadastroService;
	}

	public LoginEntity getUser() {
		return user;
	}

	public void setUser(LoginEntity user) {
		this.user = user;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
