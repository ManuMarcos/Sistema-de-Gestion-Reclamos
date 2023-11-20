package api.tpo_g04_reclamos.app.controller;


import api.tpo_g04_reclamos.app.controller.dto.AreaComunDto;
import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import api.tpo_g04_reclamos.app.service.IAreaComunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
		AreaComun areaComun = areaComunService.get(areaComunId);

		return ok(new AreaComunDto(areaComun));
	}

	@PutMapping("/{areaComunId}")
	public ResponseEntity<?> updateAreaComun(@PathVariable Long areaComunId, @RequestBody AreaComun areaComun) {
		AreaComun areaComunUpdated = areaComunService.update(areaComunId, areaComun);

		return ok(new AreaComunDto(areaComunUpdated));
	}

	@DeleteMapping("/{areaComunId}")
	public ResponseEntity<String> deleteById(@PathVariable Long areaComunId){
		areaComunService.deleteById(areaComunId);
		
		String mensaje = "Area comun con id: " + areaComunId + " eliminada correctamente!";
		return new ResponseEntity<>(mensaje, OK);
	}
	
}
