package api.tpo_g04_reclamos.app.service;

import java.util.List;
import java.util.Optional;

import api.tpo_g04_reclamos.app.model.entity.Edificio;

public interface IEdificioService {

	List<Edificio> findAll();
	
	Optional<Edificio> findById(Long id);
	
	Edificio save(Edificio edificio);
	
	Edificio update(Long id, Edificio edificio);
	
	void deleteById(Long id);
}
