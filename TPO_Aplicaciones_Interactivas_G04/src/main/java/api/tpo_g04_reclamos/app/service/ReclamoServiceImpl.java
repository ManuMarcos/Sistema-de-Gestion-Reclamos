package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.dao.IReclamoDao;
import api.tpo_g04_reclamos.app.model.request.ReclamoDto;
import api.tpo_g04_reclamos.app.model.entity.*;
import api.tpo_g04_reclamos.app.model.enums.EstadoReclamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReclamoServiceImpl implements IReclamoService {

	@Autowired
	private IReclamoDao reclamoDao;

	@Autowired
	private IEdificioService edificioService;

	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public List<Reclamo> findAll() {
		return reclamoDao.findAll();
	}

	@Override
	public Optional<Reclamo> findById(Long id) {
		return reclamoDao.findById(id);
	}
	
	@Override
	public List<Reclamo> findByEstado(EstadoReclamo estado) {
		// TODO Auto-generated method stub
		return reclamoDao.findByEstado(estado);
	}

	@Override
	public Reclamo save(ReclamoDto reclamoDto) {
		var acDTO = reclamoDto.getAreaComun();
		var edificio = edificioService.findById(acDTO.getEdificioId()).orElseThrow(() -> new ItemNotFoundException("El edificio no existe"));
		var propietario = usuarioService.findById(reclamoDto.getUnidad().getPropietarioId()).orElseThrow(() -> new ItemNotFoundException("El propietario no existe"));

		List<Imagen> imagenes = reclamoDto.getImagenes().stream().map(imagenDto -> new Imagen(imagenDto.getId(), imagenDto.getNombre(), imagenDto.getTipo(), imagenDto.getData())).toList();
		Usuario usuario = new Usuario(reclamoDto.getUsuario().getId(), reclamoDto.getUsuario().getNombre(), reclamoDto.getUsuario().getPassword(), reclamoDto.getUsuario().getTipoUsuario());
		Unidad unidad = new Unidad(reclamoDto.getUnidad().getId(), reclamoDto.getUnidad().getPiso(), reclamoDto.getUnidad().getNumero(), edificio, propietario, reclamoDto.getUnidad().getEstado());

		AreaComun areaComun = new AreaComun(acDTO.getId(), edificio, acDTO.getNombre());

		return reclamoDao.save(new Reclamo(reclamoDto.getNumero(), imagenes, reclamoDto.getDescripcion(), reclamoDto.getMotivo(), reclamoDto.getEstado(), usuario, unidad, areaComun));
	}

	@Override
	public Reclamo update(Long id, ReclamoDto reclamoDto) {
		this.reclamoExiste(id);

		Reclamo reclamo = this.findById(id).get();
		reclamo.setDescripcion(reclamoDto.getDescripcion());
		reclamo.setEstado(reclamoDto.getEstado());

		return reclamoDao.update(reclamo);
	}

	@Override
	public void deleteById(Long id) {
		this.reclamoExiste(id);

		reclamoDao.deleteById(id);
	}

	private boolean reclamoExiste(Long id) {
		Optional<Reclamo> reclamo = this.findById(id);

		if(reclamo.isEmpty()) {
			throw new ItemNotFoundException(String.format("El reclamo %s no existe", id.toString()));
		}

		return true;
	}

	

}
