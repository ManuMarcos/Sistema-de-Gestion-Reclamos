package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.controller.request.ReclamoRequestDto;
import api.tpo_g04_reclamos.app.model.entity.Reclamo;
import api.tpo_g04_reclamos.app.model.enums.EstadoReclamo;

import java.util.List;
import java.util.Optional;

public interface IReclamoService {
	List<Reclamo> findAll();

	List<Reclamo> findAllByEdificioId(Long edificioId);

	Optional<Reclamo> findById(Long id);
	
	List<Reclamo> findByEstado(EstadoReclamo estado);

	Reclamo save(ReclamoRequestDto reclamo);

	Reclamo update(Long id, ReclamoRequestDto reclamo);
	
	void deleteById(Long id);
}
