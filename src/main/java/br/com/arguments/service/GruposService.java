package br.com.arguments.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.dto.GruposDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.GruposEntity;
import br.com.arguments.entity.GruposUsuarioEntity;
import br.com.arguments.entity.InstituicaoEntity;
import br.com.arguments.entity.LoginEntity;
import br.com.arguments.entity.TipoConteudoDebateEntity;
import br.com.arguments.entity.TipoConteudoGrupoEntity;
import br.com.arguments.entity.UsuarioEntity;
import br.com.arguments.repository.GruposDAO;

@Stateless
public class GruposService {
	
	@EJB
	private GruposDAO grupoDAO;
	
	public List<GruposEntity> findAllGrupos(){
		return grupoDAO.findAllGrupos();
	}
	
	public GruposEntity insert(GruposDTO gruposDTO, LoginEntity usuario){
		GruposEntity grupos = new GruposEntity();
		grupos.setNome(gruposDTO.getNomeGrupo());
		grupos.setDescricao(gruposDTO.getDescricao());
		grupos.setCurso(gruposDTO.getCurso());
		grupos.setInstituicao(gruposDTO.getInstituicao());
		grupos.setTipoGrupo(gruposDTO.getTipoGrupo());
		grupos.setQtdMaximaMembros(gruposDTO.getQtdMaximaMembros());
		grupos.setUsuario(usuario.getIdUsuario());
		grupos.setDataCriacao(dataAtual());
		
//		grupos.setPrivacidade(gruposDTO.getPrivacidade());
//		grupos.setMembros(gruposDTO.getMembros());
//		grupos.setIdUsuarioEntity(gruposDTO.getUsuario());
		
		return grupoDAO.insert(grupos);
	}
	
	public Timestamp dataAtual(){
		try{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
			String dataAtual = format.format(new Date());
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		    Date parsedDate = dateFormat.parse(dataAtual.toString());
		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		    return timestamp;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void removeGrupos(GruposEntity grupos){
		grupoDAO.remove(grupos);
	}

	public List<CursosEntity> findAllCursos() {
		return grupoDAO.findAllCursos();
	}

	public List<InstituicaoEntity> findAllInstituicaoById(CursosEntity curso) {
		return grupoDAO.findAllInstituicaoById(curso);
	}

	public List<UsuarioEntity> findAllAlunosByInstituicao(Integer instituicaoSelecionado) {
		return grupoDAO.findAllAlunosByInstituicao(instituicaoSelecionado);
	}

	public List<UsuarioEntity> findAllAlunosByCurso(Integer cursoSelecionado) {
		return grupoDAO.findAllAlunosByCurso(cursoSelecionado);
	}

	public void insertGruposCurso(GruposEntity grupo, UsuarioEntity curso) {
		GruposUsuarioEntity entity = new GruposUsuarioEntity();
		entity.setUsuario(curso);
		entity.setGrupo(grupo);
		grupoDAO.insertGruposCurso(entity);
	}

	public int findQtdMembrosGruposById(Long id) {
		return grupoDAO.findQtdMembrosGruposById(id);
	}

	public boolean validaParticipacao(GruposEntity grupo, UsuarioEntity user) {
		return grupoDAO.validaParticipacao(grupo,user);
	}

	public GruposUsuarioEntity participarGrupos(GruposEntity grupo, UsuarioEntity user) {
		
		GruposUsuarioEntity entity = new GruposUsuarioEntity();
		entity.setGrupo(grupo);
		entity.setUsuario(user);
		
		return grupoDAO.participarGrupos(entity);
		
	}

	public void cancelarPparticipacaoGrupos(GruposEntity grupo, UsuarioEntity user) {
		grupoDAO.cancelarPparticipacaoGrupos(grupo,user);
		
	}
	
	public void removeTipoGrupo(GruposEntity grupo){
		grupoDAO.removeTipoConteudo(grupo);
	}
	
	public void removeTimeLine(TipoConteudoGrupoEntity tcg){
		grupoDAO.removeTimeLine(tcg);
	}
	
	public void removeGruposUsuario(GruposEntity grupo){
		grupoDAO.removeGrupoUsuario(grupo);
	}
	
	public TipoConteudoGrupoEntity findTipoConteudoGrupo(GruposEntity grupo){
		return grupoDAO.findTipoConteudoGrupos(grupo);
	}

}
