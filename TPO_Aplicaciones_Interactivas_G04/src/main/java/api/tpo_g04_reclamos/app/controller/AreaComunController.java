package api.tpo_g04_reclamos.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.tpo_g04_reclamos.app.model.dao.IEdificioDao;
import api.tpo_g04_reclamos.app.model.dto.AreaComunDto;
import api.tpo_g04_reclamos.app.model.dto.AreaComunRequestDto;
import api.tpo_g04_reclamos.app.model.entity.AreaComun;
import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.service.IAreaComunService;
import api.tpo_g04_reclamos.app.service.IEdificioService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/areasComunes")
public class AreaComunController {

	@Autowired
	private IAreaComunService areaComunService;
	
	@Autowired
	private IEdificioService edificioService;
	
	
	
	
	@GetMapping
	public List<AreaComunDto> findAll(){
		List<AreaComun> areasComunes = areaComunService.findAll();
		List<AreaComunDto> areasComunesDtos = new ArrayList<>();
		
		for(AreaComun areaComun : areasComunes) {
			AreaComunDto areaComunDto = convertToDto(areaComun);
			areasComunesDtos.add(areaComunDto);
		}
		return areasComunesDtos;
	}
	
	@GetMapping("/{areaComunId}")
	public ResponseEntity<?> findById(@PathVariable int areaComunId){
		AreaComun areaComun = areaComunService.findById(areaComunId);
		
		if(areaComun == null) {
			String mensaje = "El area comun con id:" +  areaComunId + " no existe";
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}
		return ok(areaComun);
	}
	
	
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
	
	
	
	@DeleteMapping("/{areaComunId}")
	public ResponseEntity<String> deleteById(@PathVariable int areaComunId){
		AreaComun areaComunToDelete = areaComunService.findById(areaComunId);
		if(areaComunToDelete != null) {
			areaComunService.deleteById(areaComunId);
			String mensaje = "Area comun con id: " + areaComunId + " eliminada correctamente!";
			return new ResponseEntity<String>(mensaje, HttpStatus.OK);
		}
		String mensaje = "El area comun con id: " + areaComunId +  " no existe";
		return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
	}
	
	
	public AreaComun convertToEntity(AreaComunRequestDto areaComunDto) {
		Edificio edificio = edificioService.findById(areaComunDto.getEdificioDto().getId());
		return new AreaComun(edificio, areaComunDto.getNombre());
	}
	
	public AreaComunDto convertToDto(AreaComun areaComun) {
		Edificio edificio = edificioService.findById(areaComun.getEdificio().getId());
		return new AreaComunDto(areaComun.getId(), areaComun.getNombre());
	}
	
	
	
}
