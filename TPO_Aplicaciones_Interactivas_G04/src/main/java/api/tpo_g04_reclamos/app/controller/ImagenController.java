package api.tpo_g04_reclamos.app.controller;

import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import api.tpo_g04_reclamos.app.model.entity.Imagen;
import api.tpo_g04_reclamos.app.service.IImagenService;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
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
