package api.tpo_g04_reclamos.app.model.dao;

import api.tpo_g04_reclamos.app.model.entity.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.HexFormat;
import java.util.List;
import java.util.Optional;

import static api.tpo_g04_reclamos.app.model.enums.EstadoReclamo.ABIERTO;
import static api.tpo_g04_reclamos.app.model.enums.EstadoReclamo.EN_PROCESO;
import static api.tpo_g04_reclamos.app.model.enums.EstadoUnidad.ALQUILADA;
import static api.tpo_g04_reclamos.app.model.enums.TipoUsuario.PROPIETARIO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ReclamoDaoImplTests {
	@Autowired
	private IReclamoDao reclamoDao;

	private static List<Imagen> imagenesExistentes;
	private static Usuario usuarioExistente;
	private static Unidad unidadExistente;
	private static AreaComun areaComunExistente;
	private static Edificio edificioExistente;

	@BeforeAll
	public static void setUp(@Autowired IImagenDao imagenDao, @Autowired IUsuarioDao usuarioDao, @Autowired IUnidadDao unidadDao,
							 @Autowired IAreaComunDao areaComunDao, @Autowired IEdificioDao edificioDao) {
		imagenesExistentes = List.of(
								imagenDao.save(new Imagen("nombre1", "tipo1", HexFormat.of().parseHex("e04fd020ea3a6910a2d808002b30309d"))),
								imagenDao.save(new Imagen("nombre2", "tipo2", HexFormat.of().parseHex("e04fd020ea3a6910a2d808002b30309d"))),
								imagenDao.save(new Imagen("nombre3", "tipo3", HexFormat.of().parseHex("e04fd020ea3a6910a2d808002b30309d"))));
		usuarioExistente = usuarioDao.save(new Usuario("nombre", "password", PROPIETARIO));
		edificioExistente = edificioDao.save(new Edificio());
		unidadExistente = unidadDao.save(new Unidad(3, 2, edificioExistente, usuarioExistente, ALQUILADA));
		areaComunExistente = areaComunDao.save(new AreaComun(edificioExistente, "nombreAreaComun"));
	}

	@AfterEach
	public void clearDatabaseB(@Autowired JdbcTemplate jdbcTemplate) {
		System.out.println("borrando reclamos...");
	    JdbcTestUtils.deleteFromTables(jdbcTemplate, "reclamos");
	}

	@AfterAll
	public static void deleteAll(@Autowired JdbcTemplate jdbcTemplate) {
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "areas_comunes");
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "unidades");
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "edificios");
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "imagenes");
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "usuarios");
	}

	@Test
	public void saveTest() {
		Reclamo reclamoCreado = reclamoDao.save(new Reclamo(3, imagenesExistentes, "description", ABIERTO, usuarioExistente, unidadExistente, areaComunExistente));

		Optional<Reclamo> reclamoGuardadoOptional = reclamoDao.findById(reclamoCreado.getId());

		assertTrue(reclamoGuardadoOptional.isPresent());
		assertEquals(reclamoCreado.getNumero(), reclamoGuardadoOptional.get().getNumero());
		assertEquals(reclamoCreado.getEstado(), reclamoGuardadoOptional.get().getEstado());
		assertEquals(reclamoCreado.getUsuario(), reclamoGuardadoOptional.get().getUsuario());
		assertEquals(reclamoCreado.getUnidad(), reclamoGuardadoOptional.get().getUnidad());
		assertEquals(reclamoCreado.getAreaComun(), reclamoGuardadoOptional.get().getAreaComun());
	}
	
	@Test
	public void findAllTest() {
		Reclamo reclamoCreado1 = reclamoDao.save(new Reclamo(3, imagenesExistentes, "description1", ABIERTO, usuarioExistente, unidadExistente, areaComunExistente));
		Reclamo reclamoCreado2 = reclamoDao.save(new Reclamo(1, imagenesExistentes, "description1", EN_PROCESO, null, null, null));

		List<Reclamo> reclamosCreados = reclamoDao.findAll();

		assertEquals(reclamosCreados.size(), 2);
		assertTrue(reclamosCreados.containsAll(List.of(
				new Reclamo(reclamoCreado1.getId(), 3, imagenesExistentes, "description1", ABIERTO, usuarioExistente, unidadExistente, areaComunExistente),
				new Reclamo(reclamoCreado2.getId(), 1, imagenesExistentes, "description1", EN_PROCESO, null, null, null))));
	}

	@Test
	public void updateTest() {
		Reclamo reclamoCreado = reclamoDao.save(new Reclamo(3, imagenesExistentes, "descripcion", ABIERTO, usuarioExistente, unidadExistente, areaComunExistente));

		Optional<Reclamo> reclamoAntesDeActualizar = reclamoDao.findById(reclamoCreado.getId());

		reclamoCreado.setDescripcion("nuevaDescripcion");
		reclamoCreado.setNumero(1);
		reclamoDao.update(reclamoCreado);

		Optional<Reclamo> reclamoDespuesDeActualizar = reclamoDao.findById(reclamoCreado.getId());

		assertEquals("descripcion", reclamoAntesDeActualizar.get().getDescripcion());
		assertEquals("nuevaDescripcion", reclamoDespuesDeActualizar.get().getDescripcion());
		assertEquals(3, reclamoAntesDeActualizar.get().getNumero());
		assertEquals(1, reclamoDespuesDeActualizar.get().getNumero());
	}

	@Test
	public void deleteById() {
		Reclamo reclamoCreado = reclamoDao.save(new Reclamo(3, imagenesExistentes, "descripcion", ABIERTO, usuarioExistente, unidadExistente, areaComunExistente));

		Optional<Reclamo> reclamoAntesDeBorrar = reclamoDao.findById(reclamoCreado.getId());

		reclamoDao.deleteById(reclamoCreado.getId());

		Optional<Reclamo> reclamoDespuesDeBorrar = reclamoDao.findById(reclamoCreado.getId());

		assertTrue(reclamoAntesDeBorrar.isPresent());
		assertTrue(reclamoDespuesDeBorrar.isEmpty());
	}
}
