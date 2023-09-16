package api.tpo_g04_reclamos.app.model.dao;

import api.tpo_g04_reclamos.app.model.entity.Edificio;

import java.util.List;
import java.util.Optional;

public interface IEdificioDao {

	List<Edificio> findAll();
	
	Optional<Edificio> findById(Long id);
	
	Edificio save(Edificio edificio);
	
	void deleteById(Long id);
}
