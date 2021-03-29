package br.com.arguments.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import br.com.arguments.dto.TrabalhoDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.TipoConteudoTrabalhoEntity;
import br.com.arguments.entity.TrabalhoEntity;
import br.com.arguments.entity.UsuarioEntity;
import br.com.arguments.service.TimeLineService;
import br.com.arguments.service.TrabalhoService;
import br.com.arguments.util.jsf.SessionUtil;

@ManagedBean
@ViewScoped
public class TrabalhoManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(CadastroManager.class.getName());
	
	private static final String ERRO_01 = "ERRO";
	
	@EJB
	private TrabalhoService trabalhoService;
	
	@EJB
	private TimeLineService timeLineService;
	
	private List<TrabalhoEntity> listTrabalho;
	
	private TrabalhoEntity selectedTrabalho;
	
	private TrabalhoDTO dto;
	
	private boolean edit;
	
	private LoginEntity login;

	private UsuarioEntity user;
	
	private UploadedFile file;	
	
	private String destinonome;
	
	private boolean verifica;
	
	byte[] bytes = new byte[2*1024*1024];
	
	private File dir;
	
	private List<CursosEntity> listaCursos;

	private Integer cursoSelecionado;
	
	@PostConstruct
	public void init() {
		login = (LoginEntity) SessionUtil.getParam("UserLoged");
		user = login.getIdUsuario();
		listaCursos = buscaListaCursos();
		destinonome = "C:\\Develop\\Arguments\\workspace\\tccshow\\src\\main\\Trabalhos\\" + user.getId() + "\\";
		posInit();
	}
	
	private void posInit() {
		dto = new TrabalhoDTO();
		edit = false;
		cursoSelecionado = null;
	}
	
	private List<CursosEntity> buscaListaCursos() {
		return trabalhoService.findAllCursos();
	}
	
	public void cadastrarTrabalho() {
		FacesContext context = FacesContext.getCurrentInstance();
		TrabalhoEntity trabalho = new TrabalhoEntity();
		int read = 0;
		
		if((!dto.getNome().isEmpty() && dto.getNome() != null)){
			if(valida(file)){
				if(!(verifica = (new File(destinonome + "\\").exists()))){
					dir = new File(destinonome + "\\");
					dir.mkdir();
				}
				try(InputStream is = file.getInputstream();
						OutputStream out = new FileOutputStream(destinonome + user.getId() + "_"
								+ ".pdf")){
							
							while((read = is.read(bytes)) != -1){
								out.write(bytes, 0, read);
							}
							
							dto.setCaminho(destinonome);
							dto.setNomearq(user.getId() + "_" + ".pdf");
						}catch(Exception e){
							LOG.warning(ERRO_01 + " Erro ");
							context.addMessage(null, new FacesMessage(ERRO_01, "Erro cath"));
							e.printStackTrace();
						}
				if(cursoSelecionado != null){
					for(CursosEntity item : listaCursos){
						if(item.getId().equals(new Long(cursoSelecionado))){
							dto.setCurso(item);
						}
					}
					
					trabalho = trabalhoService.insert(dto);
					
					timeLineService.insertTrabalho(trabalho, user);
					
					posInit();
					carregaLista();
					context.addMessage(null, new FacesMessage("Sucesso", "Cadastrado com Sucesso"));
					}
			}else{
				LOG.warning(ERRO_01 + " Nenhum curso selecionado! ");
				context.addMessage(null, new FacesMessage(ERRO_01, "Nenhum curso selecionado!"));
			}
		}else{
			LOG.warning(ERRO_01 + " Campos Sem preencher! ");
			context.addMessage(null, new FacesMessage(ERRO_01, "Campos Sem preencher!"));
		}
	}
	
	public String removeTrabalho(){
		TipoConteudoTrabalhoEntity tct = trabalhoService.findTipoConteudoTrabalho(selectedTrabalho);
		trabalhoService.removeTimeLine(tct);
		trabalhoService.removeTipoConteudo(selectedTrabalho);
		trabalhoService.removeTrabalhoUsuario(selectedTrabalho);
		trabalhoService.remove(selectedTrabalho);
		carregaLista();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso", "Trabalho Removido"));
		return "trabalho.xhtml?faces-redirect=true";
	}
	
	public void selecionaTrabalhos(TrabalhoEntity trabalho){
		this.selectedTrabalho = trabalho;
	}
	
	public void editaTrabalho(TrabalhoEntity entity){
		if(entity != null){
			dto = new TrabalhoDTO();
			dto.setId(entity.getId());
			dto.setNome(entity.getNome());
			dto.setDescricao(entity.getDescricao());
			cursoSelecionado = entity.getNumCurso().getId().intValue();
			dto.setAtivo(true);
			edit = true;
		}else{
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("ERRO", "Trabalho em branco"));
		}
	}
	
	public void saveTrabalhoEdit(){
		FacesContext context = FacesContext.getCurrentInstance();
		if(edit){
			if(cursoSelecionado != null){
				for(CursosEntity item : listaCursos){
					if(item.getId().equals(new Long(cursoSelecionado))){
						dto.setCurso(item);
					}
				}
			}
			
			trabalhoService.update(dto);
			posInit();
			carregaLista();
			context.addMessage(null, new FacesMessage("Sucesso" + ": Trabalho alterado."));
		}
	}
	
	private boolean valida(UploadedFile newFile){
		
		final int Tam = 2 * 1024 * 1024;
		Long valTrab = new Long(5);
		UploadedFile val = newFile;
		
		if(val.getSize() > Tam){
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Arquivo muito grande", "O arquivo deve ter o tamanho maximo de 2MB"));
			return false;
		}else{
			if(!val.getFileName().endsWith(".pdf")){
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Tipo de Arquivo Errado", "Somente Arquvios .PDF"));
			return false;
			}			
		}
		return true;
	}
	
	private void carregaLista(){
		listTrabalho = trabalhoService.findAllActive();
	}

	public Integer getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Integer cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public List<CursosEntity> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<CursosEntity> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public UsuarioEntity getUser() {
		return user;
	}

	public void setUser(UsuarioEntity user) {
		this.user = user;
	}

	public LoginEntity getLogin() {
		return login;
	}

	public void setLogin(LoginEntity login) {
		this.login = login;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public TrabalhoDTO getDto() {
		return dto;
	}

	public void setDto(TrabalhoDTO dto) {
		this.dto = dto;
	}

	public TrabalhoEntity getSelectedTrabalho() {
		return selectedTrabalho;
	}

	public void setSelectedTrabalho(TrabalhoEntity selectedTrabalho) {
		this.selectedTrabalho = selectedTrabalho;
	}

	public List<TrabalhoEntity> getListTrabalho() {
		if(listTrabalho == null){
			carregaLista();
		}
		return listTrabalho;
	}

	public void setListTrabalho(List<TrabalhoEntity> listTrabalho) {
		this.listTrabalho = listTrabalho;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
}
