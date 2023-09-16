package api.tpo_g04_reclamos.app.controller;

import api.tpo_g04_reclamos.app.controller.dto.AreaComunDto;
import api.tpo_g04_reclamos.app.controller.dto.EdificioDto;
import api.tpo_g04_reclamos.app.controller.dto.UnidadDto;
import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.service.IEdificioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/edificios")
public class EdificioController {
	
	@Autowired
	private IEdificioService edificioService;
	

	
	@GetMapping("/{edificioId}")
	public ResponseEntity<?> findById(@PathVariable Long edificioId){
		Edificio edificio = edificioService.findById(edificioId).orElseThrow(() -> new ItemNotFoundException(String.format("El edificio con id: %s no existe", edificioId)));
		return ok(edificio);
	}
	
	@PostMapping
	public ResponseEntity<Edificio> addEdificio(@RequestBody EdificioDto edificio) {
		Edificio edificioCreado = edificioService.save(edificio);
		return new ResponseEntity<>(edificioCreado, CREATED);
	}

	@PostMapping("/{edificioId}/unidad")
	public ResponseEntity<Edificio> addUnidad(@PathVariable Long edificioId, @RequestBody UnidadDto unidad) {
		Edificio edificioAAgregarUnidad = edificioService.findById(edificioId).orElseThrow(() -> new ItemNotFoundException("El edificio no existe"));
		edificioService.addUnidad(edificioAAgregarUnidad, unidad);

		return new ResponseEntity<>(edificioAAgregarUnidad, OK);
	}

	@PostMapping("/{edificioId}/area-comun")
	public ResponseEntity<Edificio> addAreaComun(@PathVariable Long edificioId, @RequestBody AreaComunDto areaComunDto) {
		Edificio edificioAAgregarAreaComun = edificioService.findById(edificioId).orElseThrow(() -> new ItemNotFoundException("El edificio no existe"));
		edificioService.addAreaComun(edificioAAgregarAreaComun, areaComunDto);

		return new ResponseEntity<>(edificioAAgregarAreaComun, OK);
	}
	
	@PutMapping("/{edificioId}")
	public ResponseEntity<?> updateEdificio(@PathVariable Long edificioId, @RequestBody Edificio edificio){
		Edificio edificioUpdated = edificioService.update(edificioId, edificio);
		return ok(edificioUpdated);
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
