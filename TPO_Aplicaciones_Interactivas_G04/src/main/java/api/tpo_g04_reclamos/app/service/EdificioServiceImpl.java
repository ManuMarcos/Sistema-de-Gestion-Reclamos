package api.tpo_g04_reclamos.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.tpo_g04_reclamos.app.model.dao.IEdificioDao;
import api.tpo_g04_reclamos.app.model.entity.Edificio;

@Service
public class EdificioServiceImpl implements IEdificioService{

	@Autowired
	private IEdificioDao edificioDao;
	
	
	@Override
	public List<Edificio> findAll() {
		return edificioDao.findAll();
	}

	@Override
	public Optional<Edificio> findById(Long id) {
		return edificioDao.findById(id);
	}

	@Override
	public Edificio save(Edificio edificio) {
		return edificioDao.save(edificio);
	}

	@Override
	public void update(Long id, Edificio edificio) {
		Optional<Edificio> edificioToUpdateOptional = edificioDao.findById(id);
		if(edificioToUpdateOptional.isPresent()) {
			Edificio edificioToUpdate = edificioToUpdateOptional.get();
			edificioToUpdate.setDireccion(edificio.getDireccion());
			//TO DO: Verificar si estan bien estos dos
			edificioToUpdate.setAreasComunes(edificio.getAreasComunes());
			edificioToUpdate.setUnidades(edificio.getUnidades());
			edificioDao.save(edificioToUpdate);
		}
	}

	@Override
	public void deleteById(Long id) {
		edificioDao.deleteById(id);
	}

}
