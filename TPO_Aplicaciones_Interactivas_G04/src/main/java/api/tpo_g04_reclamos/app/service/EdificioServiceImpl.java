package api.tpo_g04_reclamos.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import api.tpo_g04_reclamos.app.exception.exceptions.BadRequestException;
import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import api.tpo_g04_reclamos.app.model.entity.Unidad;
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
	public Edificio update(Long id, Edificio edificio) {
		this.edificioExiste(id);
		this.unidadesYAreasComunesPertenecen(edificio);

		Edificio edificioToUpdate = edificioDao.findById(id).get();
		edificioToUpdate.setDireccion(edificio.getDireccion());
		edificioToUpdate.setAreasComunes(edificio.getAreasComunes());
		edificioToUpdate.setUnidades(edificio.getUnidades());
		return edificioDao.save(edificioToUpdate);
	}

	@Override
	public void deleteById(Long id) {
		this.edificioExiste(id);

		edificioDao.deleteById(id);
	}

	private boolean edificioExiste(Long id) {
		Optional<Edificio> edificio = this.findById(id);

		if(edificio.isEmpty()) {
			throw new ItemNotFoundException("El edificio no existe");
		}

		return true;
	}

	private boolean unidadesYAreasComunesPertenecen(Edificio edificio) {
		List<AreaComun> areasComunesNoPertenecientes = edificio.getAreasComunes().stream()
				.filter(areaComun -> !areaComun.getEdificio().getId().equals(edificio.getId())).toList();

		List<Unidad> unidadesNoPertenecientes = edificio.getUnidades().stream()
				.filter(unidad -> !unidad.getEdificio().getId().equals(edificio.getId())).toList();

		if(!areasComunesNoPertenecientes.isEmpty() || !unidadesNoPertenecientes.isEmpty()) {
			throw new BadRequestException("No se puede hacer update del edificio, areasComunes o unidades no pertenecen");
		}

		return true;
	}
}
