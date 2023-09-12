package api.tpo_g04_reclamos.app.model.dao;

import api.tpo_g04_reclamos.app.model.entity.Imagen;

import java.util.Optional;

public interface IImagenDao {
	Optional<Imagen> findById(String id);
	Imagen save(Imagen imagen);
	void deleteById(String id);
}
