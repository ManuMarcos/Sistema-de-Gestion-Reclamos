package api.tpo_g04_reclamos.app.service;

import java.util.List;

import api.tpo_g04_reclamos.app.model.entity.Usuario;

public interface IUsuarioService {
	List<Usuario> findAll();
	
	Usuario findById(int id);
	
	void save(Usuario usuario);
	
	void update(Usuario edificio);
	
	void deleteById(int id);
}
