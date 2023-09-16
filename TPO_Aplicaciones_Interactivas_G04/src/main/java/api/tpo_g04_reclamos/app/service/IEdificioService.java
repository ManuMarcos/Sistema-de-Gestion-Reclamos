package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.controller.dto.AreaComunDto;
import api.tpo_g04_reclamos.app.controller.dto.EdificioDto;
import api.tpo_g04_reclamos.app.controller.dto.UnidadDto;
import api.tpo_g04_reclamos.app.model.entity.Edificio;

import java.util.List;
import java.util.Optional;

public interface IEdificioService {

	List<Edificio> findAll();
	
	Optional<Edificio> findById(Long id);
	
	Edificio save(EdificioDto edificio);
	
	Edificio update(Long id, Edificio edificio);
	
	void deleteById(Long id);

	void addUnidad(Edificio edificio, UnidadDto unidad);

	void addAreaComun(Edificio edificio, AreaComunDto areaComunDto);

}
