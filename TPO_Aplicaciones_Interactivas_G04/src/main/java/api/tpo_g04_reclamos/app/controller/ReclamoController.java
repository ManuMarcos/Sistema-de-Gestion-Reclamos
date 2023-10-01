package api.tpo_g04_reclamos.app.controller;

import api.tpo_g04_reclamos.app.controller.dto.ReclamoSearchDto;
import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.dto.ReclamoDto;
import api.tpo_g04_reclamos.app.model.entity.Reclamo;
import api.tpo_g04_reclamos.app.service.IReclamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/reclamos")
public class ReclamoController {

    @Autowired
    private IReclamoService reclamoService;

	@GetMapping
	public ResponseEntity<List<Reclamo>> findAll(){
		return new ResponseEntity<>(reclamoService.findAll(), OK);
	}

	@GetMapping("/filtro")
	public ResponseEntity<List<Reclamo>> findAllByEstado(@RequestBody ReclamoSearchDto reclamoSearchDto){
		return new ResponseEntity<>(reclamoService.findByEstado(reclamoSearchDto.getEstado()), OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Reclamo> findById(@PathVariable Long id){
		Reclamo reclamo = reclamoService.findById(id).orElseThrow(() -> new ItemNotFoundException("El reclamo no existe"));

		return new ResponseEntity<>(reclamo, OK);
	}
	
	@PostMapping
	public ResponseEntity<Reclamo> createReclamo(@RequestBody ReclamoDto reclamo) {
		Reclamo reclamoCreado = reclamoService.save(reclamo);
		return new ResponseEntity<>(reclamoCreado, CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Reclamo> updateReclamo(@PathVariable Long id, @RequestBody ReclamoDto reclamo) {
		Reclamo reclamoActualizado = reclamoService.update(id, reclamo);
		return new ResponseEntity<>(reclamoActualizado, OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteReclamo(@PathVariable Long id){
		reclamoService.deleteById(id);

		return new ResponseEntity<>(String.format("deleted reclamo %s", id), OK);
	}
}
