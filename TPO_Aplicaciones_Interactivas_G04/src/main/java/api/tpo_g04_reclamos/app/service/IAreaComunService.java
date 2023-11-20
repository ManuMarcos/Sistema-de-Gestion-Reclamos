package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.model.entity.AreaComun;

import java.util.List;
import java.util.Optional;

public interface IAreaComunService {

	List<AreaComun> findAll();
	
	Optional<AreaComun> findById(Long id);

	AreaComun get(Long id);

	List<AreaComun> findAllByIds(List<Long> ids);
	
	AreaComun save(AreaComun areaComun);
	
	AreaComun update(Long id, AreaComun areaComun);
	
	void deleteById(Long id);
	
}
