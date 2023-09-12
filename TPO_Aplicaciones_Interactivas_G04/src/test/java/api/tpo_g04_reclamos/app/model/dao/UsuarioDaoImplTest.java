package api.tpo_g04_reclamos.app.model.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import api.tpo_g04_reclamos.app.model.entity.Usuario;
import api.tpo_g04_reclamos.app.model.enums.TipoUsuario;

@SpringBootTest
public class UsuarioDaoImplTest {
	@Autowired
	IUsuarioDao usuarioDao;
	
	@Test
	@Order(1)
	public void saveTest() {
		Optional<Usuario> usuarioOptional = usuarioDao.findById(1);
		if(usuarioOptional.isEmpty()) {
			Usuario usuario = new Usuario("PepeUser", "PepePass", TipoUsuario.PERSONAL_INTERNO);
			usuarioDao.save(usuario);
		}
	}
	
	@Test
	@Order(2)
	public void findAllTest() {
		List<Usuario> usuarios = usuarioDao.findAll();
		assert(usuarios.size() > 0);
	}

	@Test
	@Order(3)
	public void findByIdTest() {
		int id = 1;
		Optional<Usuario> userOptional = usuarioDao.findById(id);
		assert(userOptional.isPresent());
	}

	@Test
	@Order(4)
	public void updateTest() {
		int id = 1; //le actualiza la fecha
		Optional<Usuario> usuarioExistenteOptional = usuarioDao.findById(id);

		Usuario usuarioExistente = usuarioExistenteOptional.orElseThrow();

		usuarioExistente.setFechaCreacion(new Date("01/01/01"));
		usuarioDao.update(usuarioExistente);
	}

	@Test
	@Order(5)
	public void deleteById() {
		int id = 1;
		// usuarioDao.deleteById(id);
	}
}
