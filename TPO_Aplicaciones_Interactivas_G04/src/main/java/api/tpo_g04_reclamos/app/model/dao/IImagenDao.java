package api.tpo_g04_reclamos.app.model.dao;

import api.tpo_g04_reclamos.app.model.entity.Imagen;

public interface IImagenDao {
	Imagen findById(int id);
	void save(Imagen imagen);
	void deleteById(int id);
}
