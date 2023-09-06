package api.tpo_g04_reclamos.app.model.dao;

import java.util.List;

import api.tpo_g04_reclamos.app.model.entity.Usuario;

public interface IUsuarioDao {
	List<Usuario> findAll();

	Usuario findById(int id);

	void save(Usuario usuario);

	void update(Usuario usuario);

	void deleteById(int id);
}
