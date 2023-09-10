package api.tpo_g04_reclamos.app.service;

import org.springframework.web.multipart.MultipartFile;

import api.tpo_g04_reclamos.app.model.entity.Imagen;

public interface IImagenService {
	Imagen findById(String id);
	Imagen save(MultipartFile file);
	void deleteById(String id);
}
