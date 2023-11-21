package api.tpo_g04_reclamos.app.controller;

import api.tpo_g04_reclamos.app.controller.dto.ImagenDto;
import api.tpo_g04_reclamos.app.model.entity.Imagen;
import api.tpo_g04_reclamos.app.service.IImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/imagen")
public class ImagenController {

	@Autowired
	private IImagenService imagenService;
	
	@GetMapping("/{imagenId}")
	public ResponseEntity<ImagenDto> findById(@PathVariable Long imagenId){
		Imagen imagen = imagenService.get(imagenId);

		return ok(new ImagenDto(imagen));
	}
	
	@PostMapping
	public ResponseEntity<Long> upload(@RequestParam("file") MultipartFile file){
		Imagen img = imagenService.save(file);
		return ok(img.getId());
	}
}
