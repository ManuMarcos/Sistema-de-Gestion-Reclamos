package api.tpo_g04_reclamos.app.model.dao;

import java.util.List;
import java.util.Optional;

import api.tpo_g04_reclamos.app.model.entity.AreaComun;

public interface IAreaComunDao {

	List<AreaComun> findAll();
	
	Optional<AreaComun> findById(int id);
	
	void save(AreaComun areaComun);
	
	void deleteById(int id);
	
}
