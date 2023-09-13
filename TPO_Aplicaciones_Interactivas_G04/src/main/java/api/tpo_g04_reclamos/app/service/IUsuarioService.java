package api.tpo_g04_reclamos.app.service;

import java.util.List;
import java.util.Optional;

import api.tpo_g04_reclamos.app.model.entity.Usuario;

public interface IUsuarioService {
	List<Usuario> findAll();
	
	Optional<Usuario> findById(Long id);
	
	Usuario save(Usuario usuario);
	
	Usuario update(Usuario edificio);
	
	void deleteById(Long id);
}
