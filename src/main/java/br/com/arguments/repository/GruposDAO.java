package br.com.arguments.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.GruposEntity;
import br.com.arguments.entity.GruposUsuarioEntity;
import br.com.arguments.entity.InstituicaoCursosEntity;
import br.com.arguments.entity.InstituicaoEntity;
import br.com.arguments.entity.TipoConteudoDebateEntity;
import br.com.arguments.entity.TipoConteudoGrupoEntity;
import br.com.arguments.entity.UsuarioEntity;

@Stateless
public class GruposDAO extends BaseDAO {
	
	public List<GruposEntity> findAllGrupos(){
	
		TypedQuery<GruposEntity> query = getManager()
				.createQuery("FROM GruposEntity", GruposEntity.class);
		
		List<GruposEntity> ls = query.getResultList();
		
		if(ls.isEmpty()){
			return null;
		}
		
		return ls;
	
	}
	
	public GruposEntity insert(GruposEntity grupo){
		return getManager().merge(grupo);
	}
	
	public void remove(GruposEntity grupo){
		Query query = getManager().createQuery("DELETE GruposEntity WHERE id = :id");
		query.setParameter("id", grupo.getId());
		query.executeUpdate();
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

	public List<InstituicaoEntity> findAllInstituicaoById(CursosEntity curso) {
		
		TypedQuery<InstituicaoCursosEntity> query = getManager()
				.createQuery("SELECT L FROM InstituicaoCursosEntity L JOIN L.idInstituicaoEntity "
						+ "WHERE L.idCursosEntity = :idCursosEntity ", InstituicaoCursosEntity.class);
		query.setParameter("idCursosEntity", curso);
		
		List<InstituicaoCursosEntity> itens = query.getResultList();
		
		if(itens != null){
			List<InstituicaoEntity> inst = new ArrayList<>();
			for(InstituicaoCursosEntity um : itens){
				inst.add(um.getIdInstituicaoEntity());
			}
			if(inst != null){
				return inst;
			}
		}
		return null;
	}

	public List<UsuarioEntity> findAllAlunosByInstituicao(Integer instituicaoSelecionado) {

		TypedQuery<UsuarioEntity> query = getManager()
				.createQuery("SELECT L FROM UsuarioEntity L JOIN L.idInstituicaoCursos J JOIN J.idInstituicaoEntity M "
						+ "WHERE M.id = :id ", UsuarioEntity.class);
		query.setParameter("id", new Long(instituicaoSelecionado));
		
		List<UsuarioEntity> ls = query.getResultList();
		
		if(ls != null){
			return ls;
		}
		
		return null;
	}

	public List<UsuarioEntity> findAllAlunosByCurso(Integer cursoSelecionado) {

		TypedQuery<UsuarioEntity> query = getManager()
				.createQuery("SELECT L FROM UsuarioEntity L JOIN L.idInstituicaoCursos J JOIN J.idCursosEntity M "
						+ "WHERE M.id = :id ", UsuarioEntity.class);
		query.setParameter("id", new Long(cursoSelecionado));
		
		List<UsuarioEntity> ls = query.getResultList();
		
		if(ls != null){
			return ls;
		}
		
		return null;
	}

	public void insertGruposCurso(GruposUsuarioEntity entity) {
		getManager().merge(entity);
	}

	public int findQtdMembrosGruposById(Long id) {


		TypedQuery<GruposUsuarioEntity> query = getManager()
				.createQuery("SELECT L FROM GruposUsuarioEntity L JOIN L.grupo J "
						+ "WHERE J.id = :id ", GruposUsuarioEntity.class);
		query.setParameter("id", id);
		
		List<GruposUsuarioEntity> ls = query.getResultList();
		
		if(ls != null){
			return ls.size();
		}
		
		return 0;
		
	}

	public boolean validaParticipacao(GruposEntity grupo, UsuarioEntity user) {

		TypedQuery<GruposUsuarioEntity> query = getManager()
				.createQuery("SELECT L FROM GruposUsuarioEntity L " //JOIN L.grupo J JOIN L.usuario M "
						//+ "WHERE J.id = :idGroup AND M.id = :idUsuario ", GruposUsuarioEntity.class);
						+ "WHERE L.grupo = :grupo AND L.usuario = :usuario ", GruposUsuarioEntity.class);
		query.setParameter("grupo", grupo);
		query.setParameter("usuario", user);
		
		List<GruposUsuarioEntity> ls = query.getResultList();
		
		if(ls.size() > 0){
			return true;
		}
		
		return false;
	}

	public GruposUsuarioEntity participarGrupos(GruposUsuarioEntity entity) {
		return getManager().merge(entity);
	}

	public void cancelarPparticipacaoGrupos(GruposEntity grupo, UsuarioEntity user) {
		Query query = getManager().createQuery("DELETE GruposUsuarioEntity L WHERE L.usuario = :usuario AND L.grupo = :grupo");
		query.setParameter("grupo", grupo);
		query.setParameter("usuario", user);
		query.executeUpdate();
	}
	
	public void removeTimeLine(TipoConteudoGrupoEntity tcg){
		Query query = getManager().createQuery("DELETE TimeLineEntity WHERE idTipoConteudoGrupo = :tcg");
		query.setParameter("tcg", tcg);
		query.executeUpdate();
	}
	
	public void removeTipoConteudo(GruposEntity grupo){
		Query query = getManager().createQuery("DELETE TipoConteudoGrupoEntity WHERE grupo = :grupo");
		query.setParameter("grupo",grupo);
		query.executeUpdate();
	}
	
	public void removeGrupoUsuario(GruposEntity grupo){
		Query query = getManager().createQuery("DELETE GruposUsuarioEntity WHERE grupo = :grupo");
		query.setParameter("grupo", grupo);
		query.executeUpdate();
	}
	
	public TipoConteudoGrupoEntity findTipoConteudoGrupos(GruposEntity grupo){
		
		TypedQuery<TipoConteudoGrupoEntity> query = getManager()
				.createQuery("SELECT L FROM TipoConteudoGrupoEntity L JOIN L.grupo "
						+ "WHERE L.grupo = :grupo", TipoConteudoGrupoEntity.class);
		
		query.setParameter("grupo", grupo);
		
		List<TipoConteudoGrupoEntity> itens = query.getResultList();
		
		return itens.get(0);
	}


}
