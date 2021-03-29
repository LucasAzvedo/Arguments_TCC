package br.com.arguments.manager;

import java.io.Serializable;
import java.util.Base64;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import br.com.arguments.dto.UsuarioDTO;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.UsuarioEntity;
import br.com.arguments.service.LoginService;
import br.com.arguments.service.UsuarioService;
import br.com.arguments.util.jsf.SessionUtil;
import br.com.arguments.util.jsf.criptografia;

@ManagedBean
@ViewScoped
public class PerfilManager implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(PerfilManager.class.getName());

	private static final String ERRO_01 = "ERRO";

	private static final String ERRO_02 = "ERRO INESPERADO";
	
	@EJB
	private UsuarioService usuarioService;

	private UsuarioDTO dto;

	private LoginEntity user;
	
	private boolean verifica;	
	
	@EJB
	private LoginService loginService;
	
	private LoginEntity loginEntity;
	
	private Long selectedarea;
	
	private String novasenha; 
	
	private String novaSenhaBd;
	
	private String novaSenhaconf;
	
	private String senha;
	
	private String senhaBd;
	
	private String senhaconf;
	
	private String usuario;
	
	private UsuarioEntity  idUsuario;
	
	private UploadedFile file;
	
	@PostConstruct
	public void init() {
		dto = new UsuarioDTO();
		idUsuario = new UsuarioEntity();
		loginEntity = new LoginEntity();
		user = (LoginEntity) SessionUtil.getParam("UserLoged");
		senha = user.getSenha();
		senhaBd = user.getSenha();
		senhaconf= user.getSenha();
		selectedarea = new Long(user.getIdUsuario().getId());
		idUsuario = user.getIdUsuario();
		usuario = user.getUsuario();
		populaDTO(user.getIdUsuario());
	}
	
	private void populaDTO(UsuarioEntity user){		
		dto.setNome(user.getNome());
		dto.setSobrenome(user.getSobrenome());
		dto.setEmail(user.getEmail());
		dto.setRa(user.getRa());
		dto.setBaseFile(user.getBaseFile());
	}
	
	public void Altera(){
		UsuarioEntity user = new UsuarioEntity();
		
		user.setId(this.user.getIdUsuario().getId());
		user.setNome(dto.getNome());
		user.setSobrenome(dto.getSobrenome());
		user.setRa(dto.getRa());
		user.setEmail(dto.getEmail());
		
//		if(file != null){
//			String bytesEncoded = Base64.getEncoder().encodeToString(file.getContents());
//			dto.setBaseFile(bytesEncoded);
//		}
		
//		user.setId(dto.getId());
//		user.setNome(dto.getNome());
//		user.setEmail(dto.getEmail());
//		user.setSobrenome(dto.getSobrenome());
//		user.setRa(dto.getRa());
		
//		loginEntity.setIdUsuario(idUsuario);
		loginEntity.setId(this.user.getId());		
		loginEntity.setUsuario(this.user.getUsuario());
		//loginEntity.setIdUsuario(user);
		
		if(dto != null){
			
			if(senha.equals(criptografia.md5(novasenha))){
				if(novaSenhaBd.equals(novaSenhaconf)){
					loginEntity.setSenha(criptografia.md5(novaSenhaBd)); 
					usuarioService.update(user);
					loginService.update(loginEntity);
				}else{
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Senhas diferentes"));
				}
				
				loginEntity = loginService.findById(loginEntity.getId());
				SessionUtil.setParam("UserLoged", loginEntity);
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Cadastro Atualizado com Sucesso"));
			}else{
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Senha atual inválida!"));
			}
		}
		
//		if(!senha.equals(senhaBd)){
//			loginEntity.setSenha(criptografia.md5(senha));
//			loginService.update(loginEntity);
//		}		
		
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public boolean isVerifica() {
		return verifica;
	}

	public void setVerifica(boolean verifica) {
		this.verifica = verifica;
	}

	public LoginEntity getLoginEntity() {
		return loginEntity;
	}

	public void setLoginEntity(LoginEntity loginEntity) {
		this.loginEntity = loginEntity;
	}

	public Long getSelectedarea() {
		return selectedarea;
	}

	public void setSelectedarea(Long selectedarea) {
		this.selectedarea = selectedarea;
	}

	public String getSenhaBd() {
		return senhaBd;
	}

	public void setSenhaBd(String senhaBd) {
		this.senhaBd = senhaBd;
	}

	public String getSenhaconf() {
		return senhaconf;
	}

	public void setSenhaconf(String senhaconf) {
		this.senhaconf = senhaconf;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public UsuarioDTO getDto() {
		return dto;
	}

	public void setDto(UsuarioDTO dto) {
		this.dto = dto;
	}

	public String getNovasenha() {
		return novasenha;
	}

	public void setNovasenha(String novasenha) {
		this.novasenha = novasenha;
	}

	public String getNovaSenhaBd() {
		return novaSenhaBd;
	}

	public void setNovaSenhaBd(String novaSenhaBd) {
		this.novaSenhaBd = novaSenhaBd;
	}

	public String getNovaSenhaconf() {
		return novaSenhaconf;
	}

	public void setNovaSenhaconf(String novaSenhaconf) {
		this.novaSenhaconf = novaSenhaconf;
	}

}
