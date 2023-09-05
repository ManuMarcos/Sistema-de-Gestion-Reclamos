package api.tpo_g04_reclamos.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping
	public ResponseEntity<Edificio> addEdificio(@RequestBody Edificio edificio) {
		edificioService.save(edificio);
		return new ResponseEntity<Edificio>(edificio, HttpStatus.CREATED);
	}
	
}
