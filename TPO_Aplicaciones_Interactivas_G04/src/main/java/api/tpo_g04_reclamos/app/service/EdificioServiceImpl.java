package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.controller.request.AreaComunRequestDto;
import api.tpo_g04_reclamos.app.controller.request.EdificioRequestDto;
import api.tpo_g04_reclamos.app.controller.request.EdificioUpdateDto;
import api.tpo_g04_reclamos.app.controller.request.UnidadRequestDto;
import api.tpo_g04_reclamos.app.exception.exceptions.BadRequestException;
import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.dao.IAreaComunDao;
import api.tpo_g04_reclamos.app.model.dao.IEdificioDao;
import api.tpo_g04_reclamos.app.model.dao.IUnidadDao;
import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.model.entity.Unidad;
import api.tpo_g04_reclamos.app.model.entity.Usuario;
import api.tpo_g04_reclamos.app.model.enums.EstadoUnidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EdificioServiceImpl implements IEdificioService{

	@Autowired
	private IEdificioDao edificioDao;

	@Autowired
	private IUnidadDao unidadDao;

	@Autowired
	private IAreaComunDao areaComunDao;

	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public List<Edificio> findAll() {
		return edificioDao.findAll();
	}

	@Override
	public List<Edificio> findByUsuarioId(Long usuarioId) {
		List<Edificio> edificios = edificioDao.findAll();

		return edificios.stream().filter(edificio -> {
			List<Unidad> unidades = edificio.getUnidades();

			List<Long> propietarioIds = unidades.stream().map(Unidad::getPropietario).filter(Objects::nonNull).toList().stream().map(Usuario::getId).toList();
			if(propietarioIds.contains(usuarioId)) {
				return true;
			}

			List<Long> inquilinosIds = unidades.stream().flatMap(unidad -> unidad.getInquilinos().stream()).filter(Objects::nonNull).toList().stream().map(Usuario::getId).toList();
			if(inquilinosIds.contains(usuarioId)) {
				return true;
			}

			return false;
		}).toList();
	}

	@Override
	public Optional<Edificio> findById(Long id) {
		return edificioDao.findById(id);
	}

	@Override
	public Edificio get(Long id) {
		return findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("El edificio %d no existe", id)));
	}

	@Override
	public Edificio save(EdificioRequestDto edificioDto) {
		return edificioDao.save(new Edificio(edificioDto.getDireccion()));
	}

	@Override
	public Edificio update(Long id, EdificioUpdateDto edificio) {
		Edificio edificioToUpdate = this.get(id);

		List<AreaComun> areasComunes = areaComunDao.findAllByIds(edificio.getAreasComunesIds());
		List<Unidad> unidades = unidadDao.findAllByIds(edificio.getUnidadesIds());
		this.unidadesYAreasComunesPertenecen(edificio.getId(), areasComunes, unidades);

		edificioToUpdate.setDireccion(edificio.getDireccion());
		edificioToUpdate.setAreasComunes(areasComunes);
		edificioToUpdate.setUnidades(unidades);
		return edificioDao.save(edificioToUpdate);
	}

	@Override
	public void deleteById(Long id) {
		this.edificioExiste(id);

		edificioDao.deleteById(id);
	}

	@Override
	public Edificio agregarInquilino(Long edificioId, Long unidadId, Long inquilinoId) {
		Edificio edificioAAgregarInquilino = this.get(edificioId);
		Unidad unidadAAgregarInquilino = edificioAAgregarInquilino.getUnidades().stream().filter(unidad -> unidadId.equals(unidad.getId())).findFirst().orElseThrow(() -> new ItemNotFoundException(String.format("La unidad %d no existe en el edificio %d", unidadId, edificioId)));

		if(unidadAAgregarInquilino.getInquilinos().stream().map(Usuario::getId).toList().contains(inquilinoId)) {
			throw new BadRequestException(String.format("El inquilino %d ya existe para la unidad %d", inquilinoId, unidadId));
		}

		Usuario inquilinoNuevo = usuarioService.get(inquilinoId);
		unidadAAgregarInquilino.getInquilinos().add(inquilinoNuevo);
		if(unidadAAgregarInquilino.getEstado() == EstadoUnidad.SIN_ALQUILAR)
			unidadAAgregarInquilino.setEstado(EstadoUnidad.ALQUILADA);

		return edificioDao.save(edificioAAgregarInquilino);
	}

	@Override
	public Edificio deleteInquilinoUnidad(Long edificioId, Long unidadId, Long inquilinoId) {
		Edificio edificioAQuitarInquilino = this.get(edificioId);
		Unidad unidadAQuitarInquilino = edificioAQuitarInquilino.getUnidades().stream().filter(unidad -> unidadId.equals(unidad.getId())).findFirst().orElseThrow(() -> new ItemNotFoundException(String.format("La unidad %d no existe en el edificio %d", unidadId, edificioId)));

		if(!unidadAQuitarInquilino.getInquilinos().stream().map(Usuario::getId).toList().contains(inquilinoId)) {
			throw new BadRequestException(String.format("El inquilino %d no existe para la unidad %d", inquilinoId, unidadId));
		}

		Usuario inquilinoAQuitar = usuarioService.get(inquilinoId);
		unidadAQuitarInquilino.getInquilinos().remove(inquilinoAQuitar);
		if(unidadAQuitarInquilino.getInquilinos().isEmpty())
			unidadAQuitarInquilino.setEstado(EstadoUnidad.SIN_ALQUILAR);

		return edificioDao.save(edificioAQuitarInquilino);
	}

	@Override
	public List<Usuario> getInquilinosUnidad(Long edificioId, Long unidadId) {
		Edificio edificio = get(edificioId);

		if(!edificio.getUnidades().stream().map(Unidad::getId).toList().contains(unidadId)) {
			throw new BadRequestException(String.format("La unidad %d no pertence al edificio %d", unidadId, edificioId));
		}

		Unidad unidad = unidadDao.findById(unidadId).orElseThrow(() -> new ItemNotFoundException(String.format("La unidad %d no existe", unidadId)));

		return unidad.getInquilinos();
	}

	public void addUnidad(Edificio edificio, UnidadRequestDto unidadDto) {
		Usuario propietario = usuarioService.get(unidadDto.getPropietarioId());
		Unidad nuevaUnidad = new Unidad(unidadDto.getPiso(), unidadDto.getNumero(), edificio, propietario, unidadDto.getEstado());

		edificio.agregarUnidad(nuevaUnidad);
		edificioDao.save(edificio);
	}

	public void addAreaComun(Edificio edificio, AreaComunRequestDto areaComunDto) {
		AreaComun nuevaAreaComun = new AreaComun(edificio, areaComunDto.getNombre());

		edificio.agregarAreaComun(nuevaAreaComun);
		edificioDao.save(edificio);
	}

	private boolean edificioExiste(Long id) {
		if(this.findById(id).isEmpty()) {
			throw new ItemNotFoundException(String.format("El edificio %d no existe", id));
		}

		return true;
	}

	private boolean unidadesYAreasComunesPertenecen(Long edificioId, List<AreaComun> areasComunes, List<Unidad> unidades) {
		List<AreaComun> areasComunesNoPertenecientes = areasComunes.stream()
				.filter(areaComun -> !areaComun.getEdificio().getId().equals(edificioId)).toList();

		List<Unidad> unidadesNoPertenecientes = unidades.stream()
				.filter(unidad -> !unidad.getEdificio().getId().equals(edificioId)).toList();

		if(!areasComunesNoPertenecientes.isEmpty() || !unidadesNoPertenecientes.isEmpty()) {
			throw new BadRequestException("No se puede hacer update del edificio, areasComunes o unidades no pertenecen");
		}

		return true;
	}
}
