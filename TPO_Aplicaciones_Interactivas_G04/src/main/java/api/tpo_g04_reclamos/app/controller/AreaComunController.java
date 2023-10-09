package api.tpo_g04_reclamos.app.controller;


import api.tpo_g04_reclamos.app.controller.dto.AreaComunDto;
import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import api.tpo_g04_reclamos.app.service.IAreaComunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/areas-comunes")
public class AreaComunController {

	@Autowired
	private IAreaComunService areaComunService;

	@GetMapping
	public List<AreaComunDto> findAll(){
		return AreaComunDto.fromList(areaComunService.findAll());
	}

	@GetMapping("/{areaComunId}")
	public ResponseEntity<AreaComunDto> findById(@PathVariable Long areaComunId){
		AreaComun areaComun = areaComunService.findById(areaComunId).orElseThrow(() -> new ItemNotFoundException(String.format("El area comun con id: %s no existe", areaComunId)));

		return ok(new AreaComunDto(areaComun));
	}

	@PutMapping("/{areaComunId}")
	public ResponseEntity<?> updateAreaComun(@PathVariable Long areaComunId, @RequestBody AreaComun areaComun) {
		Optional<AreaComun> areaComunToUpdate = areaComunService.findById(areaComunId);

		if (areaComunToUpdate.isEmpty()) {
			String mensaje = "Area Comun con id: " + areaComunId + " no encontrada";
			return new ResponseEntity<>(mensaje, NOT_FOUND);
		}
		AreaComun areaComunUpdated = areaComunService.update(areaComunId, areaComun);
		
		return new ResponseEntity<>(new AreaComunDto(areaComunUpdated), OK);
	}

	@DeleteMapping("/{areaComunId}")
	public ResponseEntity<String> deleteById(@PathVariable Long areaComunId){
		AreaComun areaComunToDeleteOptional = areaComunService.findById(areaComunId).orElseThrow(() -> new ItemNotFoundException(String.format("El area comun con id: %s no existe", areaComunId)));
		areaComunService.deleteById(areaComunId);
		
		String mensaje = "Area comun con id: " + areaComunId + " eliminada correctamente!";
		return new ResponseEntity<>(mensaje, OK);
	}
	
}
