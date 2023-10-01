package api.tpo_g04_reclamos.app.model.dao;

import api.tpo_g04_reclamos.app.model.entity.Reclamo;
import api.tpo_g04_reclamos.app.model.enums.EstadoReclamo;

import java.util.List;
import java.util.Optional;

public interface IReclamoDao {

    List<Reclamo> findAll();

    Optional<Reclamo> findById(Long id);

    List<Reclamo> findByEstado(EstadoReclamo estado);

    Reclamo save(Reclamo usuario);

    Reclamo update(Reclamo usuario);

    void deleteById(Long id);

}
