package api.tpo_g04_reclamos.app.model.dao;

import java.util.List;
import java.util.Optional;

import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import api.tpo_g04_reclamos.app.model.entity.Unidad;

public interface IAreaComunDao {

	List<AreaComun> findAll();
	
	Optional<AreaComun> findById(Long id);
	
	AreaComun save(AreaComun areaComun);

	AreaComun update(AreaComun areaComun);

	void deleteById(Long id);
	
}
