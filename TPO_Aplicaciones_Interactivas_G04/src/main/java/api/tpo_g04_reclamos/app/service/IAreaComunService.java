package api.tpo_g04_reclamos.app.service;

import java.util.List;
import java.util.Optional;

import api.tpo_g04_reclamos.app.model.entity.AreaComun;

public interface IAreaComunService {

	List<AreaComun> findAll();
	
	Optional<AreaComun> findById(int id);
	
	void save(AreaComun areaComun);
	
	void update(int id, AreaComun areaComun);
	
	void deleteById(int id);
	
}
