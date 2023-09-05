package api.tpo_g04_reclamos.app.service;

import java.util.List;

import api.tpo_g04_reclamos.app.model.entity.Edificio;

public interface IEdificioService {

	public List<Edificio> findAll();
	
	public Edificio findById(int id);
	
	public void save(Edificio edificio);
	
	public void update(int id, Edificio edificio);
	
	public void deleteById(int id);
}
