package api.tpo_g04_reclamos.app.service;

import java.util.List;
import java.util.Optional;

import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.tpo_g04_reclamos.app.model.dao.IUsuarioDao;
import api.tpo_g04_reclamos.app.model.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}

	@Override
	public Optional<Usuario> findById(Long id) {
		return usuarioDao.findById(id);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	public Usuario update(Usuario usuario) {
		this.usuarioExiste(usuario.getId());

		return usuarioDao.update(usuario);
	}

	@Override
	public void deleteById(Long id) {
		this.usuarioExiste(id);

		usuarioDao.deleteById(id);
	}

	private boolean usuarioExiste(Long id) {
		Optional<Usuario> usuario = this.findById(id);

		if(usuario.isEmpty()) {
			throw new ItemNotFoundException("El usuario no existe");
		}

		return true;
	}

}
