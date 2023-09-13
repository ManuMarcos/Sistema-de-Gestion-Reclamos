package api.tpo_g04_reclamos.app.controller;

import java.util.List;
import java.util.Optional;

import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
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
	
	@GetMapping
	public List<AreaComun> findAll(){
		return areaComunService.findAll();
	}
	
	@GetMapping("/{areaComunId}")
	public ResponseEntity<?> findById(@PathVariable Long areaComunId){
		AreaComun areaComun = areaComunService.findById(areaComunId).orElseThrow(() -> new ItemNotFoundException(String.format("El area comun con id: %s no existe", areaComunId)));

		return ok(areaComun);
	}
	
	@DeleteMapping("/{areaComunId}")
	public ResponseEntity<String> deleteById(@PathVariable Long areaComunId){
		AreaComun areaComunToDeleteOptional = areaComunService.findById(areaComunId).orElseThrow(() -> new ItemNotFoundException(String.format("El area comun con id: %s no existe", areaComunId)));
		areaComunService.deleteById(areaComunId);
		
		String mensaje = "Area comun con id: " + areaComunId + " eliminada correctamente!";
		return new ResponseEntity<>(mensaje, OK);
	}
	
	
	
	
	
}
