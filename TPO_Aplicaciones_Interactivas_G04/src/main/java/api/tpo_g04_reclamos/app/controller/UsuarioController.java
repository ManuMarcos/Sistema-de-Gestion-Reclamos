package api.tpo_g04_reclamos.app.controller;

import api.tpo_g04_reclamos.app.controller.dto.UsuarioDto;
import api.tpo_g04_reclamos.app.model.entity.Usuario;
import api.tpo_g04_reclamos.app.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioDto>> findAll(){
		return ok(UsuarioDto.fromList(usuarioService.findAll()));
	}
	
	@GetMapping("/{usuarioId}")
	public ResponseEntity<UsuarioDto> findById(@PathVariable Long usuarioId){
		Usuario usuario = usuarioService.get(usuarioId);

		return ok(new UsuarioDto(usuario));
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDto> addUsuario(@RequestBody UsuarioDto usuario) {
		Usuario usuarioCreado = usuarioService.save(usuario);
		return new ResponseEntity<>(new UsuarioDto(usuarioCreado), CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDto> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDto usuario) {
		Usuario usuarioActualizado = usuarioService.update(id, usuario);
		return ok(new UsuarioDto(usuarioActualizado));
	}
	
	@DeleteMapping("/{usuarioId}")
	public ResponseEntity<String> deleteUsuario(@PathVariable Long usuarioId){
		usuarioService.deleteById(usuarioId);

		return ok(String.format("deleted usuario %d", usuarioId));
	}
}
