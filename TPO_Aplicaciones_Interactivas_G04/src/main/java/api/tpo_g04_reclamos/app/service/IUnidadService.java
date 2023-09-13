package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.controller.dto.UnidadDto;
import api.tpo_g04_reclamos.app.model.entity.Unidad;

import java.util.List;
import java.util.Optional;

public interface IUnidadService {

	List<Unidad> findAll();
	
	Optional<Unidad> findById(Long id);

	Unidad save(UnidadDto unidadDto);

	Unidad update(Long id, UnidadDto unidadDto);
	
	void deleteById(Long id);
	
}
