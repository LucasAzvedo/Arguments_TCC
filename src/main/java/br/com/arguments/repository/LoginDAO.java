package br.com.arguments.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.arguments.entity.LoginEntity;

@Stateless
public class LoginDAO extends BaseDAO {
	
	public LoginEntity save(LoginEntity login){
		return getManager().merge(login);
	}
	
	public LoginEntity findLogin(String user){
		TypedQuery<LoginEntity> query = getManager()
				.createQuery("FROM LoginEntity L WHERE L.usuario = :usuario", LoginEntity.class);
		query.setParameter("usuario", user);
		List<LoginEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		
		return ls.get(0);
	}
	
	public void uptade(LoginEntity loginEntity) {
		Query query = getManager().createQuery("UPDATE LoginEntity SET senha = :senha, usuario = :usuario " + " WHERE id = :id ");
		query.setParameter("id", loginEntity.getId());
		query.setParameter("senha",loginEntity.getSenha());
		query.setParameter("usuario",loginEntity.getUsuario());
		query.executeUpdate();
	}
	
	public LoginEntity findById(int id) {
		return getManager().find(LoginEntity.class,id);
	}
	
}
