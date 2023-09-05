package api.tpo_g04_reclamos.app.service;

import java.util.List;

import api.tpo_g04_reclamos.app.model.entity.Edificio;

public interface IEdificioService {

	List<Edificio> findAll();
	
	Edificio findById(int id);
	
	void save(Edificio edificio);
	
	void update(int id, Edificio edificio);
	
	void deleteById(int id);
}
