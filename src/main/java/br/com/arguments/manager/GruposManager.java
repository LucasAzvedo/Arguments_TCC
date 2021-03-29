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

import br.com.arguments.dto.GruposDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.GruposEntity;
import br.com.arguments.entity.InstituicaoEntity;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.TipoConteudoGrupoEntity;
import br.com.arguments.entity.UsuarioEntity;
import br.com.arguments.service.GruposService;
import br.com.arguments.service.TimeLineService;
import br.com.arguments.util.jsf.SessionUtil;

@ManagedBean
@ViewScoped
public class GruposManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(GruposEntity.class.getName());

	private static final String ERRO_01 = "ERRO";

	@EJB
	private GruposService gruposService;

	@EJB
	private TimeLineService timeLineService;

	private List<GruposEntity> listaGrupos;

	private GruposDTO gruposDTO;

	private LoginEntity user;

	private boolean edit;

	private GruposEntity gruposEntity;
	
	private GruposEntity selectGrupos;

	private Integer cursoSelecionado;

	private Integer instituicaoSelecionado;

	private List<CursosEntity> listaCursos;

	private List<InstituicaoEntity> listaInstituicao;

	private List<UsuarioEntity> listaAlunos;

	private List<UsuarioEntity> listaAlunosSelecionados;

	@PostConstruct
	public void init() {
		user = (LoginEntity) SessionUtil.getParam("UserLoged");
		cursoSelecionado = null;
		instituicaoSelecionado = null;
		// carregaListaCurso();
		carregaListaGrupos();
		posInit();
	}

	private void posInit() {
		gruposDTO = new GruposDTO();
		edit = false;
	}

	public void cadastrarGrupos() {
		FacesContext context = FacesContext.getCurrentInstance();
		GruposEntity grupo = new GruposEntity();
		if ((!gruposDTO.getNomeGrupo().isEmpty() && gruposDTO.getNomeGrupo() != null)) {
			
			if(gruposDTO.getTipoGrupo() == 1){
				
				grupo = saveGrupos();
				saveTimeLine(grupo);

				if (listaAlunosSelecionados != null) {
					for (UsuarioEntity item : listaAlunosSelecionados) {
						gruposService.insertGruposCurso(grupo, item);
					}
				}

				context.addMessage(null, new FacesMessage("Sucesso", "Cadastrado com Sucesso"));
				posInit();
				cleanVariaveis();

				carregaListaGrupos();
				
			}else{
			
				if ((gruposDTO.getTipoGrupo() == 2 && cursoSelecionado != 0)
						|| (gruposDTO.getTipoGrupo() == 1 && cursoSelecionado == 0)) {
	
					if (cursoSelecionado != null) {
						for (CursosEntity item : listaCursos) {
							if (item.getId().equals(new Long(cursoSelecionado))) {
								gruposDTO.setCurso(item);
							}
						}
					}
	
					if (instituicaoSelecionado != null) {
						for (InstituicaoEntity item : listaInstituicao) {
							if (item.getId().equals(new Long(instituicaoSelecionado))) {
								gruposDTO.setInstituicao(item);
							}
						}
					}
	
					if (listaAlunosSelecionados != null) {
						if (listaAlunosSelecionados.size() > gruposDTO.getQtdMaximaMembros()) {
							LOG.warning(ERRO_01
									+ " Numero de alunos selecionados maior que numero maximo permitido para o grupo! ");
							context.addMessage(null, new FacesMessage(
									"Numero de alunos selecionados maior que numero maximo permitido para o grupo!"));
						} else {
	
							// grupo = gruposService.insert(gruposDTO, user);
							grupo = saveGrupos();
							saveTimeLine(grupo);
	
							if (listaAlunosSelecionados != null) {
								for (UsuarioEntity item : listaAlunosSelecionados) {
									gruposService.insertGruposCurso(grupo, item);
								}
							}
	
							context.addMessage(null, new FacesMessage("Sucesso", "Cadastrado com Sucesso"));
							posInit();
							cleanVariaveis();
						}
	
						carregaListaGrupos();
					} else {
						grupo = saveGrupos();
						saveTimeLine(grupo);
	
						context.addMessage(null, new FacesMessage("Sucesso", "Cadastrado com Sucesso"));
						posInit();
						cleanVariaveis();
						carregaListaGrupos();
					}
				}else{
					cursoSelecionado = null;
					LOG.warning(ERRO_01 + " Campos Sem preencher! ");
					context.addMessage(null, new FacesMessage("Campo Curso obrigatorio!"));
				}
			}

		} else {
			LOG.warning(ERRO_01 + " Campos Sem preencher! ");
			context.addMessage(null, new FacesMessage(ERRO_01, "Campos Sem preencher!"));
		}
	}

	private GruposEntity saveGrupos() {
		return gruposService.insert(gruposDTO, user);
	}

	private void saveTimeLine(GruposEntity grupo) {
		timeLineService.insertGrupo(grupo, user);
		// timeLineService.insertEvent(event, user);
	}

	private void cleanVariaveis() {
		listaAlunos = null;
		listaAlunosSelecionados = null;
		listaCursos = null;
		listaInstituicao = null;
		cursoSelecionado = null;
		instituicaoSelecionado = null;
	}

	public void buscaInstituicaoCursos() {
		if (cursoSelecionado != null && cursoSelecionado != 0) {
			listaInstituicao = null;
			carregaListaInstituicao();
			listaAlunos = null;
			carregaListaAlunosCurso();
		} else {
			listaAlunos = null;
			listaAlunosSelecionados = null;
			listaInstituicao = null;
			cursoSelecionado = null;
			instituicaoSelecionado = null;
		}
	}

	public void buscaCursosInstituicao() {
		if (gruposDTO.getTipoGrupo() != 1) {
			carregaListaCurso();
		} else {
			cleanVariaveis();
		}
	}
	
	public String removeGrupos(){
		TipoConteudoGrupoEntity tcg = gruposService.findTipoConteudoGrupo(selectGrupos);
		gruposService.removeTimeLine(tcg);
		gruposService.removeTipoGrupo(selectGrupos);
		gruposService.removeGruposUsuario(selectGrupos);
		gruposService.removeGrupos(selectGrupos);
		carregaListaGrupos();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso", "Grupo Removido"));
		return "grupos.xhtml?faces-redirect=true";
	}
	
	public void selecionaGrupos(GruposEntity grupos){
		this.selectGrupos = grupos;
	}

	public void buscaAlunos() {
		if (instituicaoSelecionado != null && instituicaoSelecionado != 0) {
			listaAlunos = null;
			listaAlunosSelecionados = null;
			carregaListaAlunos();
		} else {
			listaAlunos = null;
			carregaListaAlunosCurso();
			listaAlunosSelecionados = null;
			instituicaoSelecionado = null;
		}
	}

	public int qtdMembros(Long id) {
		return gruposService.findQtdMembrosGruposById(id);
	}

	private void carregaListaCurso() {
		if (listaCursos == null) {
			listaCursos = gruposService.findAllCursos();
		}
	}

	private void carregaListaAlunos() {
		if (listaAlunos == null) {
			listaAlunos = gruposService.findAllAlunosByInstituicao(instituicaoSelecionado);
		}
		SessionUtil.setParam("listaAlunosSelecionados", listaAlunos);
	}

	private void carregaListaAlunosCurso() {
		if (listaAlunos == null) {
			listaAlunos = gruposService.findAllAlunosByCurso(cursoSelecionado);
		}
		SessionUtil.setParam("listaAlunosSelecionados", listaAlunos);
	}

	private void carregaListaInstituicao() {
		if (listaInstituicao == null) {
			for (CursosEntity curso : listaCursos) {
				if (curso.getId().equals(new Long(cursoSelecionado))) {
					listaInstituicao = gruposService.findAllInstituicaoById(curso);
				}
			}
		}
	}

	private void carregaListaGrupos() {
		// listaCursos = gruposService.findAllCursos();
		listaGrupos = gruposService.findAllGrupos();
	}

	public List<GruposEntity> getListaGrupos() {
		return listaGrupos;
	}

	public GruposDTO getGruposDTO() {
		return gruposDTO;
	}

	public LoginEntity getUser() {
		return user;
	}

	public boolean isEdit() {
		return edit;
	}

	public GruposEntity getGruposEntity() {
		return gruposEntity;
	}

	public Integer getCursoSelecionado() {
		return cursoSelecionado;
	}

	public List<CursosEntity> getListaCursos() {
		return listaCursos;
	}

	public void setListaGrupos(List<GruposEntity> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

	public void setGruposDTO(GruposDTO gruposDTO) {
		this.gruposDTO = gruposDTO;
	}

	public void setUser(LoginEntity user) {
		this.user = user;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public void setGruposEntity(GruposEntity gruposEntity) {
		this.gruposEntity = gruposEntity;
	}

	public void setCursoSelecionado(Integer cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
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

	public Integer getInstituicaoSelecionado() {
		return instituicaoSelecionado;
	}

	public void setInstituicaoSelecionado(Integer instituicaoSelecionado) {
		this.instituicaoSelecionado = instituicaoSelecionado;
	}

	public List<UsuarioEntity> getListaAlunos() {
		return listaAlunos;
	}

	public void setListaAlunos(List<UsuarioEntity> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}

	public List<UsuarioEntity> getListaAlunosSelecionados() {
		return listaAlunosSelecionados;
	}

	public void setListaAlunosSelecionados(List<UsuarioEntity> listaAlunosSelecionados) {
		this.listaAlunosSelecionados = listaAlunosSelecionados;
	}

	public GruposEntity getSelectGrupos() {
		return selectGrupos;
	}

	public void setSelectGrupos(GruposEntity selectGrupos) {
		this.selectGrupos = selectGrupos;
	}

}
