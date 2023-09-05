package api.tpo_g04_reclamos.app.controller;

import java.util.List;

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
	public ResponseEntity<?> findById(@PathVariable int edificioId){
		Edificio edificio = edificioService.findById(edificioId);
		if(edificio == null) {
			String mensaje =  "El edificio con id: " + edificioId + " no existe";
			return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Edificio>(edificio, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Edificio> addEdificio(@RequestBody Edificio edificio) {
		edificioService.save(edificio);
		return new ResponseEntity<Edificio>(edificio, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{edificioId}")
	public ResponseEntity<?> updateEdificio(@PathVariable int edificioId, @RequestBody Edificio edificio){
		Edificio edificioToUpdate = edificioService.findById(edificioId);
		if(edificioToUpdate != null) {
			edificioService.update(edificioId, edificio);
			return new ResponseEntity<Edificio>(edificio, HttpStatus.OK);
		}
		String mensaje = "El edificio con id: " + edificioId + " no existe";
		return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{edificioId}")
	public ResponseEntity<String> deleteEdificio(@PathVariable int edificioId){
		Edificio edificioToDelete = edificioService.findById(edificioId);
		if(edificioToDelete != null) {
			edificioService.deleteById(edificioId);
			String mensaje = "Edificio con id: " + edificioId + " eliminado correctamente!";
			return new ResponseEntity<String>(mensaje, HttpStatus.OK);
		}
		String mensaje = "El edificio con id: " + edificioId + " no existe";
		return new ResponseEntity<String>(mensaje, HttpStatus.NOT_FOUND);
	}
	
	
	
	
}
