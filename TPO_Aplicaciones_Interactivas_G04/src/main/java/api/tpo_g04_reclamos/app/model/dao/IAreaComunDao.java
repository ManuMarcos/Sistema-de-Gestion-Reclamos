package api.tpo_g04_reclamos.app.model.dao;

import java.util.List;

import api.tpo_g04_reclamos.app.model.entity.AreaComun;

public interface IAreaComunDao {

	public List<AreaComun> findAll();
	
	public AreaComun findById(int id);
	
	public void save(AreaComun areaComun);
	
	public void deleteById(int id);
	
}
