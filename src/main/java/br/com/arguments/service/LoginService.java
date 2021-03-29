package br.com.arguments.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.repository.LoginDAO;
import br.com.arguments.entity.LoginEntity;

@Stateless
public class LoginService {

	@EJB
	private LoginDAO loginDAO;
	
	public LoginEntity findLogin(String user){
		return loginDAO.findLogin(user);
	}
	
	public LoginEntity insert(LoginEntity login){
		return loginDAO.save(login);
	}
	
	public void update(LoginEntity loginEntity){
		loginDAO.uptade(loginEntity);	
	}
	
	public LoginEntity findById(int id) {
		return loginDAO.findById(id);
	}
	
}
