package api.tpo_g04_reclamos.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.tpo_g04_reclamos.app.model.dao.IAreaComunDao;
import api.tpo_g04_reclamos.app.model.entity.AreaComun;

@Service
public class AreaComunServiceImpl implements IAreaComunService {

	@Autowired
	private IAreaComunDao areaComunDao;
	
	@Override
	public List<AreaComun> findAll() {
		List<AreaComun> areasComunes = areaComunDao.findAll();
		return areasComunes;
	}

	@Override
	public AreaComun findById(int id) {
		AreaComun areaComun = areaComunDao.findById(id);
		return areaComun;
	}

	@Override
	public void save(AreaComun areaComun) {
		areaComunDao.save(areaComun);
		
	}

	@Override
	public void update(int id, AreaComun areaComun) {
		AreaComun areaComunToUpdate = areaComunDao.findById(id);
		
		if(areaComunToUpdate != null) {
			areaComunToUpdate.setNombre(areaComun.getNombre());
			areaComunDao.save(areaComunToUpdate);
		}

	}

	@Override
	public void deleteById(int id) {
		areaComunDao.deleteById(id);
	}

}
