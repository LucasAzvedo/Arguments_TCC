package br.com.arguments.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.entity.TipoUsuarioEntity;
import br.com.arguments.repository.TipoUsuarioDAO;

@Stateless
public class TipoUsuarioService{

	@EJB
	private TipoUsuarioDAO dao;
	
	public TipoUsuarioEntity findId(Long l) {
		return dao.findId(l);
	}
	
	public List<TipoUsuarioEntity> findAllTipoUsuario(){
		return dao.findAllTipoUsuario();
	}
	
}
