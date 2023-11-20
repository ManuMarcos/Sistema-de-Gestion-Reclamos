package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.controller.dto.UnidadDto;
import api.tpo_g04_reclamos.app.controller.request.UnidadUpdateRequestDto;
import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.dao.IUnidadDao;
import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.model.entity.Unidad;
import api.tpo_g04_reclamos.app.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadServiceImpl implements IUnidadService {

	@Autowired
	private IUnidadDao unidadDao;

	@Autowired
	private IEdificioService edificioService;

	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public List<Unidad> findAll() {
		return unidadDao.findAll();
	}

	@Override
	public Optional<Unidad> findById(Long id) {
		return unidadDao.findById(id);
	}

	@Override
	public List<Unidad> findAllByIds(List<Long> ids) {
		return unidadDao.findAllByIds(ids);
	}

	@Override
	public Unidad save(UnidadDto unidadDto) {
		Edificio edificio = edificioService.findById(unidadDto.getEdificioId()).orElseThrow(() -> new ItemNotFoundException("El edificio no existe"));
		return unidadDao.save(new Unidad(unidadDto.getPiso(), unidadDto.getNumero(), edificio, unidadDto.getEstado()));
	}

	@Override
	public Unidad update(Long id, UnidadUpdateRequestDto updateRequest) {
		this.unidadExiste(id);

		Unidad unidadToUpdate = unidadDao.findById(id).get();

		unidadToUpdate.setEdificio(unidadToUpdate.getEdificio());
		unidadToUpdate.setEstado(updateRequest.getEstado());
		unidadToUpdate.setPiso(updateRequest.getPiso());
		unidadToUpdate.setNumero(updateRequest.getNumero());

		if(updateRequest.getPropietarioId() != null) {
			Usuario nuevoPropietario = usuarioService.findById(updateRequest.getPropietarioId()).orElseThrow(() -> new ItemNotFoundException(String.format("El usuario %d no existe", updateRequest.getPropietarioId())));
			unidadToUpdate.setPropietario(nuevoPropietario);
		}

		return unidadDao.save(unidadToUpdate);

	}

	@Override
	public void deleteById(Long id) {
		this.unidadExiste(id);

		unidadDao.deleteById(id);
	}

	private boolean unidadExiste(Long id) {
		Optional<Unidad> unidad = this.findById(id);

		if(unidad.isEmpty()) {
			throw new ItemNotFoundException("La unidad no existe");
		}

		return true;
	}

}
