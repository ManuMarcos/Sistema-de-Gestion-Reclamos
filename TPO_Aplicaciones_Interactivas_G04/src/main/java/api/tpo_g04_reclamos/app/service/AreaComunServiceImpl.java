package api.tpo_g04_reclamos.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.tpo_g04_reclamos.app.model.dao.IAreaComunDao;
import api.tpo_g04_reclamos.app.model.dao.IEdificioDao;
import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import api.tpo_g04_reclamos.app.model.entity.Edificio;

@Service
public class AreaComunServiceImpl implements IAreaComunService {

	@Autowired
	private IAreaComunDao areaComunDao;
	
	@Autowired
	private IEdificioDao edificioDao;
	
	@Override
	public List<AreaComun> findAll() {
		return areaComunDao.findAll();
	}

	@Override
	public AreaComun findById(int id) {
		return areaComunDao.findById(id);
	}

	@Override
	public void save(AreaComun areaComun) {
		Edificio edificio = edificioDao.findById(areaComun.getEdificio().getId());
		
		if (edificio != null) {
			edificio.agregarAreaComun(areaComun);
			areaComun.setEdificio(edificio);
			areaComunDao.save(areaComun);
		}
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
