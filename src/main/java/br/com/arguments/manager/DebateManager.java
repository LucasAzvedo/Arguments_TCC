package br.com.arguments.manager;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.arguments.dto.DebateDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.DebateEntity;
import br.com.arguments.entity.InstituicaoEntity;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.TipoConteudoDebateEntity;
import br.com.arguments.service.DebateService;
import br.com.arguments.service.InstituicaoService;
import br.com.arguments.service.TimeLineService;
import br.com.arguments.util.jsf.SessionUtil;

@ManagedBean
@ViewScoped
public class DebateManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(DebateManager.class.getName());

	private static final String ERRO_01 = "ERRO";

	private static final String SUCESSO_01 = "SUCESSO";

	@EJB
	private DebateService debateService;

	@EJB
	private TimeLineService timeLineService;

	@EJB
	private InstituicaoService instituicaoService;

	private List<DebateEntity> listaDebate;

	private List<CursosEntity> listaCursos;

	private Integer cursoSelecionado;

	private DebateDTO debateDTO;

	private LoginEntity user;

	private boolean edit;

	private DebateEntity selectDebate;

	private Long selectedarea;

	private Long selectedclassif;

	private Integer instituicaoSelecionada;

	private List<InstituicaoEntity> listaInstituicao;

	private boolean verifica;

	@PostConstruct
	public void init() {
		user = (LoginEntity) SessionUtil.getParam("UserLoged");
		// listaInstituicao = instituicaoService.findAllInstituicao();
		listaCursos = instituicaoService.findAllCursos();
		posInit();
	}

	private void posInit() {
		debateDTO = new DebateDTO();
		edit = false;
	}

	public void criaDebate() {
		FacesContext context = FacesContext.getCurrentInstance();
		DebateEntity debate = new DebateEntity();

		if ((!debateDTO.getNomeDebate().isEmpty() && debateDTO.getNomeDebate() != null)
				|| debateDTO.getDataCriacao() != null || debateDTO.getDataFechamento() != null) {
			if (validData()) {

				buscaCursoInstutuicao();
				debate = debateService.insert(debateDTO, user.getIdUsuario());

				timeLineService.insertDebate(debate, user.getIdUsuario());

				posInit();
				carregaLista();
				context.addMessage(null, new FacesMessage(SUCESSO_01, "Cadastro com Sucesso"));
			} else {
				LOG.warning(ERRO_01 + " Data Invalida! ");
				context.addMessage(null, new FacesMessage(ERRO_01, "Data Invalida!"));
			}
		} else {
			LOG.warning(ERRO_01 + " Campo sem preencher! ");
			context.addMessage(null, new FacesMessage(ERRO_01, "Campo sem preencher!"));
		}
	}

	private boolean validData() {
		if (debateDTO.getDataCriacao() != null && debateDTO.getDataFechamento() != null) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");

				Date parsedDateIni = dateFormat.parse(debateDTO.getDataCriacao());
				Date parsedDateFim = dateFormat.parse(debateDTO.getDataFechamento());

				Timestamp timestampIni = new java.sql.Timestamp(parsedDateIni.getTime());
				Timestamp timestampFim = new java.sql.Timestamp(parsedDateFim.getTime());

				debateDTO.setDataCriacaoStamp(timestampIni);
				debateDTO.setDataFechamentoStamp(timestampFim);

				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}

	}

	private void carregaLista() {
		listaDebate = debateService.findAllDebates();
	}

	public void editDebate(DebateEntity debatentidade) {
		if (debatentidade != null) {
			debateDTO = new DebateDTO();
			debateDTO.setId(Long.valueOf(debatentidade.getId()));
			debateDTO.setNomeDebate(debatentidade.getNome());
			debateDTO.setTemaDebate(debatentidade.getTema());
			debateDTO.setAssunto(debatentidade.getAssunto());
			debateDTO.setStatus(debatentidade.getStatus());
			cursoSelecionado = debatentidade.getIdCurso().getId().intValue();
			debateDTO.setDataCriacao(convertoCompleteTimestampToString(debatentidade.getDataAbertura()));
			debateDTO.setDataFechamento(convertoCompleteTimestampToString(debatentidade.getDataFechamento()));
			edit = true;
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("ERRO", "Debate está em branco"));
		}
	}
	
	public void editTipo(DebateDTO entity){
		if(entity != null){
			debateService.updateTipoConteudoDebate(entity);
			carregaLista();
			
		}else {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("ERRO", "Debate em branco"));
	}	
	}

	public void saveEdit() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (edit) {
			if ((!debateDTO.getNomeDebate().isEmpty() && debateDTO.getNomeDebate() != null)
					|| debateDTO.getDataCriacao() != null || debateDTO.getDataFechamento() != null) {
				if (validData()) {

					buscaCursoInstutuicao();
					debateService.update(debateDTO);
					editTipo(debateDTO);
					posInit();
					carregaLista();
					context.addMessage(null, new FacesMessage(SUCESSO_01, "Editado com Sucesso"));
				} else {
					LOG.warning(ERRO_01 + " Data Invalida! ");
					context.addMessage(null, new FacesMessage(ERRO_01, "Data Invalida!"));
				}
			} else {
				LOG.warning(ERRO_01 + " Campo sem preencher! ");
				context.addMessage(null, new FacesMessage(ERRO_01, "Campo sem preencher!"));
			}
		}
	}
	
	public String removeDebate() {
		debateService.removeComentariosDebate(selectDebate);
		TipoConteudoDebateEntity tcd = debateService.findTipoConteudoDebate(selectDebate);
		debateService.removeTimeLine(tcd);
		debateService.removeTipoConteudo(selectDebate);
		debateService.remove(selectDebate);
		carregaLista();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso", "Debate Removido"));
		return "debate.xhtml?faces-redirect=true";
	}

	public String convertoCompleteTimestampToString(Timestamp data) {
		return new SimpleDateFormat("dd/MM/yyyy hh:mm").format(data);
	}

	public void buscaCursoInstutuicao() {
		for (CursosEntity item : listaCursos) {
			if (item.getId().equals(new Long(cursoSelecionado))) {
				debateDTO.setIdCursos(item);
			}
		}
	}

	public DebateService getDebateService() {
		return debateService;
	}

	public void setDebateService(DebateService debateService) {
		this.debateService = debateService;
	}

	public List<DebateEntity> getListaDebate() {
		if (listaDebate == null) {
			carregaLista();
		}
		return listaDebate;
	}

	public void setListaDebate(List<DebateEntity> listaDebate) {
		this.listaDebate = listaDebate;
	}

	public DebateDTO getDebateDTO() {
		return debateDTO;
	}

	public void setDebateDTO(DebateDTO debateDTO) {
		this.debateDTO = debateDTO;
	}

	public LoginEntity getUser() {
		return user;
	}

	public void setUser(LoginEntity user) {
		this.user = user;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public Long getSelectedarea() {
		return selectedarea;
	}

	public void setSelectedarea(Long selectedarea) {
		this.selectedarea = selectedarea;
	}

	public Long getSelectedclassif() {
		return selectedclassif;
	}

	public void setSelectedclassif(Long selectedclassif) {
		this.selectedclassif = selectedclassif;
	}

	public boolean isVerifica() {
		return verifica;
	}

	public void setVerifica(boolean verifica) {
		this.verifica = verifica;
	}

	public List<CursosEntity> getListaCursos() {
		return listaCursos;
	}

	public Integer getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Integer cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public void setListaCursos(List<CursosEntity> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public Integer getInstituicaoSelecionada() {
		return instituicaoSelecionada;
	}

	public void setInstituicaoSelecionada(Integer instituicaoSelecionada) {
		this.instituicaoSelecionada = instituicaoSelecionada;
	}

	public List<InstituicaoEntity> getListaInstituicao() {
		return listaInstituicao;
	}

	public void setListaInstituicao(List<InstituicaoEntity> listaInstituicao) {
		this.listaInstituicao = listaInstituicao;
	}

	public DebateEntity getSelectDebate() {
		return selectDebate;
	}

	public void setSelectDebate(DebateEntity selectDebate) {
		this.selectDebate = selectDebate;
	}

}
