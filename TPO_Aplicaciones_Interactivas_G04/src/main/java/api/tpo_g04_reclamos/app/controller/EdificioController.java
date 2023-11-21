package api.tpo_g04_reclamos.app.controller;

import api.tpo_g04_reclamos.app.controller.dto.EdificioDto;
import api.tpo_g04_reclamos.app.controller.dto.UsuarioDto;
import api.tpo_g04_reclamos.app.controller.request.AreaComunRequestDto;
import api.tpo_g04_reclamos.app.controller.request.EdificioRequestDto;
import api.tpo_g04_reclamos.app.controller.request.EdificioUpdateDto;
import api.tpo_g04_reclamos.app.controller.request.UnidadRequestDto;
import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.model.entity.Usuario;
import api.tpo_g04_reclamos.app.service.IEdificioService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/edificios")
public class EdificioController {
	
	@Autowired
	private IEdificioService edificioService;
	
	@GetMapping
	public ResponseEntity<List<EdificioDto>> findAll(@RequestParam(value = "usuarioId", required = false) String usuarioId){
		if(Strings.isNotBlank(usuarioId)) {
			return ok(EdificioDto.fromList(edificioService.findByUsuarioId(Long.valueOf(usuarioId))));
		}

		return ok(EdificioDto.fromList(edificioService.findAll()));
	}

	@GetMapping("/{edificioId}")
	public ResponseEntity<?> findById(@PathVariable Long edificioId){
		Edificio edificio = edificioService.get(edificioId);
		return ok(new EdificioDto(edificio));
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EdificioDto> addEdificio(@RequestBody EdificioRequestDto edificio) {
		Edificio edificioCreado = edificioService.save(edificio);
		return new ResponseEntity<>(new EdificioDto(edificioCreado), CREATED);
	}

	@PostMapping("/{edificioId}/unidad")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EdificioDto> addUnidad(@PathVariable Long edificioId, @RequestBody UnidadRequestDto unidad) {
		Edificio edificioAAgregarUnidad = edificioService.get(edificioId);
		edificioService.addUnidad(edificioAAgregarUnidad, unidad);

		return ok(new EdificioDto(edificioAAgregarUnidad));
	}

	@PostMapping("/{edificioId}/unidad/{unidadId}/inquilino/{inquilinoId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EdificioDto> addInquilinoAUnidad(@PathVariable("edificioId") Long edificioId, @PathVariable("unidadId") Long unidadId, @PathVariable("inquilinoId") Long inquilinoId) {
		Edificio edificio = edificioService.agregarInquilino(edificioId, unidadId, inquilinoId);

		return ok(new EdificioDto(edificio));
	}

	@GetMapping("/{edificioId}/inquilinos/{unidadId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UsuarioDto>> getInquilinosUnidad(@PathVariable("edificioId") Long edificioId, @PathVariable("unidadId") Long unidadId) {
		List<Usuario> inquilinos = edificioService.getInquilinosUnidad(edificioId, unidadId);

		return ok(UsuarioDto.fromList(inquilinos));
	}

	@PostMapping("/{edificioId}/area-comun")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EdificioDto> addAreaComun(@PathVariable Long edificioId, @RequestBody AreaComunRequestDto areaComunDto) {
		Edificio edificioAAgregarAreaComun = edificioService.get(edificioId);
		edificioService.addAreaComun(edificioAAgregarAreaComun, areaComunDto);

		return ok(new EdificioDto(edificioAAgregarAreaComun));
	}
	
	@PutMapping("/{edificioId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EdificioDto> updateEdificio(@PathVariable Long edificioId, @RequestBody EdificioUpdateDto edificio){
		Edificio edificioUpdated = edificioService.update(edificioId, edificio);
		return ok(new EdificioDto(edificioUpdated));
	}
	
	@DeleteMapping("/{edificioId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteEdificio(@PathVariable Long edificioId){
		edificioService.deleteById(edificioId);

		String mensaje = "Edificio con id: " + edificioId + " eliminado correctamente!";
		return ok(mensaje);
	}
	
}
