package api.tpo_g04_reclamos.app.service;

import java.util.List;
import java.util.Optional;

import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.entity.Usuario;
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
	public Optional<AreaComun> findById(Long id) {
		return areaComunDao.findById(id);
	}

	@Override

	public AreaComun save(AreaComun areaComun) {
		return areaComunDao.save(areaComun);
	}

	@Override
	public AreaComun update(Long id, AreaComun areaComun) {
		this.areaComunExiste(id);

		AreaComun areaComunToUpdate = areaComunDao.findById(id).get();

		areaComunToUpdate.setNombre(areaComun.getNombre());
		return areaComunDao.save(areaComunToUpdate);

	}

	@Override
	public void deleteById(Long id) {
		this.areaComunExiste(id);

		areaComunDao.deleteById(id);
	}

	private boolean areaComunExiste(Long id) {
		Optional<AreaComun> areaComun = this.findById(id);

		if(areaComun.isEmpty()) {
			throw new ItemNotFoundException("El area comun no existe");
		}

		return true;
	}

}
