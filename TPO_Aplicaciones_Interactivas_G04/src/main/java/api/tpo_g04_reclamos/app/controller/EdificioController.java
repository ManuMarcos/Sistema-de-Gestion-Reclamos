package api.tpo_g04_reclamos.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import api.tpo_g04_reclamos.app.controller.dto.AreaComunDto;
import api.tpo_g04_reclamos.app.controller.dto.EdificioDto;
import api.tpo_g04_reclamos.app.controller.dto.UnidadDto;
import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.entity.Unidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.tpo_g04_reclamos.app.model.dto.AreaComunDto;
import api.tpo_g04_reclamos.app.model.dto.EdificioDto;
import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.service.IEdificioService;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/edificios")
public class EdificioController {
	
	@Autowired
	private IEdificioService edificioService;
	

	
	@GetMapping
	public List<EdificioDto> findAll(){
		List<Edificio> edificios = edificioService.findAll();
		List<EdificioDto> edificiosDto = new ArrayList<>();
		
		for(Edificio edificio : edificios) {
			EdificioDto edificioDto = convertToDto(edificio);
			edificiosDto.add(edificioDto);
		}
		
		return edificiosDto;
	}
	
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
	
	public EdificioDto convertToDto(Edificio edificio) {
		List<AreaComun> areasComunes = edificio.getAreasComunes();
		List<AreaComunDto> areasComunesDto = new ArrayList<>();
		for(AreaComun areaComun : areasComunes) {
			AreaComunDto areaComunDto = new AreaComunDto(areaComun.getId(),areaComun.getNombre());
			areasComunesDto.add(areaComunDto);
		}
		
		return new EdificioDto(edificio.getId(),edificio.getDireccion(),areasComunesDto);
	}
	
	
	
	
	
	
}
