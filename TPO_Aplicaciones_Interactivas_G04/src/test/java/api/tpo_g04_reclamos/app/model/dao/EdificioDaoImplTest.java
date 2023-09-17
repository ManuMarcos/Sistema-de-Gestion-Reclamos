package api.tpo_g04_reclamos.app.model.dao;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static api.tpo_g04_reclamos.app.model.enums.EstadoReclamo.ABIERTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.model.entity.Reclamo;

@SpringBootTest
public class EdificioDaoImplTest {
	
	@Autowired
	private IEdificioDao edificioDao;
	
	
	@AfterEach
	public void clearDatabaseB(@Autowired JdbcTemplate jdbcTemplate) {
		System.out.println("borrando edificios...");
	    JdbcTestUtils.deleteFromTables(jdbcTemplate, "edificio");
	}
	
	
	@Test
	public void saveTest() {
		Edificio edificioNuevo = edificioDao.save(new Edificio("direccion", List.of(), List.of()));
	
		Optional<Edificio> edificioGuardado = edificioDao.findById(edificioNuevo.getId());
		
		assertTrue(edificioGuardado.isPresent());
	}
	
	@Test
	public void findAllTest() {
		Edificio edificioNuevo1 = edificioDao.save(new Edificio("direccion1", List.of(), List.of()));
		Edificio edificioNuevo2 = edificioDao.save(new Edificio("direccion2", List.of(), List.of()));
		Edificio edificioNuevo3 = edificioDao.save(new Edificio("direccion3", List.of(), List.of()));
		
		List<Edificio> edificiosNuevos = edificioDao.findAll();
		
		assertEquals(edificiosNuevos.size(), 3);
		assertTrue(edificiosNuevos.containsAll(List.of(
				new Edificio(edificioNuevo1.getId(),"direccion1", List.of(), List.of()),
				new Edificio(edificioNuevo2.getId(),"direccion2", List.of(), List.of()),
				new Edificio(edificioNuevo3.getId(),"direccion3", List.of(), List.of()))));
	}
	
	@Test
	public void findByIdTest() {
		Edificio edificioNuevo = edificioDao.save(new Edificio("direccion", List.of(), List.of()));
		
		Optional<Edificio> edificioBuscado = edificioDao.findById(edificioNuevo.getId());
		
		assertTrue(edificioBuscado.isPresent());
		assertEquals(edificioNuevo.getDireccion(), edificioBuscado.get().getDireccion());
		//assertEquals(edificioNuevo.getAreasComunes(), edificioBuscado.get().getAreasComunes());
		//assertEquals(edificioNuevo.getUnidades(), edificioBuscado.get().getUnidades());
	}
	
	
	@Test
	public void updateTest() {
		Edificio edificioNuevo = edificioDao.save(new Edificio("direccion", List.of(), List.of()));
		
		Optional<Edificio> edificioPreUpdate = edificioDao.findById(edificioNuevo.getId());
		
		edificioNuevo.setDireccion("direccion_update");
		edificioDao.save(edificioNuevo);
		
		Optional<Edificio> edificioPostUpdate = edificioDao.findById(edificioNuevo.getId());
		
		assertEquals("direccion", edificioPreUpdate.get().getDireccion());
		assertEquals("direccion_update", edificioPostUpdate.get().getDireccion());	
	}

	
	@Test
	public void deleteByIdTest() {
		Edificio edificioNuevo = edificioDao.save(new Edificio("direccion", List.of(), List.of()));
		
		Optional<Edificio> edificioPreDelete = edificioDao.findById(edificioNuevo.getId());
		
		edificioDao.deleteById(edificioNuevo.getId());
		
		Optional<Edificio> edificioPostDelete = edificioDao.findById(edificioNuevo.getId());
		
		assertTrue(edificioPreDelete.isPresent());
		assertTrue(edificioPostDelete.isEmpty());
	}
	
	
	
}
