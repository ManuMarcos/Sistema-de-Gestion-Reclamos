package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.model.entity.Imagen;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface IImagenService {
	Optional<Imagen> findById(String id);
	Imagen save(MultipartFile file);
	void deleteById(String id);
}
