package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.controller.dto.AreaComunDto;
import api.tpo_g04_reclamos.app.controller.dto.EdificioDto;
import api.tpo_g04_reclamos.app.controller.dto.UnidadDto;
import api.tpo_g04_reclamos.app.exception.exceptions.BadRequestException;
import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.dao.IEdificioDao;
import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.model.entity.Unidad;
import api.tpo_g04_reclamos.app.model.request.AreaComunRequestDto;
import api.tpo_g04_reclamos.app.model.request.EdificioRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EdificioServiceImpl implements IEdificioService{

	@Autowired
	private IEdificioDao edificioDao;

	@Autowired
	private IUnidadService unidadService;

	@Autowired
	private IAreaComunService areaComunService;

	@Override
	public List<Edificio> findAll() {
		return edificioDao.findAll();
	}

	@Override
	public Optional<Edificio> findById(Long id) {
		return edificioDao.findById(id);
	}

	@Override
	public Edificio save(EdificioRequestDto edificioDto) {
		Edificio edificio = edificioDao.save(new Edificio(edificioDto.getDireccion()));

		return edificio;
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

	public void addUnidad(Edificio edificio, UnidadDto unidadDto) {
		if(!edificio.getId().equals(unidadDto.getEdificio().getId())) {
			throw new BadRequestException("Unidad no se puede agregar, no pertenece al mismo edificio");
		}

		UnidadDto unidadAAgregar = new UnidadDto(unidadDto.getPiso(), unidadDto.getNumero(), unidadDto.getEdificio(), unidadDto.getEstado());
		Unidad unidadCreada = unidadService.save(unidadAAgregar);

		edificio.getUnidades().add(unidadCreada);
		edificioDao.save(edificio);
	}

	public void addAreaComun(Edificio edificio, AreaComunRequestDto areaComunDto) {
		AreaComun areaComunAAgregar = new AreaComun(edificio, areaComunDto.getNombre());

		edificio.getAreasComunes().add(areaComunAAgregar);

		edificioDao.save(edificio);
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
