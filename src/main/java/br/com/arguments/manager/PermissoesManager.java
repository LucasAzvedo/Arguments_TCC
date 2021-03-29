
package br.com.arguments.manager;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.arguments.service.UsuarioService;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.TipoUsuarioEntity;
import br.com.arguments.entity.UsuarioEntity;
import br.com.arguments.util.jsf.SessionUtil;

@ManagedBean
@ViewScoped
public class PermissoesManager implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(PermissoesManager.class.getName());
	
	private static final String ERRO_01 = "ERRO";
	
	private static final String SUCESSO_01 = "SUCESSO";

	private LoginEntity login;
	
	private UsuarioEntity user;
	
	private List<UsuarioEntity> listaUsuario;
	
	private Long userSelected;
	
	private TipoUsuarioEntity tipoUsuario;
	
	private UsuarioEntity usuarioEditado;
	
	private Long idTipoUsuarioSelected;
	
	private List<TipoUsuarioEntity> listaTipoUsuario;
	
	@EJB
	private UsuarioService usuarioService;
	
	@PostConstruct
	public void init() {
		login = (LoginEntity) SessionUtil.getParam("UserLoged");
		user = login.getIdUsuario();
		posInit();
	}
	
	private void posInit(){
		idTipoUsuarioSelected = null;
		usuarioEditado = null;
		tipoUsuario = null;
		userSelected = null;
		listaUsuario = null;
	}
	
	public void novaPermissao(UsuarioEntity usuario){
		usuarioEditado = usuario;
		idTipoUsuarioSelected = usuario.getTipoUsuario().getId();
	}
	
	public void salvarPermissao(){
		FacesContext context = FacesContext.getCurrentInstance();
		if(usuarioEditado != null){
			for(TipoUsuarioEntity item : listaTipoUsuario){
				if(item.getId() == idTipoUsuarioSelected){
					usuarioService.updatePermissao(usuarioEditado,item);
				}
			}
			posInit();
			LOG.info(SUCESSO_01 + ": Permissão alterada com sucesso.");
			context.addMessage(null, new FacesMessage(SUCESSO_01 + ": Permissão alterada com sucesso."));
		} else {
			LOG.info(ERRO_01 + ": Selecione um usuário.");
			context.addMessage(null, new FacesMessage(ERRO_01 + ": Selecione um usuário."));
		}
		
	}
	
	public void ativarUsuario(UsuarioEntity usuario){
		FacesContext context = FacesContext.getCurrentInstance();
		if(usuario != null){
			if(usuarioService.ativeUser(usuario)){
				LOG.info(SUCESSO_01 + ": Usuario ativado.");
				context.addMessage(null, new FacesMessage(SUCESSO_01 + ": Usuario ativado."));
			}
		}
		posInit();
	}
	
	public void desativarUsuario(UsuarioEntity usuario){
		FacesContext context = FacesContext.getCurrentInstance();
		if(usuario != null){
			if(usuarioService.inativeUser(usuario)){
				LOG.info(SUCESSO_01 + ": Usuario inativado.");
				context.addMessage(null, new FacesMessage(SUCESSO_01 + ": Usuario desativado."));
			}
		}
		posInit();
	}
	
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

	public List<UsuarioEntity> getListaUsuario() {
		if(listaUsuario == null){
			listaUsuario = usuarioService.findAllUsers();
			if(listaUsuario != null){
				for(int x = 0; x < listaUsuario.size(); x++){
					if(user.getId().equals(listaUsuario.get(x).getId())){
						if(user.getTipoUsuario().getId().equals(new Long(2))){
							listaUsuario.remove(x);
							x--;
						}
					}
				}
			}
		}
		return listaUsuario;
	}

	public void setListaUsuario(List<UsuarioEntity> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Long getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(Long userSelected) {
		this.userSelected = userSelected;
	}

	public TipoUsuarioEntity getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuarioEntity tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public List<TipoUsuarioEntity> getListaTipoUsuario() {
		if(listaTipoUsuario == null){
			listaTipoUsuario = usuarioService.findAllTipoUsuario();
		}
		return listaTipoUsuario;
	}

	public void setListaTipoUsuario(List<TipoUsuarioEntity> listaTipoUsuario) {
		this.listaTipoUsuario = listaTipoUsuario;
	}

	public Long getIdTipoUsuarioSelected() {
		return idTipoUsuarioSelected;
	}

	public void setIdTipoUsuarioSelected(Long idTipoUsuarioSelected) {
		this.idTipoUsuarioSelected = idTipoUsuarioSelected;
	}

	public UsuarioEntity getUsuarioEditado() {
		return usuarioEditado;
	}

	public void setUsuarioEditado(UsuarioEntity usuarioEditado) {
		this.usuarioEditado = usuarioEditado;
	}

	
}
