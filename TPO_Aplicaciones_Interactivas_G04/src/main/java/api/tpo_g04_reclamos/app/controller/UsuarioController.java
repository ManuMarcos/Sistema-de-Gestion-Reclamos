package api.tpo_g04_reclamos.app.controller;

import api.tpo_g04_reclamos.app.model.entity.Usuario;
import api.tpo_g04_reclamos.app.service.IUsuarioService;

import java.util.List;

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
		return new ResponseEntity<List<Usuario>>(usuarioService.findAll(), OK);
	}
	
	@GetMapping("/{usuarioId}")
	public ResponseEntity<Usuario> findById(@PathVariable int usuarioId){
		try {
			return new ResponseEntity<Usuario>(usuarioService.findById(usuarioId).get(), OK);
		} catch(IllegalArgumentException e) //TODO: una excepcion mas custom capaz
		{
			return new ResponseEntity<Usuario>(NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
		usuarioService.save(usuario);
		return new ResponseEntity<Usuario>(usuario, CREATED);
	}
	
	@PutMapping
	public ResponseEntity<String> updateUsuario(@RequestBody Usuario usuario) {
		usuarioService.update(usuario);
		return new ResponseEntity<String>("udpated", OK);
	}
	
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<String> deleteUsuario(@PathVariable int usuarioId){
		try {
			usuarioService.deleteById(usuarioId);
			return new ResponseEntity<String>("deleted", OK);
		} catch(IllegalArgumentException e) //TODO: una excepcion mas custom capaz
		{
			return new ResponseEntity<String>("Not valid Id", NOT_FOUND);
		}
	}
}
