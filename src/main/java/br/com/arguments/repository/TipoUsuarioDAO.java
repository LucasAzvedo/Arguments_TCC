package br.com.arguments.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.arguments.entity.TipoUsuarioEntity;

@Stateless
public class TipoUsuarioDAO extends BaseDAO {
	
	public TipoUsuarioEntity findId(Long id){
		return getManager().find(TipoUsuarioEntity.class, id);
	}
	
	public List<TipoUsuarioEntity> findAllTipoUsuario(){
		
		TypedQuery<TipoUsuarioEntity> query = getManager()
				.createQuery("FROM TipoUsuarioEntity", TipoUsuarioEntity.class);
		List<TipoUsuarioEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		
		return ls;
	}
	
}
