package api.tpo_g04_reclamos.app.service;

import java.util.List;

import api.tpo_g04_reclamos.app.model.entity.AreaComun;

public interface IAreaComunService {

	public List<AreaComun> findAll();
	
	public AreaComun findById(int id);
	
	public void save(AreaComun areaComun);
	
	public void update(int id, AreaComun areaComun);
	
	public void deleteById(int id);
	
}
