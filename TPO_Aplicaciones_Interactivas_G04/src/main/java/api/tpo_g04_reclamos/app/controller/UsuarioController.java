package api.tpo_g04_reclamos.app.controller;

import api.tpo_g04_reclamos.app.controller.dto.UsuarioDto;
import api.tpo_g04_reclamos.app.exception.exceptions.ItemNotFoundException;
import api.tpo_g04_reclamos.app.model.entity.Usuario;
import api.tpo_g04_reclamos.app.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioDto>> findAll(){
		return new ResponseEntity<>(UsuarioDto.fromList(usuarioService.findAll()), OK);
	}
	
	@GetMapping("/{usuarioId}")
	public ResponseEntity<UsuarioDto> findById(@PathVariable Long usuarioId){
		Usuario usuario = usuarioService.findById(usuarioId).orElseThrow(() -> new ItemNotFoundException("El usuario no existe"));

		return new ResponseEntity<>(new UsuarioDto(usuario), OK);
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDto> addUsuario(@RequestBody UsuarioDto usuario) {
		Usuario usuarioCreado = usuarioService.save(usuario);
		return new ResponseEntity<>(new UsuarioDto(usuarioCreado), CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDto> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDto usuario) {
		Usuario usuarioActualizado = usuarioService.update(id, usuario);
		return new ResponseEntity<>(new UsuarioDto(usuarioActualizado), OK);
	}
	
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<String> deleteUsuario(@PathVariable Long usuarioId){
		usuarioService.deleteById(usuarioId);

		return new ResponseEntity<>(String.format("deleted usuario %s", usuarioId), OK);
	}
}
