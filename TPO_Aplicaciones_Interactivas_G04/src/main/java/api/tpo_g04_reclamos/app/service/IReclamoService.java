package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.model.request.ReclamoDto;
import api.tpo_g04_reclamos.app.model.entity.Reclamo;
import api.tpo_g04_reclamos.app.model.enums.EstadoReclamo;

import java.util.List;
import java.util.Optional;

public interface IReclamoService {
	List<Reclamo> findAll();
	
	Optional<Reclamo> findById(Long id);
	
	List<Reclamo> findByEstado(EstadoReclamo estado);

	Reclamo save(ReclamoDto reclamo);

	Reclamo update(Long id, ReclamoDto reclamo);
	
	void deleteById(Long id);
}
