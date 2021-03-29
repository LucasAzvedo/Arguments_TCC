package br.com.arguments.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.entity.DebateEntity;
import br.com.arguments.entity.EventoEntity;
import br.com.arguments.entity.GruposEntity;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.TimeLineEntity;
import br.com.arguments.entity.TipoConteudoDebateEntity;
import br.com.arguments.entity.TipoConteudoEventoEntity;
import br.com.arguments.entity.TipoConteudoGrupoEntity;
import br.com.arguments.entity.TipoConteudoTrabalhoEntity;
import br.com.arguments.entity.TrabalhoEntity;
import br.com.arguments.entity.UsuarioEntity;
import br.com.arguments.repository.TimeLineDAO;

@Stateless
public class TimeLineService {

	@EJB
	private TimeLineDAO timeLineDAO;
	
	public TimeLineEntity insertEvent(EventoEntity event, UsuarioEntity user) {
		
		TipoConteudoEventoEntity tpe = new TipoConteudoEventoEntity();
		tpe.setNome(event.getNome());
		tpe.setDescricao(event.getDescricao());
		tpe.setDataInicio(event.getDataInicio());
		tpe.setUsuario(user);
		tpe.setEvento(event);
		
		TipoConteudoEventoEntity tpEntity = timeLineDAO.insertTipoConteudoEvento(tpe);
		
		TimeLineEntity tl = new TimeLineEntity();
		tl.setIdCurso(event.getNumCurso().getId().intValue());
		tl.setIdTipoConteudoEvento(tpEntity);
		tl.setDataCriacao(dataAtual());
		tl.setTipoConteudo(1);
		
		TimeLineEntity tlEntity = timeLineDAO.insert(tl);
		
		return tlEntity;
	}

	public List<TimeLineEntity> findTimeLineByCurso(int id) {
		return timeLineDAO.findTimeLineByCurso(id);
		
	}

	public List<TipoConteudoEventoEntity> findTipoConteudoEvento(List<Integer> listaIdConteudoEvento) {
		return timeLineDAO.findTipoConteudoEvento(listaIdConteudoEvento);
	}

	public TimeLineEntity insertDebate(DebateEntity debate, UsuarioEntity user) {
		TipoConteudoDebateEntity tpd = new TipoConteudoDebateEntity();
		tpd.setNome(debate.getNome());
		tpd.setTema(debate.getTema());
		tpd.setDataAbertura(debate.getDataAbertura());
		tpd.setDataFechamento(debate.getDataFechamento());
		tpd.setDebate(debate);
//		tpd.setUsuario(user);
		
		TipoConteudoDebateEntity tpEntity = timeLineDAO.insertTipoConteudoDebate(tpd);
		
		TimeLineEntity tl = new TimeLineEntity();
		tl.setIdCurso(debate.getIdCurso().getId().intValue());
		tl.setIdTipoConteudoDebate(tpEntity);
		tl.setDataCriacao(dataAtual());
		tl.setTipoConteudo(2);
		
		
		TimeLineEntity tlEntity = timeLineDAO.insert(tl);
		
		return tlEntity;
	}
	
	public TimeLineEntity insertGrupo(GruposEntity grupo, LoginEntity user) {
		TipoConteudoGrupoEntity tcg = new TipoConteudoGrupoEntity();
		tcg.setNomeGrupo(grupo.getNome());
		tcg.setDescricaoGrupo(grupo.getDescricao());
		tcg.setQtdMaximaMembros(grupo.getQtdMaximaMembros());
		tcg.setTipoGrupo(grupo.getTipoGrupo());
		tcg.setCurso(grupo.getCurso());
		tcg.setGrupo(grupo);
		tcg.setInstituicao(grupo.getInstituicao());
		tcg.setUsuario(user.getIdUsuario());
		
		TipoConteudoGrupoEntity tpEntity = timeLineDAO.insertTipoConteudoGrupo(tcg);
		
		TimeLineEntity tl = new TimeLineEntity();
		tl.setIdCurso(grupo.getCurso() != null ? grupo.getCurso().getId().intValue() : 0);
		tl.setIdTipoConteudoGrupo(tpEntity);
		tl.setDataCriacao(dataAtual());
		tl.setTipoConteudo(3);
		
		TimeLineEntity tlEntity = timeLineDAO.insert(tl);
		
		return tlEntity;
		
	}
	
	public TimeLineEntity insertTrabalho(TrabalhoEntity trabalho, UsuarioEntity user) {
		TipoConteudoTrabalhoEntity tct = new TipoConteudoTrabalhoEntity();
		tct.setNome(trabalho.getNome());
		tct.setDescricao(trabalho.getDescricao());
		tct.setNumCurso(trabalho.getNumCurso());
		tct.setTrabalho(trabalho);
		
		TipoConteudoTrabalhoEntity tpEntity = timeLineDAO.insertTipoConteudoTrabalho(tct);
		
		TimeLineEntity tl = new TimeLineEntity();
		tl.setIdCurso(trabalho.getNumCurso().getId().intValue());
		tl.setIdTipoConteudoTrabalho(tpEntity);
		tl.setDataCriacao(dataAtual());
		tl.setTipoConteudo(4);
		
		TimeLineEntity tlEntity = timeLineDAO.insert(tl);
		
		return tlEntity;
		
	}
	
	public Timestamp dataAtual(){
		try{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String dataAtual = format.format(new Date());
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    Date parsedDate = dateFormat.parse(dataAtual.toString());
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    return timestamp;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}



}
