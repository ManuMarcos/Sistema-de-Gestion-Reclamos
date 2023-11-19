package api.tpo_g04_reclamos.app.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		return new ResponseEntity<>(new UnidadDto(unidad), OK);
	}
	
	
}
