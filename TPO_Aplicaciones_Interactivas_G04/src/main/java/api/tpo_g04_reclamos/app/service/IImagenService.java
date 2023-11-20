package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.model.entity.Imagen;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IImagenService {
	Optional<Imagen> findById(Long id);
	Imagen get(Long id);
	List<Imagen> findAllByIds(List<Long> ids);
	Imagen save(MultipartFile file);
	void deleteById(Long id);
}
