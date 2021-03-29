package br.com.arguments.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.dto.EventoDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.EventoEntity;
import br.com.arguments.entity.TimeLineEntity;
import br.com.arguments.entity.TipoConteudoDebateEntity;
import br.com.arguments.entity.TipoConteudoEventoEntity;
import br.com.arguments.repository.EventoDAO;

@Stateless
public class EventoService {
	
	@EJB
	private EventoDAO eventoDAO;

	public List<EventoEntity> findAllActive(){
		return eventoDAO.findAllActive();
	}

	public EventoEntity insert(EventoDTO dto){
		EventoEntity evento = new EventoEntity();
		evento.setNome(dto.getNome());
		evento.setDescricao(dto.getDescricao());
		evento.setNumCurso(dto.getCurso());
		evento.setDataInicio(dto.getDataInicioStamp());
		evento.setDataCriacao(dataAtual());
		evento.setAtivo(true);
		return eventoDAO.insert(evento);
	}

	public void remove(EventoEntity entity){
		eventoDAO.remove(entity);
	}

	public void update(EventoDTO dto){
		EventoEntity evento = new EventoEntity();
		evento.setId(dto.getId());
		evento.setNome(dto.getNome());
		evento.setDescricao(dto.getDescricao());
		evento.setNumCurso(dto.getCurso());
		evento.setDataInicio(dto.getDataInicioStamp());
		evento.setAtivo(true);
		eventoDAO.update(evento);
	}

	public void updateTipoConteudoEvento(EventoDTO dto){	
		EventoEntity evento = new EventoEntity();
		evento.setId(dto.getId());
		evento.setDataInicio(dto.getDataInicioStamp());
		evento.setDescricao(dto.getDescricao());
		evento.setNome(dto.getNome());		
		eventoDAO.updateTipoConteudoEvento(evento);
	}
	
//	public void updateTimeLine(EventoDTO dto){
//		TimeLineEntity evento = new TimeLineEntity();
//		TipoConteudoEventoEntity tce = new TipoConteudoEventoEntity();
//		evento.setIdTipoConteudoEvento(dto.getId());
//		evento.setIdCurso(dto.getNumCurso().getId().intValue());;
//		EventoEntity evento = new EventoEntity();
//		evento.setNumCurso(dto.getCurso());
//		eventoDAO.updateTimeLine(evento);
//	}
	
	public List<CursosEntity> findAllCursos() {
		return eventoDAO.findAllCursos();
	}
	
	public Timestamp dataAtual(){
		try{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
			String dataAtual = format.format(new Date());
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		    Date parsedDate = dateFormat.parse(dataAtual.toString());
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    return timestamp;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void removeTipoConteudo(EventoEntity evento){
		eventoDAO.removeTipoConteudo(evento);
	}
	
	public void removeTimeLine(TipoConteudoEventoEntity tcd){
		eventoDAO.removeTimeLine(tcd);
	}
	
	public TipoConteudoEventoEntity findTipoConteudoEvento(EventoEntity evento){
		return eventoDAO.findTipoConteudoEvento(evento);
	}
	
}
