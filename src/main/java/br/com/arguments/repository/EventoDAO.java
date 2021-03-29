package br.com.arguments.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.arguments.dto.EventoDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.EventoEntity;
import br.com.arguments.entity.TimeLineEntity;
import br.com.arguments.entity.TipoConteudoDebateEntity;
//import br.com.unieventos.entity.UsuarioEntity;
//import br.com.unieventos.entity.UsuarioEventoEntity;
import br.com.arguments.entity.TipoConteudoEventoEntity;

@Stateless
public class EventoDAO extends BaseDAO {
	
	public List<EventoEntity> findAllActive(){
		TypedQuery<EventoEntity> query = getManager()
				.createQuery("FROM EventoEntity L WHERE L.ativo = :ativo", EventoEntity.class);
		query.setParameter("ativo", true);
		List<EventoEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		return ls;
	}
//	
//	public EventoEntity findEventoById(Long id){
//		return getManager().find(EventoEntity.class, id.intValue());
//	}
//
////	public UsuarioEntity findUserById(Long id) {
////		return getManager().find(UsuarioEntity.class, id);
////	}
//
	public EventoEntity insert(EventoEntity event) {
		 return getManager().merge(event);
	}
//	
	public void update(EventoEntity entity) {
		Query query = getManager().createQuery("UPDATE EventoEntity SET nome = :nome, descricao = :desc, dataInicio = :dataInicio, numCurso = :numCurso " + 
					" WHERE id = :id ");
		query.setParameter("id", entity.getId());
		query.setParameter("nome", entity.getNome());
		query.setParameter("desc", entity.getDescricao());
		query.setParameter("numCurso", entity.getNumCurso());
		query.setParameter("dataInicio", entity.getDataInicio());
		query.executeUpdate();
	}
	
	public void updateTipoConteudoEvento(EventoEntity tce){
		Query query = getManager().createQuery("UPDATE TipoConteudoEventoEntity SET nome = :nome, descricao = :descricao, dataInicio = :dataInicio " + "WHERE evento = :evento ");
		query.setParameter("evento", tce);
		query.setParameter("nome", tce.getNome());
		query.setParameter("descricao", tce.getDescricao());
		query.setParameter("dataInicio", tce.getDataInicio());
		query.executeUpdate();
	}
	
//	public void updateTimeLine(EventoEntity evento){
//		Query query = getManager().createQuery("UPDATE TimeLineEntity SET idCurso = :idCurso WHERE idTipoConteudoEvento = :idTipoConteudoEvento ");
//		query.setParameter("idTipoConteudoEvento", evento.getId());
//		query.setParameter("idCurso", evento.getNumCurso().getId().intValue());
//		query.executeUpdate();
//	}
//	
	public void remove(EventoEntity entity){
		Query query = getManager().createQuery("DELETE EventoEntity WHERE id = :id ");
		query.setParameter("id", entity.getId());
		query.executeUpdate();
	}
	
	public void removeTipoConteudo(EventoEntity evento){
		Query query = getManager().createQuery("DELETE TipoConteudoEventoEntity WHERE evento = :evento");
		query.setParameter("evento", evento);
		query.executeUpdate();
	}
	
	public void removeTimeLine(TipoConteudoEventoEntity tcd){
		Query query = getManager().createQuery("DELETE TimeLineEntity WHERE idTipoConteudoEvento = :tcd");
		query.setParameter("tcd", tcd);
		query.executeUpdate();
	}
	
