package api.tpo_g04_reclamos.app.model.dao;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import api.tpo_g04_reclamos.app.model.entity.Usuario;

@SpringBootTest
public class UsuarioDaoTest {
	@Autowired
	UsuarioDao usuarioDao;
	
	@Test
	@Order(1)
	public void saveTest() {
		if(usuarioDao.findById(1) == null) {
			Usuario usuario = new Usuario("PepeUser", "PepePass");
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
		Usuario user = usuarioDao.findById(id);
		assert(user != null);
	}

	@Test
	@Order(4)
	public void updateTest() {
		int id = 1; //le actualiza la fecha
		Usuario usuarioExistente = usuarioDao.findById(id);
		usuarioExistente.setFechaCreacion(new Date());
		usuarioDao.save(usuarioExistente);
	}

	@Test
	@Order(5)
	public void deleteById() {
		int id = 1;
		// usuarioDao.deleteById(id);
	}
}
