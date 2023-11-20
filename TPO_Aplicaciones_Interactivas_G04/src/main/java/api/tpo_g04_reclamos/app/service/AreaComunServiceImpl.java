package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.dao.IAreaComunDao;
import api.tpo_g04_reclamos.app.model.dao.IEdificioDao;
import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
	public AreaComun get(Long id) {
		return findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("El area comun con id: %d no existe", id)));
	}

	@Override
	public List<AreaComun> findAllByIds(List<Long> ids) {
		return areaComunDao.findAllByIds(ids);
	}

	@Override

	public AreaComun save(AreaComun areaComun) {
		return areaComunDao.save(areaComun);
	}

	@Override
	public AreaComun update(Long id, AreaComun areaComun) {
		AreaComun areaComunToUpdate = get(id);

		areaComunToUpdate.setNombre(areaComun.getNombre());
		return areaComunDao.save(areaComunToUpdate);

	}

	@Override
	public void deleteById(Long id) {
		this.areaComunExiste(id);

		areaComunDao.deleteById(id);
	}

	private boolean areaComunExiste(Long id) {
		if(this.findById(id).isEmpty()) {
			throw new ItemNotFoundException(String.format("El area comun %d no existe", id));
		}

		return true;
	}

}
