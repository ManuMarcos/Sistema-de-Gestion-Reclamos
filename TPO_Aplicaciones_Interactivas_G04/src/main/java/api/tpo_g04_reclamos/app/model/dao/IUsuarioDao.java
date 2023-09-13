package api.tpo_g04_reclamos.app.model.dao;

import java.util.List;
import java.util.Optional;

import api.tpo_g04_reclamos.app.model.entity.Usuario;

public interface IUsuarioDao {
	List<Usuario> findAll();

	Optional<Usuario> findById(Long id);

	Usuario save(Usuario usuario);

	Usuario update(Usuario usuario);

	void deleteById(Long id);
}
