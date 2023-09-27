package api.tpo_g04_reclamos.app.service;

import api.tpo_g04_reclamos.app.controller.dto.UsuarioDto;
import api.tpo_g04_reclamos.app.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
	List<Usuario> findAll();
	
	Optional<Usuario> findById(Long id);
	
	Optional<Usuario> findUser(String username, String password);
	
	Usuario save(UsuarioDto usuario);
	
	Usuario update(Long id, UsuarioDto usuario);
	
	void deleteById(Long id);
}
