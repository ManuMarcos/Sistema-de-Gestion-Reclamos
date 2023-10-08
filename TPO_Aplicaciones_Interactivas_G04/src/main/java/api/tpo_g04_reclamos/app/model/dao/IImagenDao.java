package api.tpo_g04_reclamos.app.model.dao;

import api.tpo_g04_reclamos.app.model.entity.Imagen;

import java.util.List;
import java.util.Optional;

public interface IImagenDao {
	Optional<Imagen> findById(String id);
	List<Imagen> findAllByIds(List<String> ids);
	Imagen save(Imagen imagen);
	void deleteById(String id);
}
