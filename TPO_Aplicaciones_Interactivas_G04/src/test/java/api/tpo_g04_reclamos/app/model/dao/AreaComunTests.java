package api.tpo_g04_reclamos.app.model.dao;

import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import api.tpo_g04_reclamos.app.model.entity.Edificio;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AreaComunTests {

    @Autowired
    private IAreaComunDao areaComunDao;

    private static Edificio edificioExistente;

    @BeforeAll
    public static void setUp(@Autowired IEdificioDao edificioDao) {
        edificioExistente = edificioDao.save(new Edificio("direccion", List.of(), List.of()));
    }

    @AfterEach
    public void clearDatabaseB(@Autowired JdbcTemplate jdbcTemplate) {
        System.out.println("borrando reclamos...");
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "area_comun");
    }

    @AfterAll
    public static void deleteAll(@Autowired JdbcTemplate jdbcTemplate) {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "edificio");
    }

    @Test
    public void saveTest() {
        AreaComun areaComunCreada = areaComunDao.save(new AreaComun(edificioExistente, "nombre"));

        Optional<AreaComun> areaComunGuardadaOptional = areaComunDao.findById(areaComunCreada.getId());

        assertTrue(areaComunGuardadaOptional.isPresent());
        assertEquals(areaComunCreada.getId(), areaComunGuardadaOptional.get().getId());
        assertEquals(areaComunCreada.getEdificio(), areaComunGuardadaOptional.get().getEdificio());
        assertEquals(areaComunCreada.getNombre(), areaComunGuardadaOptional.get().getNombre());
    }

    @Test
    public void findAllTest() {
        AreaComun areaComunCreada1 = areaComunDao.save(new AreaComun(edificioExistente, "nombre1"));
        AreaComun areaComunCreada2 = areaComunDao.save(new AreaComun(edificioExistente, "nombre2"));
        AreaComun areaComunCreada3 = areaComunDao.save(new AreaComun(edificioExistente, "nombre3"));

        List<AreaComun> areasComunesCreadas = areaComunDao.findAll();

        assertEquals(areasComunesCreadas.size(), 3);
        assertTrue(areasComunesCreadas.containsAll(List.of(
                new AreaComun(areaComunCreada1.getId(), edificioExistente, "nombre1"),
                new AreaComun(areaComunCreada2.getId(), edificioExistente, "nombre2"),
                new AreaComun(areaComunCreada3.getId(), edificioExistente, "nombre3"))));
    }

    @Test
    public void findByIdTest() {
        AreaComun areaComunCreada = areaComunDao.save(new AreaComun(edificioExistente, "nombre"));

        Optional<AreaComun> areaComunGuardadaOptional = areaComunDao.findById(areaComunCreada.getId());

        assertTrue(areaComunGuardadaOptional.isPresent());
        assertEquals(areaComunCreada.getId(), areaComunGuardadaOptional.get().getId());
        assertEquals(areaComunCreada.getEdificio(), areaComunGuardadaOptional.get().getEdificio());
        assertEquals(areaComunCreada.getNombre(), areaComunGuardadaOptional.get().getNombre());
    }

    @Test
    public void updateTest() {
        AreaComun areaComunCreada = areaComunDao.save(new AreaComun(edificioExistente, "nombre"));

        Optional<AreaComun> areaComunAntesDeActualizar = areaComunDao.findById(areaComunCreada.getId());

        areaComunCreada.setNombre("nuevoNombre");
        areaComunDao.update(areaComunCreada);

        Optional<AreaComun> areaComunDespuesDeActualizar = areaComunDao.findById(areaComunCreada.getId());

        assertEquals("nombre", areaComunAntesDeActualizar.get().getNombre());
        assertEquals("nuevoNombre", areaComunDespuesDeActualizar.get().getNombre());
    }

    @Test
    public void deleteById() {
        AreaComun areaComunCreada = areaComunDao.save(new AreaComun(edificioExistente, "nombre"));

        Optional<AreaComun> areaComunAntesDeBorrar = areaComunDao.findById(areaComunCreada.getId());

        areaComunDao.deleteById(areaComunCreada.getId());

        Optional<AreaComun> areaComunDespuesDeBorrar = areaComunDao.findById(areaComunCreada.getId());

        assertTrue(areaComunAntesDeBorrar.isPresent());
        assertTrue(areaComunDespuesDeBorrar.isEmpty());
    }

}
