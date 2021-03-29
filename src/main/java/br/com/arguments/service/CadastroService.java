package br.com.arguments.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.dto.LoginUsuarioDTO;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.UsuarioEntity;
import br.com.arguments.repository.CadastroDAO;
import br.com.arguments.util.jsf.criptografia;

@Stateless
public class CadastroService {

	@EJB
	private CadastroDAO cadastroDAO;
	
	@EJB
	private LoginService loginService;
	
	@EJB
	private TipoUsuarioService tipoUsuarioService;
	
	public LoginEntity insert(LoginUsuarioDTO user){
		UsuarioEntity usuario=  new UsuarioEntity();
		usuario.setNome(user.getNome());
		usuario.setSobrenome(user.getSobrenome());
		usuario.setEmail(user.getEmail());
		usuario.setRa(user.getRa());
		usuario.setTipoUsuario(user.getTipoUsuario());
		usuario.setIdInstituicaoCursos(user.getInstituicaoCurso());
		LoginEntity login = new LoginEntity();
		login.setSenha(criptografia.md5( user.getSenha()));
		login.setIdUsuario(cadastroDAO.insertUser(usuario));
		if(user.getRa() != null){
			login.setUsuario(user.getRa().toString());
		} else {
			login.setUsuario(user.getEmail());
		}
		return loginService.insert(login);
	}
	
	public UsuarioEntity findUserById(Long id) {
		return cadastroDAO.findUserById(id);
	}
	
}
