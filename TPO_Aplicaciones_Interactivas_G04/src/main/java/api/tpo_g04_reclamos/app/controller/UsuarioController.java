package api.tpo_g04_reclamos.app.controller;

import api.tpo_g04_reclamos.app.controller.dto.UsuarioDto;
import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.entity.Usuario;
import api.tpo_g04_reclamos.app.service.IUsuarioService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> findAll(){
		return new ResponseEntity<>(usuarioService.findAll(), OK);
	}
	
	@GetMapping("/{usuarioId}")
	public ResponseEntity<Usuario> findById(@PathVariable Long usuarioId){
		Usuario usuario = usuarioService.findById(usuarioId).orElseThrow(() -> new ItemNotFoundException("El usuario no existe"));

		return new ResponseEntity<>(usuario, OK);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> addUsuario(@RequestBody UsuarioDto usuario) {
		Usuario usuarioCreado = usuarioService.save(usuario);
		return new ResponseEntity<>(usuarioCreado, CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDto usuario) {
		Usuario usuarioActualizado = usuarioService.update(id, usuario);
		return new ResponseEntity<>(usuarioActualizado, OK);
	}
	
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<String> deleteUsuario(@PathVariable Long usuarioId){
		usuarioService.deleteById(usuarioId);

		return new ResponseEntity<>(String.format("deleted usuario %s", usuarioId), OK);
	}
}
