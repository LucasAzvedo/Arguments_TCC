package br.com.arguments.repository;

import javax.ejb.Stateless;

import br.com.arguments.entity.TipoUsuarioEntity;
import br.com.arguments.entity.UsuarioEntity;

@Stateless
public class CadastroDAO extends BaseDAO {
	
//	public LoginEntity save(LoginEntity login){
//		return getManager().merge(login);
//	}
//	
//	public List<LoginEntity> findAll() {
//		return getManager().createQuery("SELECT A FROM LOGIN A", LoginEntity.class).getResultList();
//	}
//
//	public List<LoginEntity> findByUsuario(String usuario) {
//		TypedQuery<LoginEntity> query = getManager().createQuery("SELECT U FROM LOGIN U WHERE U.NOME LIKE :NOME", LoginEntity.class);
//		query.setParameter("NOME", "%" + usuario + "%");
//		return query.getResultList();
//	}
	
	public UsuarioEntity insertUser(UsuarioEntity user){
		return getManager().merge(user);
	}

	public UsuarioEntity findUserById(Long id) {
		return getManager().find(UsuarioEntity.class, id);
	}
	
}
