package api.tpo_g04_reclamos.app.service;

import java.util.List;
import java.util.Optional;

import api.tpo_g04_reclamos.app.controller.dto.UsuarioDto;
import api.tpo_g04_reclamos.app.model.entity.Usuario;

public interface IUsuarioService {
	List<Usuario> findAll();
	
	Optional<Usuario> findById(Long id);
	
	Usuario save(UsuarioDto usuario);
	
	Usuario update(Long id, UsuarioDto usuario);
	
	void deleteById(Long id);
}
