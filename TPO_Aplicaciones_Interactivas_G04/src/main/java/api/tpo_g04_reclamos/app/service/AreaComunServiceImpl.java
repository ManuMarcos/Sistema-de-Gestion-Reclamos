package api.tpo_g04_reclamos.app.service;

import java.util.List;
import java.util.Optional;

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
		return areaComunDao.findAll();
	}

	@Override
	public Optional<AreaComun> findById(int id) {
		return areaComunDao.findById(id);
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
