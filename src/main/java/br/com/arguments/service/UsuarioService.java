package br.com.arguments.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.repository.UsuarioDAO;
import br.com.arguments.entity.TipoUsuarioEntity;
import br.com.arguments.entity.UsuarioEntity;

@Stateless
public class UsuarioService {

	@EJB
	private UsuarioDAO usuarioDAO;
	
	public List<UsuarioEntity> findAllUsers() {
		return usuarioDAO.findAllUsers();
	}

	public List<TipoUsuarioEntity> findAllTipoUsuario() {
		return usuarioDAO.findAllTipoUsuario();
	}

	public void updatePermissao(UsuarioEntity usuarioEditado, TipoUsuarioEntity tipoUsuario) {
		usuarioDAO.updatePermissao(usuarioEditado,tipoUsuario);
	}

	public UsuarioEntity findUserById(Long id) {
		return usuarioDAO.findUserById(id);
	}
	
	public void update(UsuarioEntity user) {
		usuarioDAO.uptade(user);
	}
		
	public boolean ativeUser(UsuarioEntity usuario) {
		return usuarioDAO.ativeUser(usuario);
	}

	public boolean inativeUser(UsuarioEntity usuario) {
		return usuarioDAO.inativeUser(usuario);
	}
	
}
