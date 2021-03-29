package br.com.arguments.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.arguments.entity.TipoUsuarioEntity;
import br.com.arguments.entity.UsuarioEntity;

@Stateless
public class UsuarioDAO extends BaseDAO {

	public List<UsuarioEntity> findAllUsers() {
		TypedQuery<UsuarioEntity> query = getManager()
				.createQuery("FROM UsuarioEntity ", UsuarioEntity.class);
		List<UsuarioEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		return ls;
	}

	public List<TipoUsuarioEntity> findAllTipoUsuario() {
		TypedQuery<TipoUsuarioEntity> query = getManager()
				.createQuery("FROM TipoUsuarioEntity ", TipoUsuarioEntity.class);
		List<TipoUsuarioEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		return ls;
	}

	public void updatePermissao(UsuarioEntity usuarioEditado, TipoUsuarioEntity tipoUsuario) {
			Query query = getManager().createQuery("UPDATE UsuarioEntity SET tipoUsuario = :tipoUsuario " + " WHERE id = :id ");
			query.setParameter("tipoUsuario", tipoUsuario);
			query.setParameter("id", usuarioEditado.getId());
			query.executeUpdate();
	}
	
	public void uptade(UsuarioEntity user) {
		Query query = getManager()
		 .createQuery("UPDATE UsuarioEntity SET nome = :nome, sobrenome = :sobrenome, email = :email, ra = :ra" + " WHERE id = :id ");
		query.setParameter("id", user.getId());
		query.setParameter("nome", user.getNome());
		query.setParameter("sobrenome", user.getSobrenome());
		query.setParameter("email", user.getEmail());		
		query.setParameter("ra", user.getRa());		
		query.executeUpdate();
		
	}

	public UsuarioEntity findUserById(Long id) {
		return getManager().find(UsuarioEntity.class, id);
	}
	
	public boolean ativeUser(UsuarioEntity usuario) {
		Query query = getManager()
			 .createQuery("UPDATE UsuarioEntity SET ativo = true" + " WHERE id = :id ");
			query.setParameter("id", usuario.getId());
			int x = query.executeUpdate();
			return x > 0 ? true : false;
	}

	public boolean inativeUser(UsuarioEntity usuario) {
		Query query = getManager()
				 .createQuery("UPDATE UsuarioEntity SET ativo = false" + " WHERE id = :id ");
				query.setParameter("id", usuario.getId());
				int x = query.executeUpdate();
				return x > 0 ? true : false;
	}
	
	
}
