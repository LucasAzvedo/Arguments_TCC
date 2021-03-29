package br.com.arguments.autentication;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.arguments.entity.LoginEntity;
import br.com.arguments.exception.UnexpectedException;
import br.com.arguments.service.LoginService;
import br.com.arguments.util.jsf.SessionUtil;
import br.com.arguments.util.jsf.criptografia;

@ManagedBean
@ViewScoped
public class LoginManager implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(LoginManager.class.getName());
	
	private static final String ERRO_01 = "ERRO";
	
	private static final String ERRO_02 = "ERRO INESPERADO";

	private String login;

	private String senha;
	
	@EJB
	private LoginService loginService;
	
	private LoginEntity loginEntity;

	public String autentica() throws UnexpectedException {
		
		FacesContext context = FacesContext.getCurrentInstance();

		LOG.info(" ===== INICIA METODO LOGIN ===== ");
		
		 try {
			
			 if(login != null){				 
				 loginEntity = new LoginEntity();
				 loginEntity = loginService.findLogin(login);
				 
				 if(loginEntity != null){
				
					 if(loginEntity.getSenha().equals(criptografia.md5(senha))){
//					 if(loginEntity.getSenha().equals(senha)){
						 
						 SessionUtil.setParam("UserLoged", loginEntity);
						 return "linha.xhtml?faces-redirect=true";
					 }
					 
				 } 
				 
			 } 
			 LOG.warning(ERRO_01 + " Usuário inválido! ");
			 context.addMessage(null, new FacesMessage(ERRO_01, "Usuário inválido.") );
			 return null;
			 
		} catch (Exception e) {
			LOG.warning(ERRO_02 + e.getMessage());
			context.addMessage(null, new FacesMessage(ERRO_02, ": " + e.getMessage()) );			
            throw new UnexpectedException(e.getMessage());
		}
		
	}

	/**
	 * Método que efetua o logout
	 * 
	 * @return
	 */
	public String registraSaida() {
		/** REMOVER USUARIO DA SESSION */
		return "/Login?faces-redirect=true";
	}

	/** GETTERS E SETTERS */

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
