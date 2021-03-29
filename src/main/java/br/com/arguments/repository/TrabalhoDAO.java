package br.com.arguments.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.GruposUsuarioEntity;
import br.com.arguments.entity.TipoConteudoDebateEntity;
import br.com.arguments.entity.TipoConteudoTrabalhoEntity;
import br.com.arguments.entity.TrabalhoEntity;
import br.com.arguments.entity.TrabalhoUsuarioEntity;
import br.com.arguments.entity.UsuarioEntity;

@Stateless
public class TrabalhoDAO extends BaseDAO{
	
	public List<TrabalhoEntity> findAllActive(){
		TypedQuery<TrabalhoEntity> query = getManager()
				.createQuery("FROM TrabalhoEntity L WHERE L.ativo = :ativo", TrabalhoEntity.class);
		query.setParameter("ativo", true);
		List<TrabalhoEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		
		return ls;
	}
	
	public List<CursosEntity> findAllCursos(){
		TypedQuery<CursosEntity> query = getManager()
				.createQuery("FROM CursosEntity", CursosEntity.class);
		List<CursosEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		
		return ls;
	}
	
	public TrabalhoEntity insert(TrabalhoEntity trabalho){
		return getManager().merge(trabalho);
	}
	
	public void update(TrabalhoEntity entity){
		Query query = getManager().createQuery("UPDATE TrabalhoEntity SET nome = :nome, descricao = :descricao, numCurso = :numCurso, caminho = :caminho, nomearq = :nomearq " + "WHERE id = :id");
		query.setParameter("id", entity.getId());
		query.setParameter("nome", entity.getNome());
		query.setParameter("descricao", entity.getDescricao());
		query.setParameter("nomearq", entity.getNomearq());
		query.setParameter("caminho", entity.getCaminho());
		query.setParameter("numCurso", entity.getNumCurso());
		query.executeUpdate();
	}
	
	public void remove(TrabalhoEntity entity){
		Query query = getManager().createQuery("DELETE TrabalhoEntity WHERE id = :id ");
		query.setParameter("id", entity.getId());
		query.executeUpdate();
	}

	public void curtirTrabalho(TrabalhoUsuarioEntity entity) {
		getManager().merge(entity);
	}

	public void descurtirTrabalho(TrabalhoEntity trabalho, UsuarioEntity user) {
		Query query = getManager().createQuery("DELETE TrabalhoUsuarioEntity L WHERE L.usuario = :usuario AND L.trabalho = :trabalho");
		query.setParameter("trabalho", trabalho);
		query.setParameter("usuario", user);
		query.executeUpdate();
	}

	public boolean ValidaCurtir(TrabalhoEntity trabalho, UsuarioEntity user) {
		
		TypedQuery<TrabalhoUsuarioEntity> query = getManager()
				.createQuery("SELECT L FROM TrabalhoUsuarioEntity L " //JOIN L.grupo J JOIN L.usuario M "
						//+ "WHERE J.id = :idGroup AND M.id = :idUsuario ", GruposUsuarioEntity.class);
						+ "WHERE L.trabalho = :trabalho AND L.usuario = :usuario ", TrabalhoUsuarioEntity.class);
		query.setParameter("trabalho", trabalho);
		query.setParameter("usuario", user);
		
		List<TrabalhoUsuarioEntity> ls = query.getResultList();
		
		if(ls.size() > 0){
			return true;
		}
		
		return false;
	}

	public int qtdCurtidas(TrabalhoEntity trabalho) {
		
		TypedQuery<TrabalhoUsuarioEntity> query = getManager()
				.createQuery("SELECT L FROM TrabalhoUsuarioEntity L "
						+ "WHERE L.trabalho = :trabalho", TrabalhoUsuarioEntity.class);
		query.setParameter("trabalho", trabalho);
		
		List<TrabalhoUsuarioEntity> ls = query.getResultList();
		
		if(ls != null){
			return ls.size();
		}
		
		return 0;
	}
	
	public void removeTipoConteudo(TrabalhoEntity trabalho){
		Query query = getManager().createQuery("DELETE TipoConteudoTrabalhoEntity WHERE trabalho = :trabalho");
		query.setParameter("trabalho", trabalho);
		query.executeUpdate();
	}
	
	public void removeTimeLine(TipoConteudoTrabalhoEntity tct){
		Query query = getManager().createQuery("DELETE TimeLineEntity WHERE idTipoConteudoTrabalho = :tct");
		query.setParameter("tct", tct);
		query.executeUpdate();
	}
	
	public void removeTrabalhosUsuario(TrabalhoEntity trabalho){
		Query query = getManager().createQuery("DELETE TrabalhoUsuarioEntity WHERE trabalho = :trabalho");
		query.setParameter("trabalho", trabalho);
		query.executeUpdate();
	}
	
	public TipoConteudoTrabalhoEntity findTipoConteudoTrabalho(TrabalhoEntity trabalho){
		
		TypedQuery<TipoConteudoTrabalhoEntity> query = getManager()
				.createQuery("SELECT L FROM TipoConteudoTrabalhoEntity L JOIN L.trabalho "
						+ "WHERE L.trabalho = :trabalho", TipoConteudoTrabalhoEntity.class);
		
		query.setParameter("trabalho", trabalho);
		
		List<TipoConteudoTrabalhoEntity> itens = query.getResultList();
		
		return itens.get(0);
				
	}
	
	
}
