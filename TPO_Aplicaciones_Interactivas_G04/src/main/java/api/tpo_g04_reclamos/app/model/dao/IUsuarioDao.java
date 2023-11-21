package api.tpo_g04_reclamos.app.model.dao;

import api.tpo_g04_reclamos.app.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioDao {
	List<Usuario> findAll();
	
	List<Usuario> findPropietariosSinUnidad();

	Optional<Usuario> findById(Long id);

	Optional<Usuario> findUser(String username, String password);
	
	Usuario save(Usuario usuario);

	Usuario update(Usuario usuario);

	void deleteById(Long id);
	
}
