package br.com.arguments.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.arguments.dto.LoginUsuarioDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.InstituicaoCursosEntity;
import br.com.arguments.entity.InstituicaoEntity;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.TipoUsuarioEntity;
import br.com.arguments.exception.UnexpectedException;
import br.com.arguments.service.CadastroService;
import br.com.arguments.service.InstituicaoService;
import br.com.arguments.service.LoginService;
import br.com.arguments.service.TipoUsuarioService;

@ManagedBean
@ViewScoped
public class CadastroManager implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(CadastroManager.class.getName());
	
	private static final String ERRO_01 = "ERRO";
	
	private static final String ERRO_02 = "ERRO INESPERADO";

	private boolean termo;
	
	@EJB
	private LoginService loginService;
	
	@EJB
	private CadastroService cadastroService;
	
	@EJB
	private InstituicaoService instituicaoService;
	
	@EJB
	private TipoUsuarioService tipoUsuarioService;
	
	private LoginUsuarioDTO dto;
			
	private Integer cursoSelecionado;
	
	private List<CursosEntity> listaCursos;
	
	private Integer instituicaoSelecionada;
	
	private List<InstituicaoEntity> listaInstituicao;
	
	private List<InstituicaoCursosEntity> listaInstituicaoCursos;
	
	private List<TipoUsuarioEntity> listaTipoUsuario;
	
	private Integer tipoUsuarioSelecionado;
	
	@PostConstruct
	public void init(){
		dto = new LoginUsuarioDTO();
		listaInstituicao = instituicaoService.findAllInstituicao();
		
		List<TipoUsuarioEntity> ls = new ArrayList<>();
		
		ls = tipoUsuarioService.findAllTipoUsuario();
		
		listaTipoUsuario = new ArrayList<>();
		
		listaTipoUsuario.add(ls.get(0));
		
	}
	
	public String cadastro() throws UnexpectedException {
		
		FacesContext context = FacesContext.getCurrentInstance();

		LOG.info(" ===== INICIA METODO CADASTRO ===== ");
		
		 try {
			 if(!termo){
				 LOG.warning(ERRO_01 + " Termo não aceito! ");
				 context.addMessage(null, new FacesMessage(ERRO_01, "Termo não aceito.") );
				 return null; 
			 }
			 if(dto.getSenha() != dto.getConfirmacaoSenha()){
				 
				 if(validaCampos()){
					 
					 LoginEntity login = new LoginEntity();
					 buscaCursoInstutuicao();
					 buscaTipousuario();
					 login = cadastroService.insert(dto);
					 
					 return "login.xhtml?faces-redirect=true";
					 
				 }
				 
			 } else {
				 LOG.warning(ERRO_01 + " Senhas diferentes! ");
				 context.addMessage(null, new FacesMessage(ERRO_01, "Senhas diferentes!.") );
				 return null;
			 }
			 
			 LOG.warning(ERRO_01 + " Cadastro inválido! ");
			 context.addMessage(null, new FacesMessage(ERRO_01, "Cadastro inválido.") );
			 return null;
			 
		} catch (Exception e) {
			LOG.warning(ERRO_02 + e.getMessage());
			context.addMessage(null, new FacesMessage(ERRO_02, ": " + e.getMessage()) );
            throw new UnexpectedException(e.getMessage());
		}
		
	}
	
	private void buscaTipousuario() {
		for(TipoUsuarioEntity item : listaTipoUsuario){
			if(item.getId() == tipoUsuarioSelecionado.longValue()){
				dto.setTipoUsuario(item);
			}
		}
	}

	public void buscaCursosInstituicao(){
		for(InstituicaoEntity item : listaInstituicao){
			if(item.getId() == instituicaoSelecionada.longValue()){
				listaInstituicaoCursos = instituicaoService.findCursosByInstituicao(item);
				if(listaInstituicaoCursos != null){
					listaCursos = new ArrayList<>();
					for(InstituicaoCursosEntity it : listaInstituicaoCursos){
						listaCursos.add(it.getIdCursosEntity());
					}
				}
			}
		}
	}
	
	public void buscaCursoInstutuicao(){
		for(InstituicaoCursosEntity item : listaInstituicaoCursos){
			if(item.getIdCursosEntity().getId() == cursoSelecionado.longValue() &&
					item.getIdInstituicaoEntity().getId() == instituicaoSelecionada.longValue()){
				dto.setInstituicaoCurso(item);
			}
		}
	}

	/**
	 * Método que efetua o logout
	 * 
	 * @return
	 */
	public boolean validaCampos() {
		if((dto.getNome() != null && !dto.getNome().isEmpty()) || 
		   (dto.getSobrenome() != null || !dto.getSobrenome().isEmpty()) || 
		   (dto.getEmail() != null && !dto.getEmail().isEmpty()) || 
		   dto.getRa() != null || 
		   (dto.getSenha() != null && dto.getSenha().isEmpty()) || 
		   (dto.getConfirmacaoSenha() != null && !dto.getConfirmacaoSenha().isEmpty())){
			return true;
		}
		return false;
	}
	
	public void addMessage() {
        String summary = termo ? "Checked" : "Unchecked";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }
	
	/** GETTERS E SETTERS */

	public boolean isTermo() {
		return termo;
	}

	public void setTermo(boolean termo) {
		this.termo = termo;
	}

	public CadastroService getCadastroService() {
		return cadastroService;
	}

	public void setCadastroService(CadastroService cadastroService) {
		this.cadastroService = cadastroService;
	}

	public LoginUsuarioDTO getDto() {
		return dto;
	}

	public void setDto(LoginUsuarioDTO dto) {
		this.dto = dto;
	}

	public List<CursosEntity> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<CursosEntity> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public List<InstituicaoEntity> getListaInstituicao() {
		return listaInstituicao;
	}

	public void setListaInstituicao(List<InstituicaoEntity> listaInstituicao) {
		this.listaInstituicao = listaInstituicao;
	}

	public Integer getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Integer cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public Integer getInstituicaoSelecionada() {
		return instituicaoSelecionada;
	}

	public void setInstituicaoSelecionada(Integer instituicaoSelecionada) {
		this.instituicaoSelecionada = instituicaoSelecionada;
	}

	public List<InstituicaoCursosEntity> getListaInstituicaoCursos() {
		return listaInstituicaoCursos;
	}

	public void setListaInstituicaoCursos(List<InstituicaoCursosEntity> listaInstituicaoCursos) {
		this.listaInstituicaoCursos = listaInstituicaoCursos;
	}

	public List<TipoUsuarioEntity> getListaTipoUsuario() {
		return listaTipoUsuario;
	}

	public void setListaTipoUsuario(List<TipoUsuarioEntity> listaTipoUsuario) {
		this.listaTipoUsuario = listaTipoUsuario;
	}

	public Integer getTipoUsuarioSelecionado() {
		return tipoUsuarioSelecionado;
	}

	public void setTipoUsuarioSelecionado(Integer tipoUsuarioSelecionado) {
		this.tipoUsuarioSelecionado = tipoUsuarioSelecionado;
	}

}
