package api.tpo_g04_reclamos.app.controller;

import api.tpo_g04_reclamos.app.controller.dto.LoginResponse;
import api.tpo_g04_reclamos.app.controller.dto.UsuarioDto;
import api.tpo_g04_reclamos.app.model.entity.Usuario;
import api.tpo_g04_reclamos.app.model.enums.TipoUsuario;
import api.tpo_g04_reclamos.app.service.IEdificioService;
import api.tpo_g04_reclamos.app.service.IUsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final Integer EXPIRATION_TIME_IN_MIN = 100;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IEdificioService edificioService;

	@Autowired
	private SecretKey secretKey;

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody UsuarioDto credentials){

		//Se validan las credenciales
		Optional<Usuario> u = usuarioService.findUser(credentials.getNombre(), credentials.getPassword());
		if(u.isPresent()) {
			String token =
					Jwts.builder()
					.setSubject(credentials.getNombre())
					.setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MIN * 60 * 1000))
					.claim("role", "ROLE_" + u.get().getRoleType())
					.signWith(secretKey, SignatureAlgorithm.HS256)
					.compact();

			Long idEdificio = -1L;
			var listEdificios = edificioService.findByUsuarioId(u.get().getId());
			if(u.get().getTipoUsuario() != TipoUsuario.PERSONAL_INTERNO){
				if(listEdificios.size() == 0) {
					return new ResponseEntity<>(new LoginResponse("Usuario sin edificios asociados", -2L, u.get().getTipoUsuario(), -1L), UNAUTHORIZED);
				}
				if(listEdificios.size() > 1) {
					// Inquilino solo debería estar en un edificio residiendo...
					return new ResponseEntity<>(new LoginResponse("Usuario tiene varios edificios asociados. No soportado.", -3L, u.get().getTipoUsuario(), -1L), UNAUTHORIZED);
				}
				idEdificio = listEdificios.get(0).getId();
			}
			return ok(new LoginResponse(token, u.get().getId(), u.get().getTipoUsuario(), idEdificio, u.get().getRoleType()));
		}
		else {
			return new ResponseEntity<>(new LoginResponse("Credenciales invalidas", -1L, TipoUsuario.PROPIETARIO, -1L), UNAUTHORIZED);
		}
	}
	
	
}
