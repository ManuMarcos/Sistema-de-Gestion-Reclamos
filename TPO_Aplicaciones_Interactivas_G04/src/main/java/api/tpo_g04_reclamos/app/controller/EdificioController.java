package api.tpo_g04_reclamos.app.controller;

import java.util.List;
import java.util.Optional;

import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
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

import api.tpo_g04_reclamos.app.model.entity.Edificio;
import api.tpo_g04_reclamos.app.service.IEdificioService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/edificios")
public class EdificioController {
	
	@Autowired
	private IEdificioService edificioService;
	
	@GetMapping
	public List<Edificio> findAll(){
		return edificioService.findAll();
	}
	
	@GetMapping("/{edificioId}")
	public ResponseEntity<?> findById(@PathVariable Long edificioId){
		Optional<Edificio> edificioOptional = edificioService.findById(edificioId);
		if(edificioOptional.isEmpty()) {
			String mensaje =  "El edificio con id: " + edificioId + " no existe";
			return new ResponseEntity<>(mensaje, NOT_FOUND);
		}
		return  ok(edificioOptional.get());
	}
	
	@PostMapping
	public ResponseEntity<Edificio> addEdificio(@RequestBody Edificio edificio) {
		edificioService.save(edificio);
		return new ResponseEntity<>(edificio, CREATED);
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
