package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.controller.dto.UsuarioDto;
import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.dao.IUsuarioDao;
import api.tpo_g04_reclamos.app.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}

	@Override
	public Optional<Usuario> findById(Long id) {
		return usuarioDao.findById(id);
	}

	@Override
	public Usuario get(Long id) {
		return findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("El usuario %d no existe", id)));
	}

	@Override
	public Optional<Usuario> findUser(String username, String password) {
		// TODO Auto-generated method stub
		return usuarioDao.findUser(username, password);
	}

	@Override
	public Usuario save(UsuarioDto usuario) {
		return usuarioDao.save(new Usuario(usuario.getNombre(), passwordEncoder.encode(usuario.getPassword()), usuario.getTipoUsuario()));
	}

	@Override
	public Usuario update(Long id, UsuarioDto usuarioDto) {
		Usuario usuario = this.get(id);
		usuario.setNombre(usuarioDto.getNombre());
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		usuario.setTipoUsuario(usuarioDto.getTipoUsuario());

		return usuarioDao.update(usuario);
	}

	@Override
	public void deleteById(Long id) {
		this.usuarioExiste(id);

		usuarioDao.deleteById(id);
	}

	private boolean usuarioExiste(Long id) {
		if(this.findById(id).isEmpty()) {
			throw new ItemNotFoundException(String.format("El usuario %d no existe", id));
		}

		return true;
	}

	

}
