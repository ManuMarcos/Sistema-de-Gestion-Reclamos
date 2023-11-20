package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.controller.request.AreaComunRequestDto;
import api.tpo_g04_reclamos.app.controller.request.EdificioRequestDto;
import api.tpo_g04_reclamos.app.controller.request.EdificioUpdateDto;
import api.tpo_g04_reclamos.app.controller.request.UnidadRequestDto;
import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IEdificioService {

	List<Edificio> findAll();

	List<Edificio> findByUsuarioId(Long usuarioId);
	
	Optional<Edificio> findById(Long id);
	
	Edificio save(EdificioRequestDto edificio);
	
	Edificio update(Long id, EdificioUpdateDto edificio);
	
	void deleteById(Long id);

	void addUnidad(Edificio edificio, UnidadRequestDto unidad);

	void addAreaComun(Edificio edificio, AreaComunRequestDto areaComunDto);

	Edificio agregarInquilino(Long edificioId, Long unidadId, Long inquilinoId);

	List<Usuario> getInquilinosUnidad(Long edificioId, Long unidadId);

}
