package api.tpo_g04_reclamos.app.service;

import java.util.List;
import java.util.Optional;

import api.tpo_g04_reclamos.app.model.entity.Usuario;

public interface IUsuarioService {
	List<Usuario> findAll();
	
	Optional<Usuario> findById(int id);
	
	void save(Usuario usuario);
	
	void update(Usuario edificio);
	
	void deleteById(int id);
}
