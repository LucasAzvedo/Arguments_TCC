package br.com.arguments.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.arguments.dto.DebateDTO;
import br.com.arguments.entity.CursosEntity;
import br.com.arguments.entity.DebateCursoEntity;
import br.com.arguments.entity.DebateEntity;
import br.com.arguments.entity.TipoConteudoDebateEntity;
import br.com.arguments.entity.UsuarioEntity;
import br.com.arguments.repository.DebateDAO;

@Stateless
public class DebateService {

	@EJB
	private DebateDAO debateDao;

	public List<DebateEntity> findAllDebates() {
		return debateDao.findAllDebates();
	}

	public List<CursosEntity> findCursos() {
		return debateDao.findCursos();
	}

	public DebateEntity insert(DebateDTO dto, UsuarioEntity usuario) {
		DebateEntity debate = new DebateEntity();
		debate.setNome(dto.getNomeDebate());
		debate.setDataAbertura(dto.getDataCriacaoStamp());
		debate.setDataFechamento(dto.getDataFechamentoStamp());
		debate.setIdCurso(dto.getIdCursos());
		debate.setDataCriacao(dataAtual());
		debate.setIdUsuarioEntity(usuario);
		debate.setStatus(1);
		debate.setTema(dto.getTemaDebate());
		debate.setAssunto(dto.getAssunto());
		return debateDao.insert(debate);
	}	

	public Timestamp dataAtual() {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String dataAtual = format.format(new Date());
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date parsedDate = dateFormat.parse(dataAtual.toString());
			Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
			return timestamp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DebateCursoEntity insertComentarioDebate(UsuarioEntity user, DebateEntity debate, String comentario) {

		DebateCursoEntity entity = new DebateCursoEntity();

		entity.setComentario(comentario);
		entity.setIdDebateEntity(debate);
		entity.setIdUsuarioEntity(user);
		entity.setDataCriacao(dataAtual());

		return debateDao.insertComentarioDebate(entity);

	}

	public List<DebateCursoEntity> findAllDebatesByDebate(DebateEntity debate) {
		return debateDao.findAllDebatesByDebate(debate);
	}

	public void update(DebateDTO dto) {
		DebateEntity debate = new DebateEntity();
		debate.setId(dto.getId());
		debate.setNome(dto.getNomeDebate());
		debate.setDataAbertura(dto.getDataCriacaoStamp());
		debate.setDataFechamento(dto.getDataFechamentoStamp());
		debate.setIdCurso(dto.getIdCursos());
		debate.setTema(dto.getTemaDebate());
		debate.setAssunto(dto.getAssunto());
		debateDao.update(debate);

	}
	
	public void updateTipoConteudoDebate(DebateDTO dto){
		DebateEntity debate = new DebateEntity();
		debate.setId(dto.getId());
		debate.setDataAbertura(dto.getDataCriacaoStamp());
		debate.setDataFechamento(dto.getDataFechamentoStamp());
		debate.setNome(dto.getNomeDebate());
		debate.setTema(dto.getTemaDebate());
		debateDao.updateTipoConteudoDebate(debate);
	}

	public void remove(DebateEntity debate) {
		debateDao.remove(debate);
	}

	public void removeTipoConteudo(DebateEntity debate) {
		debateDao.removeTipoConteudo(debate);
	}

	public void removeComentariosDebate(DebateEntity debate) {
		debateDao.removeComentariosDebate(debate);
	}

	public void removeTimeLine(TipoConteudoDebateEntity tcd) {
		debateDao.removeTimeLine(tcd);
	}

	public TipoConteudoDebateEntity findTipoConteudoDebate(DebateEntity debate) {
		return debateDao.findTipoConteudoDebate(debate);
	}

	// public void remove(DebateEntity debate){
	// debateDao.remove(debate);
	// }
	//
	// public List<DebateCursoEntity> findDebatesByCursos(DebateEntity item) {
	// return debateDao.findDebatesByCursos(item);
	// }

}
