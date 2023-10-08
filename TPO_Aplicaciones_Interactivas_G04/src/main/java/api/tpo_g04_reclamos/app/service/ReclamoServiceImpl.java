package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.dao.IReclamoDao;
import api.tpo_g04_reclamos.app.controller.request.ReclamoRequestDto;
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
		Unidad unidad = unidadService.findById(reclamoRequestDto.getAreaComunId()).orElseThrow(() -> new ItemNotFoundException("La Unidad no existe"));
		List<Imagen> imagenes = imagenService.findAllByIds(reclamoRequestDto.getImagenesIds());
		Usuario usuario = usuarioService.findById(reclamoRequestDto.getUsuarioId()).orElseThrow(() -> new ItemNotFoundException("El Usuario no existe"));
		AreaComun areaComun = areaComunService.findById(reclamoRequestDto.getAreaComunId()).orElseThrow(() -> new ItemNotFoundException("El Area Comun no existe"));

		return reclamoDao.save(new Reclamo(reclamoRequestDto.getNumero(), imagenes, reclamoRequestDto.getDescripcion(), reclamoRequestDto.getMotivo(), reclamoRequestDto.getEstado(), usuario, unidad, areaComun));
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

	private boolean reclamoExiste(Long id) {
		Optional<Reclamo> reclamo = this.findById(id);

		if(reclamo.isEmpty()) {
			throw new ItemNotFoundException(String.format("El reclamo %s no existe", id.toString()));
		}

		return true;
	}

	

}
