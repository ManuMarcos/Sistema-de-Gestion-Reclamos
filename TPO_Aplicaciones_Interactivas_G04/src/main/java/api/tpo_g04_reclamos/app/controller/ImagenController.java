package api.tpo_g04_reclamos.app.controller;

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

@RestController
@RequestMapping("/imagen")
public class ImagenController {

	@Autowired
	private IImagenService imagenService;
	
	@GetMapping("/{imagenId}")
	public ResponseEntity<Imagen> findById(@PathVariable String imagenId){
		try {
			return new ResponseEntity<Imagen>(imagenService.findById(imagenId), HttpStatus.OK);
		} catch(IllegalArgumentException e) //TODO: una excepcion mas custom capaz
		{
			return new ResponseEntity<Imagen>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file){
		Imagen img = imagenService.save(file);
		return new ResponseEntity<String>("File uploaded: " + img.getId(), HttpStatus.OK);
	}
}
