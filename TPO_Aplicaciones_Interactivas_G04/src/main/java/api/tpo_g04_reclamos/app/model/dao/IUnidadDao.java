package api.tpo_g04_reclamos.app.model.dao;

import api.tpo_g04_reclamos.app.model.entity.Unidad;

import java.util.List;
import java.util.Optional;

public interface IUnidadDao {

	List<Unidad> findAll();
	
	Optional<Unidad> findById(Long id);

	List<Unidad> findAllByIds(List<Long> ids);

	Unidad save(Unidad unidad);

	Unidad update(Unidad unidad);

	void deleteById(Long id);
	
}
