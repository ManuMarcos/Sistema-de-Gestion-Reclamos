package api.tpo_g04_reclamos.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import api.tpo_g04_reclamos.app.service.IAreaComunService;
import api.tpo_g04_reclamos.app.service.IEdificioService;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/areasComunes")
public class AreaComunController {

	@Autowired
	private IAreaComunService areaComunService;
	
	@Autowired
	private IEdificioService edificioService;
	
	@GetMapping
	public List<AreaComun> findAll(){
		return areaComunService.findAll();
	}
	
	@GetMapping("/{areaComunId}")
	public ResponseEntity<?> findById(@PathVariable Long areaComunId){
		Optional<AreaComun> areaComunOptional = areaComunService.findById(areaComunId);
		
		if(areaComunOptional.isEmpty()) {
			String mensaje = "El area comun con id:" +  areaComunId + " no existe";
			return new ResponseEntity<>(mensaje, NOT_FOUND);
		}
		return ok(areaComunOptional.get());
	}
	
	/*
	@PostMapping
	public ResponseEntity<?> addAreaComun(@RequestBody AreaComun areaComun){
		Edificio edificio = areaComun.getEdificio();
		if(edificio != null) {
			//Deberia validar lo mismo en el IEdificioDao?
			Edificio edificioBuscado = edificioService.findById(edificio.getId());
			if(edificioBuscado != null) {
				areaComunService.save(areaComun);
				edificio.agregarAreaComun(areaComun);
				edificioService.update(edificio.getId(), edificio);
				return new ResponseEntity<AreaComun>(areaComun, HttpStatus.CREATED);
			}
			String mensaje = "El edificio con id: " + edificio.getId() + " no existe";
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}
		String mensaje = "El edificio es nulo";
		return new ResponseEntity<String>(mensaje, HttpStatus.BAD_REQUEST);
	}
	*/
	
	@DeleteMapping("/{areaComunId}")
	public ResponseEntity<String> deleteById(@PathVariable Long areaComunId){
		Optional<AreaComun> areaComunToDeleteOptional = areaComunService.findById(areaComunId);
		if(areaComunToDeleteOptional.isPresent()) {
			areaComunService.deleteById(areaComunId);
			String mensaje = "Area comun con id: " + areaComunId + " eliminada correctamente!";
			return new ResponseEntity<>(mensaje, OK);
		}
		String mensaje = "El area comun con id: " + areaComunId +  " no existe";
		return new ResponseEntity<>(mensaje, NOT_FOUND);
	}
	
	
	
	
	
}
