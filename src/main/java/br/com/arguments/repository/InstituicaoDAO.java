package br.com.arguments.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.InstituicaoCursosEntity;
import br.com.arguments.entity.InstituicaoEntity;

@Stateless
public class InstituicaoDAO extends BaseDAO {
	
	public List<InstituicaoEntity> findAllInstituicao() {

		TypedQuery<InstituicaoEntity> query = getManager()
				.createQuery("FROM InstituicaoEntity", InstituicaoEntity.class);
		List<InstituicaoEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		
		return ls;
		
	}

	public List<InstituicaoCursosEntity> findCursosByInstituicao(InstituicaoEntity idInstituicaoEntity) {
		
		TypedQuery<InstituicaoCursosEntity> query = getManager()
				.createQuery("SELECT L FROM InstituicaoCursosEntity L JOIN L.idCursosEntity "
						+ "WHERE L.idInstituicaoEntity = :idInstituicaoEntity ", InstituicaoCursosEntity.class);
		query.setParameter("idInstituicaoEntity", idInstituicaoEntity);
		
		List<InstituicaoCursosEntity> itens = query.getResultList();

//		List<CursosEntity> cursos = null;
//
//		if(itens != null){
//			cursos = new ArrayList<>();
//			for(InstituicaoCursosEntity item : itens){
//				cursos.add(item.getIdCursosEntity());
//			}
//			
//		}
		
		return itens;
		
		
	}

	public List<CursosEntity> findAllCursos() {
		TypedQuery<CursosEntity> query = getManager()
				.createQuery("FROM CursosEntity", CursosEntity.class);
		List<CursosEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		
		return ls;
	}
	
}
