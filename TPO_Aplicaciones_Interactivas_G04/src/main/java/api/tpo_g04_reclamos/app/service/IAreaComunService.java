package api.tpo_g04_reclamos.app.service;

import java.util.List;

import api.tpo_g04_reclamos.app.model.entity.AreaComun;

public interface IAreaComunService {

	List<AreaComun> findAll();
	
	AreaComun findById(int id);
	
	void save(AreaComun areaComun);
	
	void update(int id, AreaComun areaComun);
	
	void deleteById(int id);
	
}
