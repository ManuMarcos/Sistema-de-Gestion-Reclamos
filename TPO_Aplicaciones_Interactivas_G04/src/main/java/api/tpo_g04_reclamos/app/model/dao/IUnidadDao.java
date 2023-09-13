package api.tpo_g04_reclamos.app.model.dao;

import api.tpo_g04_reclamos.app.model.entity.Unidad;

import java.util.List;
import java.util.Optional;

public interface IUnidadDao {

	List<Unidad> findAll();
	
	Optional<Unidad> findById(Long id);

	Unidad save(Unidad unidad);
	
	void deleteById(Long id);
	
}
