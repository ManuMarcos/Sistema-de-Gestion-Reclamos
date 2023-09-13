package api.tpo_g04_reclamos.app.model.dao;

import java.util.List;
import java.util.Optional;

import api.tpo_g04_reclamos.app.model.entity.Edificio;

public interface IEdificioDao {

	List<Edificio> findAll();
	
	Optional<Edificio> findById(Long id);
	
	Edificio save(Edificio edificio);
	
	void deleteById(Long id);
}
