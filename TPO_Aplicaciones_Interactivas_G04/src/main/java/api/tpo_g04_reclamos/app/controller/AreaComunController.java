package api.tpo_g04_reclamos.app.controller;

import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import api.tpo_g04_reclamos.app.service.IAreaComunService;
import api.tpo_g04_reclamos.app.service.IEdificioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/areasComunes")
public class AreaComunController {

	@Autowired
	private IAreaComunService areaComunService;
	

	@Autowired
	private IEdificioService edificioService;
	
	
	

	
	@GetMapping("/{areaComunId}")
	public ResponseEntity<?> findById(@PathVariable Long areaComunId){
		AreaComun areaComun = areaComunService.findById(areaComunId).orElseThrow(() -> new ItemNotFoundException(String.format("El area comun con id: %s no existe", areaComunId)));

		return ok(areaComun);
	}
	
	/*
	@PostMapping
	public ResponseEntity<?> addAreaComun(@RequestBody AreaComunRequestDto areaComunRequestDto){
		//AreaComunDto areaComunDto = objectMapper.readValue(areaComun, AreaComunDto.class);
		//Compruebo que haya un edificio con id
		if(areaComunRequestDto != null) {
			Edificio edificio = edificioService.findById(areaComunRequestDto.getEdificioDto().getId());
			//Compruebo si el edificio existe
			if (edificio != null) {
				areaComunService.save(new AreaComun(edificio, areaComunRequestDto.getNombre()));
				return new ResponseEntity<AreaComunRequestDto>(areaComunRequestDto, HttpStatus.CREATED);
			}
			String mensaje = "El edificio con el id: " + areaComunRequestDto.getEdificioDto().getId() + " no existe";
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}
		String mensaje = "El edificio es nulo";
		return new ResponseEntity<String>(mensaje, HttpStatus.BAD_REQUEST);
	}
	*/
	
	
	
	@DeleteMapping("/{areaComunId}")
	public ResponseEntity<String> deleteById(@PathVariable Long areaComunId){
		AreaComun areaComunToDeleteOptional = areaComunService.findById(areaComunId).orElseThrow(() -> new ItemNotFoundException(String.format("El area comun con id: %s no existe", areaComunId)));
		areaComunService.deleteById(areaComunId);
		
		String mensaje = "Area comun con id: " + areaComunId + " eliminada correctamente!";
		return new ResponseEntity<>(mensaje, OK);
	}
	

	
	
	
}
