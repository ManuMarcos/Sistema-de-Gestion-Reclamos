package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.controller.request.ReclamoRequestDto;
import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.exception.exceptions.ReclamoNoSePuedeCrearException;
import api.tpo_g04_reclamos.app.model.dao.IReclamoDao;
import api.tpo_g04_reclamos.app.model.entity.*;
import api.tpo_g04_reclamos.app.model.enums.EstadoReclamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static api.tpo_g04_reclamos.app.model.enums.EstadoUnidad.ALQUILADA;
import static api.tpo_g04_reclamos.app.model.enums.EstadoUnidad.SIN_ALQUILAR;

@Service
public class ReclamoServiceImpl implements IReclamoService {

	@Autowired
	private IReclamoDao reclamoDao;

	@Autowired
	private IUnidadService unidadService;

	@Autowired
	private IAreaComunService areaComunService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IImagenService imagenService;
	
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
		return reclamoDao.findByEstado(estado);
	}

	@Override
	public Reclamo save(ReclamoRequestDto reclamoRequestDto) {
		Optional<Unidad> unidadOptional = unidadService.findById(reclamoRequestDto.getUnidadId());
		AreaComun areaComun = areaComunService.findById(reclamoRequestDto.getAreaComunId()).orElse(null);

		Usuario usuario = usuarioService.findById(reclamoRequestDto.getUsuarioId()).orElseThrow(() -> new ItemNotFoundException("El Usuario no existe"));

		// veo si es una unidad para ver si es posible generar el reclamo con el usuario
		if(unidadOptional.isPresent()) {
			if(this.reclamoSePuedeCrear(unidadOptional.get(), usuario)) {
				throw new ReclamoNoSePuedeCrearException(String.format("El reclamo no se puede crear por el usuario %s", usuario.getId()));
			}
		}

		Long edificioId = unidadOptional.isPresent() ? unidadOptional.get().getEdificio().getId() : areaComun.getEdificio().getId();

		List<Imagen> imagenes = imagenService.findAllByIds(reclamoRequestDto.getImagenesIds());

		return reclamoDao.save(new Reclamo(reclamoRequestDto.getNumero(), imagenes, reclamoRequestDto.getDescripcion(), reclamoRequestDto.getMotivo(), reclamoRequestDto.getEstado(), usuario, unidadOptional.orElse(null), areaComun, edificioId));
	}

	private boolean reclamoSePuedeCrear(Unidad unidad, Usuario usuario) {
		List<Long> inquilinosIds = unidad.getInquilinos().stream().map(Usuario::getId).toList();
		if(ALQUILADA.equals(unidad.getEstado()) && inquilinosIds.contains(usuario.getId())) {
			return true;
		}

		return SIN_ALQUILAR.equals(unidad.getEstado()) && unidad.getPropietario().getId().equals(usuario.getId());
	}

	@Override
	public Reclamo update(Long id, ReclamoRequestDto reclamoRequestDto) {
		this.reclamoExiste(id);

		Reclamo reclamo = this.findById(id).get();
		reclamo.setDescripcion(reclamoRequestDto.getDescripcion());
		reclamo.setEstado(reclamoRequestDto.getEstado());

		return reclamoDao.update(reclamo);
	}

	@Override
	public void deleteById(Long id) {
		this.reclamoExiste(id);

		reclamoDao.deleteById(id);
	}

	@Override
	public List<Reclamo> findAllByEdificioId(Long edificioId) {
		return reclamoDao.findAllByEdificioId(edificioId);
	}

	private boolean reclamoExiste(Long id) {
		Optional<Reclamo> reclamo = this.findById(id);

		if(reclamo.isEmpty()) {
			throw new ItemNotFoundException(String.format("El reclamo %s no existe", id.toString()));
		}

		return true;
	}

	

}
