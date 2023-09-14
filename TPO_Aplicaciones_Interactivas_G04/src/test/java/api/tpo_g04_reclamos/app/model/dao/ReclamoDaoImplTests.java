package api.tpo_g04_reclamos.app.model.dao;

import api.tpo_g04_reclamos.app.model.entity.*;
import api.tpo_g04_reclamos.app.model.enums.EstadoReclamo;
import api.tpo_g04_reclamos.app.model.enums.EstadoUnidad;
import api.tpo_g04_reclamos.app.model.enums.TipoUsuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.awt.*;
import java.util.HexFormat;
import java.util.List;
import java.util.Optional;

import static api.tpo_g04_reclamos.app.model.enums.EstadoReclamo.ABIERTO;
import static api.tpo_g04_reclamos.app.model.enums.EstadoUnidad.ALQUILADA;
import static api.tpo_g04_reclamos.app.model.enums.EstadoUnidad.SIN_ALQUILAR;
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
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "area_comun");
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "unidad");
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "edificio");
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "imagenes");
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
	
	/*@Test
	public void findAllTest() {
		Unidad unidadCreada1 = unidadDao.save(unidadDao.save(new Unidad(3, 2, edificioExistente, propietarioExistente1, ALQUILADA)));
		Unidad unidadCreada2 = unidadDao.save(unidadDao.save(new Unidad(1, 4, edificioExistente, propietarioExistente2, SIN_ALQUILAR)));
		Unidad unidadCreada3 = unidadDao.save(unidadDao.save(new Unidad(2, 3, edificioExistente, propietarioExistente3, ALQUILADA)));

		List<Unidad> unidadesCreadas = unidadDao.findAll();

		assertEquals(unidadesCreadas.size(), 3);
		assertTrue(unidadesCreadas.containsAll(List.of(
				new Unidad(unidadCreada1.getId(), 3, 2, edificioExistente, propietarioExistente1, ALQUILADA),
				new Unidad(unidadCreada2.getId(), 1, 4, edificioExistente, propietarioExistente2, SIN_ALQUILAR),
				new Unidad(unidadCreada3.getId(), 2, 3, edificioExistente, propietarioExistente3, ALQUILADA))));
	}

	@Test
	public void findByIdTest() {
		Unidad unidadCreada = unidadDao.save(new Unidad(1, 3, edificioExistente, SIN_ALQUILAR));

		Optional<Unidad> unidadGuardadaOptional = unidadDao.findById(unidadCreada.getId());

		assertTrue(unidadGuardadaOptional.isPresent());
		assertEquals(unidadCreada.getPiso(), unidadGuardadaOptional.get().getPiso());
		assertEquals(unidadCreada.getNumero(), unidadGuardadaOptional.get().getNumero());
		assertEquals(unidadCreada.getEstado(), unidadGuardadaOptional.get().getEstado());
	}

	@Test
	public void updateTest() {
		Unidad unidadCreada = unidadDao.save(new Unidad(1, 3, edificioExistente, SIN_ALQUILAR));

		Optional<Unidad> unidadAntesDeActualizar = unidadDao.findById(unidadCreada.getId());

		unidadCreada.setPiso(3);
		unidadCreada.setNumero(1);
		unidadDao.update(unidadCreada);

		Optional<Unidad> unidadDespuesDeActualizar = unidadDao.findById(unidadCreada.getId());

		assertEquals(1, unidadAntesDeActualizar.get().getPiso());
		assertEquals(3, unidadDespuesDeActualizar.get().getPiso());
		assertEquals(3, unidadAntesDeActualizar.get().getNumero());
		assertEquals(1, unidadDespuesDeActualizar.get().getNumero());
	}

	@Test
	public void deleteById() {
		Unidad unidadCreada = unidadDao.save(new Unidad(2, 5, edificioExistente, SIN_ALQUILAR));

		Optional<Unidad> unidadAntesDeBorrar = unidadDao.findById(unidadCreada.getId());

		unidadDao.deleteById(unidadCreada.getId());

		Optional<Unidad> unidadDespuesDeBorrar = unidadDao.findById(unidadCreada.getId());

		assertTrue(unidadAntesDeBorrar.isPresent());
		assertTrue(unidadDespuesDeBorrar.isEmpty());
	}*/
}
