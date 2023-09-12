package api.tpo_g04_reclamos.app.model.dao;

import java.util.List;
import java.util.Optional;

import api.tpo_g04_reclamos.app.model.entity.Edificio;

public interface IEdificioDao {

	List<Edificio> findAll();
	
	Optional<Edificio> findById(int id);
	
	void save(Edificio edificio);
	
	void deleteById(int id);
}
