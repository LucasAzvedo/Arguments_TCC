package br.com.arguments.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.dto.TrabalhoDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.TipoConteudoGrupoEntity;
import br.com.arguments.entity.TipoConteudoTrabalhoEntity;
import br.com.arguments.entity.TrabalhoEntity;
import br.com.arguments.entity.TrabalhoUsuarioEntity;
import br.com.arguments.entity.UsuarioEntity;
import br.com.arguments.repository.TrabalhoDAO;

@Stateless
public class TrabalhoService {
	
	@EJB
	private TrabalhoDAO trabalhoDAO;
	
	public List<TrabalhoEntity> findAllActive(){
		return trabalhoDAO.findAllActive();
	}
	
	public List<CursosEntity> findAllCursos(){
		return trabalhoDAO.findAllCursos();
	}
	
	public TrabalhoEntity insert(TrabalhoDTO dto){
		TrabalhoEntity trabalho = new TrabalhoEntity();
		trabalho.setNome(dto.getNome());
		trabalho.setDescricao(dto.getDescricao());
		trabalho.setCaminho(dto.getCaminho());
		trabalho.setNomearq(dto.getNomearq());
		trabalho.setNumCurso(dto.getCurso());
		trabalho.setAtivo(true);
		return trabalhoDAO.insert(trabalho);
	}
	
	public void remove(TrabalhoEntity entity){
		trabalhoDAO.remove(entity);
	}
	
	public void update(TrabalhoDTO dto){
		TrabalhoEntity trabalho = new TrabalhoEntity();
		trabalho.setId(dto.getId());
		trabalho.setNome(dto.getNome());
		trabalho.setDescricao(dto.getDescricao());
		trabalho.setCaminho(dto.getCaminho());
		trabalho.setNomearq(dto.getNomearq());
		trabalho.setNumCurso(dto.getCurso());
		trabalho.setAtivo(true);
		trabalhoDAO.update(trabalho);
	}

	public void curtirTrabalho(TrabalhoEntity trabalho, UsuarioEntity user) {
		TrabalhoUsuarioEntity entity = new TrabalhoUsuarioEntity();
		entity.setTrabalho(trabalho);
		entity.setUsuario(user);
		entity.setCurtir(true);
		
		trabalhoDAO.curtirTrabalho(entity);
	}

	public void descurtirTrabalho(TrabalhoEntity trabalho, UsuarioEntity user) {
		trabalhoDAO.descurtirTrabalho(trabalho,user);
	}

	public boolean ValidaCurtir(TrabalhoEntity trabalho, UsuarioEntity user) {
		return trabalhoDAO.ValidaCurtir(trabalho,user);
	}

	public int qtdCurtidas(TrabalhoEntity trabalho) {
		return trabalhoDAO.qtdCurtidas(trabalho);
	}
	
	public void removeTipoConteudo(TrabalhoEntity trabalho){
		trabalhoDAO.removeTipoConteudo(trabalho);
	}
	
	public void removeTimeLine(TipoConteudoTrabalhoEntity trabalho){
		trabalhoDAO.removeTimeLine(trabalho);
	}
	
	public void removeTrabalhoUsuario(TrabalhoEntity trabalho){
		trabalhoDAO.removeTrabalhosUsuario(trabalho);
	}
	
	public TipoConteudoTrabalhoEntity findTipoConteudoTrabalho(TrabalhoEntity trabalho){
		return trabalhoDAO.findTipoConteudoTrabalho(trabalho);
	}

}
