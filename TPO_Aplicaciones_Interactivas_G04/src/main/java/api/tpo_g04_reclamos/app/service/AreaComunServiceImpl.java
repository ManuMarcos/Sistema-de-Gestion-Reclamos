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
	public Optional<AreaComun> findById(Long id) {
		return areaComunDao.findById(id);
	}

	@Override
	public AreaComun save(AreaComun areaComun) {
		return areaComunDao.save(areaComun);
	}

	@Override
	public AreaComun update(Long id, AreaComun areaComun) {
		Optional<AreaComun> areaComunToUpdateOptional = areaComunDao.findById(id);
		
		if(areaComunToUpdateOptional.isPresent()) {
			AreaComun areaComunToUpdate = areaComunToUpdateOptional.get();

			areaComunToUpdate.setNombre(areaComun.getNombre());
			return areaComunDao.save(areaComunToUpdate);
		} else {
			throw new IllegalArgumentException();
		}

	}

	@Override
	public void deleteById(Long id) {
		areaComunDao.deleteById(id);
	}

}
