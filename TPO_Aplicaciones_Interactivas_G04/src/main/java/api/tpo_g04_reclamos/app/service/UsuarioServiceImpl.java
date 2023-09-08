package api.tpo_g04_reclamos.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.tpo_g04_reclamos.app.model.dao.IUsuarioDao;
import api.tpo_g04_reclamos.app.model.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	IUsuarioDao usuarioDao;
	
	@Override
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}

	@Override
	public Usuario findById(int id) {
		return usuarioDao.findById(id);
	}

	@Override
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}

	@Override
	public void update(Usuario usuario) {
		usuarioDao.update(usuario);
	}

	@Override
	public void deleteById(int id) {
		usuarioDao.deleteById(id);
	}

}