	public TipoConteudoEventoEntity findTipoConteudoEvento(EventoEntity evento){
		
		TypedQuery<TipoConteudoEventoEntity> query = getManager()
				.createQuery("SELECT L FROM TipoConteudoEventoEntity L JOIN L.evento "
						+ "WHERE L.evento = :evento", TipoConteudoEventoEntity.class);
		
		query.setParameter("evento", evento);
		
		List<TipoConteudoEventoEntity> itens = query.getResultList();
		
		return itens.get(0);
	}
//	
//	public void cancelParticipacaoEvent(EventoEntity evento,UsuarioEntity user){
//		Query query = getManager().createQuery("DELETE UsuarioEventoEntity WHERE idUsuarioEntity = :idUsuarioEntity "
//				+ "and idEventoEntity = :idEventoEntity");
//		query.setParameter("idUsuarioEntity", user);
//		query.setParameter("idEventoEntity", evento);
//		query.executeUpdate();
//	}
//	
//	public boolean verifyParticipacao(EventoEntity evento,UsuarioEntity user){
//		TypedQuery<UsuarioEventoEntity> query = getManager()
//				.createQuery("FROM UsuarioEventoEntity WHERE idUsuarioEntity = :idUsuarioEntity "
//							+ "and idEventoEntity = :idEventoEntity", UsuarioEventoEntity.class);
//		query.setParameter("idUsuarioEntity", user);
//		query.setParameter("idEventoEntity", evento);
//		//query.executeUpdate();
//		List<UsuarioEventoEntity> ls = query.getResultList();
//		
//		if(ls.isEmpty()){
//			return false;
//		}
//		return true;
//	}
//	
//	public void participarEvento(UsuarioEventoEntity entity){
//		getManager().merge(entity);
//		
//	}
//	
//	public List<EventoEntity> pesquisaEvento(String nome){
//		TypedQuery<EventoEntity> query = getManager()
//				.createQuery("FROM EventoEntity L WHERE L.ativo = :ativo and L.nome like :nome", EventoEntity.class);
//		query.setParameter("ativo", true);
//		query.setParameter("nome", "%" + nome + "%");
//		List<EventoEntity> ls = query.getResultList();
//		
//		if(ls.isEmpty()){
//			return null;
//		}
//		return ls;
//	}
//	
//	public List<EventoEntity> findEventoByUser(UsuarioEntity user){
//		
//		List<EventoEntity> retorno = new ArrayList<EventoEntity>();
//		TypedQuery<UsuarioEventoEntity> query = getManager()
//				.createQuery("FROM UsuarioEventoEntity L "
//						+ "WHERE L.idUsuarioEntity = :idUsuarioEntity ", UsuarioEventoEntity.class);
////		query.setParameter("ativo", true);
//		query.setParameter("idUsuarioEntity", user);
//		List<UsuarioEventoEntity> ls = query.getResultList();
//		
//		if(ls.isEmpty()){
//			return null;
//		}
//		
//		for(UsuarioEventoEntity item : ls){
//			retorno.add(item.getIdEventoEntity());
//		}
//		return retorno;
//	}
//
//	public UsuarioEventoEntity findUsuarioEvento(UsuarioEntity user, EventoEntity evento) {
//		 
//		TypedQuery<UsuarioEventoEntity> query = getManager()
//		.createQuery("FROM UsuarioEventoEntity L "
//				+ "WHERE L.idUsuarioEntity = :idUsuarioEntity "
//				+ "AND L.idEventoEntity = :idEventoEntity ", UsuarioEventoEntity.class);
//		query.setParameter("idUsuarioEntity", user);
//		query.setParameter("idEventoEntity", evento);
//		UsuarioEventoEntity item = query.getSingleResult();
//		
//		if(item != null){
//			return item;
//		}
//		
//		return null;
//	}
	public List<CursosEntity> findAllCursos() {
		TypedQuery<CursosEntity> query = getManager()
				.createQuery("FROM CursosEntity", CursosEntity.class);
		List<CursosEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		return ls;
	}
	
//	public List<EventoEntity> findAtivitByUser(UsuarioEntity user){
//		
//		String hql = "FROM UsuarioEventoEntity JOIN UsuarioEventoEntity.idEventoEntity "
//				+ "WHERE UsuarioEventoEntity.idUsuarioEntity = :idUsuarioEntity ";
//		 
//		Query query = getManager().createQuery(hql);
//		query.setParameter("idUsuarioEntity", user);
//		
//		List<UsuarioEventoEntity> listResult = query.getResultList();
//		
//		List<EventoEntity> listEvent = new ArrayList<EventoEntity>();
//		
//		for(UsuarioEventoEntity item : listResult){
//			listEvent.add(item.getIdEventoEntity());
//		}
//		
//		return listEvent;
//	}
	
}
