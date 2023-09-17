package api.tpo_g04_reclamos.app.controller;

import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.entity.Imagen;
import api.tpo_g04_reclamos.app.service.IImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/imagen")
public class ImagenController {

	@Autowired
	private IImagenService imagenService;
	
	@GetMapping("/{imagenId}")
	public ResponseEntity<Imagen> findById(@PathVariable String imagenId){
		Imagen imagen = imagenService.findById(imagenId).orElseThrow(() -> new ItemNotFoundException("La imagen no existe"));

		return new ResponseEntity<>(imagen, OK);
	}
	
	@PostMapping
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file){
		Imagen img = imagenService.save(file);
		return new ResponseEntity<>("File uploaded: " + img.getId(), OK);
	}
}
