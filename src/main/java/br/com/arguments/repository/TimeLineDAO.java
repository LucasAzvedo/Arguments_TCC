package br.com.arguments.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.arguments.entity.TimeLineEntity;
import br.com.arguments.entity.TipoConteudoDebateEntity;
import br.com.arguments.entity.TipoConteudoEventoEntity;
import br.com.arguments.entity.TipoConteudoGrupoEntity;
import br.com.arguments.entity.TipoConteudoTrabalhoEntity;

@Stateless
public class TimeLineDAO extends BaseDAO {
	
	public TimeLineEntity insert(TimeLineEntity timeLine) {
		 return getManager().merge(timeLine);
	}
	
	public TipoConteudoEventoEntity insertTipoConteudoEvento(TipoConteudoEventoEntity tpe) {
		return getManager().merge(tpe);
	}

	public List<TimeLineEntity> findTimeLineByCurso(int id) {

			TypedQuery<TimeLineEntity> query = getManager()
					.createQuery("FROM TimeLineEntity L WHERE L.idCurso = :idCurso OR L.idCurso = 0 "
							+ " ORDER BY L.dataCriacao ASC", TimeLineEntity.class);
			query.setParameter("idCurso", id);
			List<TimeLineEntity> ls = query.getResultList();
			
			if(ls.isEmpty()){
				return null;
			}
		
		return ls;
	}

	public List<TipoConteudoEventoEntity> findTipoConteudoEvento(List<Integer> listaIdConteudoEvento) {

		TypedQuery<TipoConteudoEventoEntity> query = getManager()
				.createQuery("FROM TipoConteudoEventoEntity L WHERE L.id IN (:idsConteudo)", TipoConteudoEventoEntity.class);
		query.setParameter("idsConteudo", listaIdConteudoEvento);
		List<TipoConteudoEventoEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
	
	return ls;
		
		
	}

	public TipoConteudoDebateEntity insertTipoConteudoDebate(TipoConteudoDebateEntity tpd) {
		return getManager().merge(tpd);
	}

	public TipoConteudoGrupoEntity insertTipoConteudoGrupo(TipoConteudoGrupoEntity tcg) {
		return getManager().merge(tcg);
	}

	public TipoConteudoTrabalhoEntity insertTipoConteudoTrabalho(TipoConteudoTrabalhoEntity tct) {
		return getManager().merge(tct);
	}
	
}
