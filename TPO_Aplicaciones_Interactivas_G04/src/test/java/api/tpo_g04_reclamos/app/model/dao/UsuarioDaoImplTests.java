package api.tpo_g04_reclamos.app.model.dao;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import api.tpo_g04_reclamos.app.model.entity.Usuario;

import static api.tpo_g04_reclamos.app.model.enums.TipoUsuario.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UsuarioDaoImplTests {
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@AfterEach
	public void clearDatabaseB(@Autowired JdbcTemplate jdbcTemplate) {
		System.out.println("borrando usuarios...");
	    JdbcTestUtils.deleteFromTables(jdbcTemplate, "usuario");
	}
	
	@Test
	public void saveTest() {
		Usuario usuarioCreado = usuarioDao.save(new Usuario("PepeUser", "PepePass", PERSONAL_INTERNO));

		Optional<Usuario> usuarioGuardadoOptional = usuarioDao.findById(usuarioCreado.getId());

		assertTrue(usuarioGuardadoOptional.isPresent());
		assertEquals(usuarioCreado.getNombre(), usuarioGuardadoOptional.get().getNombre());
		assertEquals(usuarioCreado.getPassword(), usuarioGuardadoOptional.get().getPassword());
		assertEquals(usuarioCreado.getTipoUsuario(), usuarioGuardadoOptional.get().getTipoUsuario());
	}
	
	@Test
	public void findAllTest() {
		Usuario usuarioCreado1 = usuarioDao.save(new Usuario( "User1", "Pass1", PERSONAL_INTERNO));
		Usuario usuarioCreado2 = usuarioDao.save(new Usuario("User2", "Pass2", INQUILINO));
		Usuario usuarioCreado3 = usuarioDao.save(new Usuario("User3", "Pass3", PROPIETARIO));

		List<Usuario> usuariosCreados = usuarioDao.findAll();

		assertEquals(usuariosCreados.size(), 3);
		assertTrue(usuariosCreados.containsAll(List.of(
				new Usuario(usuarioCreado1.getId(), "User1", "Pass1", PERSONAL_INTERNO),
				new Usuario(usuarioCreado2.getId(),"User2", "Pass2", INQUILINO),
				new Usuario(usuarioCreado3.getId(), "User3", "Pass3", PROPIETARIO))));
	}

	@Test
	public void findByIdTest() {
		Usuario usuarioCreado = usuarioDao.save(new Usuario( "User", "Pass", PERSONAL_INTERNO));

		Optional<Usuario> userOptional = usuarioDao.findById(usuarioCreado.getId());

		assertTrue(userOptional.isPresent());
		assertEquals(usuarioCreado.getNombre(), userOptional.get().getNombre());
		assertEquals(usuarioCreado.getPassword(), userOptional.get().getPassword());
		assertEquals(usuarioCreado.getTipoUsuario(), userOptional.get().getTipoUsuario());
	}

	@Test
	public void updateTest() {
		Usuario usuarioCreado = usuarioDao.save(new Usuario( "User", "Pass", PERSONAL_INTERNO));

		Optional<Usuario> usuarioAntesDeActualizar = usuarioDao.findById(usuarioCreado.getId());

		usuarioCreado.setNombre("NuevoUser");
		usuarioDao.update(usuarioCreado);

		Optional<Usuario> usuarioDespuesDeActualizar = usuarioDao.findById(usuarioCreado.getId());

		assertEquals("User", usuarioAntesDeActualizar.get().getNombre());
		assertEquals("NuevoUser", usuarioDespuesDeActualizar.get().getNombre());
	}

	@Test
	public void deleteById() {
		Usuario usuarioCreado = usuarioDao.save(new Usuario( "User", "Pass", PERSONAL_INTERNO));

		Optional<Usuario> usuarioAntesDeBorrar = usuarioDao.findById(usuarioCreado.getId());

		usuarioDao.deleteById(usuarioCreado.getId());

		Optional<Usuario> usuarioDespuesDeBorrar = usuarioDao.findById(usuarioCreado.getId());

		assertTrue(usuarioAntesDeBorrar.isPresent());
		assertTrue(usuarioDespuesDeBorrar.isEmpty());
	}
}
