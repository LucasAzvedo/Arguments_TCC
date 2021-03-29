package br.com.arguments.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.InstituicaoCursosEntity;
import br.com.arguments.entity.InstituicaoEntity;
import br.com.arguments.repository.InstituicaoDAO;

@Stateless
public class InstituicaoService {

	@EJB
	private InstituicaoDAO instituicaoDao;

	public List<InstituicaoEntity> findAllInstituicao() {
		return instituicaoDao.findAllInstituicao();
	}

	public List<InstituicaoCursosEntity> findCursosByInstituicao(InstituicaoEntity item) {
		return instituicaoDao.findCursosByInstituicao(item);
	}

	public List<CursosEntity> findAllCursos() {
		return instituicaoDao.findAllCursos();
	}
	
}
