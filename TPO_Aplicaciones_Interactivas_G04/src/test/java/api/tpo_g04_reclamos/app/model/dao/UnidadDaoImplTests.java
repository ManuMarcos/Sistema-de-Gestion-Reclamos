package api.tpo_g04_reclamos.app.model.dao;

import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.model.entity.Unidad;
import api.tpo_g04_reclamos.app.model.entity.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;
import java.util.Optional;

import static api.tpo_g04_reclamos.app.model.enums.EstadoUnidad.ALQUILADA;
import static api.tpo_g04_reclamos.app.model.enums.EstadoUnidad.SIN_ALQUILAR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UnidadDaoImplTests {
	@Autowired
	private IUnidadDao unidadDao;

	private static Edificio edificioExistente;

	private static Usuario propietarioExistente1;

	private static Usuario propietarioExistente2;

	private static Usuario propietarioExistente3;

	@BeforeAll
	public static void setUp(@Autowired IEdificioDao edificioDao, @Autowired IUsuarioDao usuarioDao) {
		edificioExistente = edificioDao.save(new Edificio("direccion", List.of(), List.of()));
		propietarioExistente1 = usuarioDao.save(new Usuario());
		propietarioExistente2 = usuarioDao.save(new Usuario());
		propietarioExistente3 = usuarioDao.save(new Usuario());
	}

	@AfterEach
	public void clearDatabaseB(@Autowired JdbcTemplate jdbcTemplate) {
		System.out.println("borrando unidades...");
	    JdbcTestUtils.deleteFromTables(jdbcTemplate, "unidad");
	}
	
	@Test
	public void saveTest() {
		Unidad unidadCreada = unidadDao.save(new Unidad(3, 2, edificioExistente, ALQUILADA));

		Optional<Unidad> unidadGuardadaOptional = unidadDao.findById(unidadCreada.getId());

		assertTrue(unidadGuardadaOptional.isPresent());
		assertEquals(unidadCreada.getPiso(), unidadGuardadaOptional.get().getPiso());
		assertEquals(unidadCreada.getNumero(), unidadGuardadaOptional.get().getNumero());
		assertEquals(unidadCreada.getEstado(), unidadGuardadaOptional.get().getEstado());
	}
	
	@Test
	public void findAllTest() {
		Unidad unidadCreada1 = unidadDao.save(new Unidad(3, 2, edificioExistente, propietarioExistente1, ALQUILADA));
		Unidad unidadCreada2 = unidadDao.save(new Unidad(1, 4, edificioExistente, propietarioExistente2, SIN_ALQUILAR));
		Unidad unidadCreada3 = unidadDao.save(new Unidad(2, 3, edificioExistente, propietarioExistente3, ALQUILADA));

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
	}
}
