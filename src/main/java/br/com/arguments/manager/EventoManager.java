package br.com.arguments.manager;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.arguments.dto.EventoDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.EventoEntity;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.TipoConteudoEventoEntity;
import br.com.arguments.entity.UsuarioEntity;
import br.com.arguments.service.EventoService;
import br.com.arguments.service.TimeLineService;
import br.com.arguments.util.jsf.SessionUtil;

@ManagedBean
@ViewScoped
public class EventoManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(CadastroManager.class.getName());

	private static final String ERRO_01 = "ERRO";

	@EJB
	private EventoService eventoService;

	@EJB
	private TimeLineService timeLineService;

	private List<EventoEntity> listEvento;

	private EventoEntity selectedEvent;

	private EventoDTO dto;

	private boolean edit;

	private LoginEntity login;

	private UsuarioEntity user;

	private List<CursosEntity> listaCursos;

	private Integer cursoSelecionado;
	
//	TESTE
	private MapModel localizacao;

	private String titulo;

	private double latutude;

	private double longitude;
	
	private List<MapModel> listaLocalizacao;

	@PostConstruct
	public void init() {
		login = (LoginEntity) SessionUtil.getParam("UserLoged");
		user = login.getIdUsuario();
		listaCursos = buscaListaCursos();
		posInit();
	}

	private void posInit() {
		dto = new EventoDTO();
		edit = false;
		cursoSelecionado = null;
	}

	private List<CursosEntity> buscaListaCursos() {
		return eventoService.findAllCursos();
	}

	public void cadastrarEvento() {
		FacesContext context = FacesContext.getCurrentInstance();
		EventoEntity event = new EventoEntity();
		if ((!dto.getNome().isEmpty() && dto.getNome() != null)) {
			if (validData()) {
				if (cursoSelecionado != null) {
					for (CursosEntity item : listaCursos) {
						if (item.getId().equals(new Long(cursoSelecionado))) {
							dto.setCurso(item);
						}
					}

					event = eventoService.insert(dto);

					timeLineService.insertEvent(event, user);

					posInit();
					carregaLista();
					context.addMessage(null, new FacesMessage("Sucesso", "Cadastrado com Sucesso"));
				} else {
					LOG.warning(ERRO_01 + " Nenhum curso selecionado! ");
					context.addMessage(null, new FacesMessage(ERRO_01, "Nenhum curso selecionado!"));
				}
			} else {
				LOG.warning(ERRO_01 + " Erro de data! "); 
				context.addMessage(null, new FacesMessage(ERRO_01, "Data errada!"));
			}
		} else {
			LOG.warning(ERRO_01 + " Campos Sem preencher! ");
			context.addMessage(null, new FacesMessage(ERRO_01, "Campos Sem preencher!"));
		}
	}

	private boolean validData() {
		if (dto.getDataInicio() != null) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
				Date parsedDate = dateFormat.parse(dto.getDataInicio());
				Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
				dto.setDataInicioStamp(timestamp);
				return true;
			} catch (Exception e) {// this generic but you can control another
									// types of exception
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}

	}

	public String removeEvent() {
		TipoConteudoEventoEntity tce = eventoService.findTipoConteudoEvento(selectedEvent);
		eventoService.removeTimeLine(tce);
		eventoService.removeTipoConteudo(selectedEvent);
		eventoService.remove(selectedEvent);
		carregaLista();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Sucesso", "Evento Removido"));
		return "evento.xhtml?faces-redirect=true";
	}

	public void editEvent(EventoEntity entity) {
		if (entity != null) {
			dto = new EventoDTO();
			dto.setId(entity.getId());
			dto.setNome(entity.getNome());
			dto.setDescricao(entity.getDescricao());
			dto.setDataCriacao(entity.getDataCriacao());
			cursoSelecionado = entity.getNumCurso().getId().intValue();
			dto.setDataInicio(convertoCompleteTimestampToString(entity.getDataInicio()));
			dto.setAtivo(entity.isAtivo());
			edit = true;			
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("ERRO", "Evento em branco"));
		}	
		
		
	}
	
	public void editTipo(EventoDTO entity){
		if(entity != null){
			eventoService.updateTipoConteudoEvento(entity);
			carregaLista();
		}else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("ERRO", "Evento em branco"));
		}	
	}
	
//	public void editTimeLine(EventoDTO entity){
//		if(entity != null){
//			eventoService.updateTimeLine(entity);
//			carregaLista();
//		}else {
//			FacesContext context = FacesContext.getCurrentInstance();
//			context.addMessage(null, new FacesMessage("ERRO", "Evento em branco"));
//		}	
//	}
	
	public void addMarker() {
		this.localizacao = new DefaultMapModel();
		Marker marker = new Marker(new LatLng(this.latutude, this.longitude), this.titulo);
		this.localizacao.addOverlay(marker);
		
		if(this.listaLocalizacao == null){
			this.listaLocalizacao = new ArrayList<>();
		}
		
		listaLocalizacao.add(this.localizacao);
	}

	public String convertDateToString(Date data) {
		if (data != null) {
			SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
			return formatado.format(data);
		}
		return null;
	}

	public String convertTimestampToString(Timestamp data) {
		return data != null ? new SimpleDateFormat("dd/MM/yyyy").format(data) : "dd/MM/yyyy";
	}

	public String convertoCompleteTimestampToString(Timestamp data) {
		return new SimpleDateFormat("dd/MM/yyyy hh:mm").format(data);
	}

	public void selecionaEvento(EventoEntity evento) {
		this.selectedEvent = evento;
	}

	public void saveEventoEdit() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (edit) {
			if (validData()) {
				if (cursoSelecionado != null) {
					for (CursosEntity item : listaCursos) {
						if (item.getId().equals(new Long(cursoSelecionado))) {
							dto.setCurso(item);
						}
					}
				}
				eventoService.update(dto);
				editTipo(dto);
//				editTimeLine(dto);
				posInit();
				carregaLista();
				context.addMessage(null, new FacesMessage("Sucesso" + ": Evento alterado."));
			}
		}
	}

	private void carregaLista() {
		listEvento = eventoService.findAllActive();
	}

	/** GETTERS E SETTERS */

	public EventoEntity getSelectedEvent() {
		return selectedEvent;
	}

	public void setSelectedEvent(EventoEntity selectedEvent) {
		this.selectedEvent = selectedEvent;
	}

	public List<EventoEntity> getListEvento() {
		if (listEvento == null) {
			carregaLista();
		}
		return listEvento;
	}

	public void setListEvento(List<EventoEntity> listEvento) {
		this.listEvento = listEvento;
	}

	public EventoDTO getDto() {
		return dto;
	}

	public void setDto(EventoDTO dto) {
		this.dto = dto;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
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

	public List<CursosEntity> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<CursosEntity> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public Integer getCursoSelecionado() {
		return cursoSelecionado;
	}

	public void setCursoSelecionado(Integer cursoSelecionado) {
		this.cursoSelecionado = cursoSelecionado;
	}

	public MapModel getLocalizacao() {
		return localizacao;
	}

	public String getTitulo() {
		return titulo;
	}

	public double getLatutude() {
		return latutude;
	}

	public double getLongitude() {
		return longitude;
	}

	public List<MapModel> getListaLocalizacao() {
		return listaLocalizacao;
	}

	public void setLocalizacao(MapModel localizacao) {
		this.localizacao = localizacao;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setLatutude(double latutude) {
		this.latutude = latutude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setListaLocalizacao(List<MapModel> listaLocalizacao) {
		this.listaLocalizacao = listaLocalizacao;
	}

}
