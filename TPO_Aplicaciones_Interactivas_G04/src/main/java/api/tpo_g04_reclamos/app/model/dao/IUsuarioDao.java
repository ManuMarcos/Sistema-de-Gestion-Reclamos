package api.tpo_g04_reclamos.app.model.dao;

import java.util.List;
import java.util.Optional;

import api.tpo_g04_reclamos.app.model.entity.Usuario;

public interface IUsuarioDao {
	List<Usuario> findAll();

	Optional<Usuario> findById(int id);

	void save(Usuario usuario);

	void update(Usuario usuario);

	void deleteById(int id);
}
