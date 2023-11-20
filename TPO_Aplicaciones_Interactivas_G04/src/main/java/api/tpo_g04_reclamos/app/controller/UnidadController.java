package api.tpo_g04_reclamos.app.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import java.util.Optional;

import api.tpo_g04_reclamos.app.controller.request.UnidadUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import api.tpo_g04_reclamos.app.controller.dto.EdificioDto;
import api.tpo_g04_reclamos.app.controller.dto.UnidadDto;
import api.tpo_g04_reclamos.app.controller.dto.UsuarioDto;
import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.model.entity.Unidad;
import api.tpo_g04_reclamos.app.model.entity.Usuario;
import api.tpo_g04_reclamos.app.service.IUnidadService;

@RestController
@RequestMapping("/unidad")
public class UnidadController {
	
	@Autowired
	private IUnidadService unidadService;
	
	@GetMapping("/{unidadId}")
	public ResponseEntity<?> findById(@PathVariable Long unidadId){
		Unidad unidad = unidadService.findById(unidadId).orElseThrow(() -> new ItemNotFoundException("La unidad no existe"));
		return ok(new UnidadDto(unidad));
	}

	@DeleteMapping("/{unidadId}")
	public ResponseEntity<Void> delete(@PathVariable("unidadId") Long unidadId) {
		unidadService.deleteById(unidadId);

		return noContent().build();
	}

	@PutMapping("/{unidadId}")
	public ResponseEntity<UnidadDto> update(@PathVariable("unidadId") Long unidadId, @RequestBody UnidadUpdateRequestDto updateRequest) {
		Unidad updatedUnidad = unidadService.update(unidadId, updateRequest);

		return ok(new UnidadDto(updatedUnidad));
	}
	
}
