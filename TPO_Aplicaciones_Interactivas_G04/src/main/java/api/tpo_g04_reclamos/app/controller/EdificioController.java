package api.tpo_g04_reclamos.app.controller;

import api.tpo_g04_reclamos.app.controller.dto.EdificioDto;
import api.tpo_g04_reclamos.app.controller.request.AreaComunRequestDto;
import api.tpo_g04_reclamos.app.controller.request.EdificioRequestDto;
import api.tpo_g04_reclamos.app.controller.request.EdificioUpdateDto;
import api.tpo_g04_reclamos.app.controller.request.UnidadRequestDto;
import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.service.IEdificioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/edificios")
public class EdificioController {
	
	@Autowired
	private IEdificioService edificioService;
	
	
	@GetMapping
	public List<EdificioDto> findAll(){
		return EdificioDto.fromList(edificioService.findAll());
	}

	@GetMapping("/{edificioId}")
	public ResponseEntity<?> findById(@PathVariable Long edificioId){
		Optional<Edificio> edificio = edificioService.findById(edificioId);
		if(edificio.isEmpty()) {
			String mensaje = "Edificio con id" +  edificioId + " no encontrado";
			return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new EdificioDto(edificio.get()), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<EdificioDto> addEdificio(@RequestBody EdificioRequestDto edificio) {
		Edificio edificioCreado = edificioService.save(edificio);
		return new ResponseEntity<>(new EdificioDto(edificioCreado), CREATED);
	}

	@PostMapping("/{edificioId}/unidad")
	public ResponseEntity<EdificioDto> addUnidad(@PathVariable Long edificioId, @RequestBody UnidadRequestDto unidad) {
		Edificio edificioAAgregarUnidad = edificioService.findById(edificioId).orElseThrow(() -> new ItemNotFoundException("El edificio no existe"));
		edificioService.addUnidad(edificioAAgregarUnidad, unidad);

		return new ResponseEntity<>(new EdificioDto(edificioAAgregarUnidad), OK);
	}

	@PostMapping("/{edificioId}/area-comun")
	public ResponseEntity<EdificioDto> addAreaComun(@PathVariable Long edificioId, @RequestBody AreaComunRequestDto areaComunDto) {
		Edificio edificioAAgregarAreaComun = edificioService.findById(edificioId).orElseThrow(() -> new ItemNotFoundException("El edificio no existe"));
		edificioService.addAreaComun(edificioAAgregarAreaComun, areaComunDto);

		return new ResponseEntity<>(new EdificioDto(edificioAAgregarAreaComun), OK);
	}
	
	@PutMapping("/{edificioId}")
	public ResponseEntity<EdificioDto> updateEdificio(@PathVariable Long edificioId, @RequestBody EdificioUpdateDto edificio){
		Edificio edificioUpdated = edificioService.update(edificioId, edificio);
		return ok(new EdificioDto(edificioUpdated));
	}
	
	@DeleteMapping("/{edificioId}")
	public ResponseEntity<String> deleteEdificio(@PathVariable Long edificioId){
		Optional<Edificio> edificioToDeleteOptional = edificioService.findById(edificioId);
		if(edificioToDeleteOptional.isPresent()) {
			edificioService.deleteById(edificioId);
			String mensaje = "Edificio con id: " + edificioId + " eliminado correctamente!";
			return ok(mensaje);
		}
		String mensaje = "El edificio con id: " + edificioId + " no existe";
		return new ResponseEntity<>(mensaje, NOT_FOUND);
	}
	
}
