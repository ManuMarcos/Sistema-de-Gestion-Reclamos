package api.tpo_g04_reclamos.app.controller;

import api.tpo_g04_reclamos.app.controller.dto.LoginResponse;
import api.tpo_g04_reclamos.app.controller.dto.UsuarioDto;
import api.tpo_g04_reclamos.app.model.entity.Usuario;
import api.tpo_g04_reclamos.app.model.enums.TipoUsuario;
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

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final int EXPIRATION_TIME_IN_MIN = 100;
	
	@Autowired
	private IUsuarioService usuarioService;
	
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
					.signWith(secretKey, SignatureAlgorithm.HS256)
					.compact();
					
			Long idEdificioHardCodeado = 1L; // TODO: ver como obtener este dato...
			return new ResponseEntity<>(new LoginResponse(token, u.get().getId(), u.get().getTipoUsuario(), idEdificioHardCodeado), OK);
		}
		else {
			return new ResponseEntity<>(new LoginResponse("Credenciales invalidas", -1L, TipoUsuario.PROPIETARIO, -1L), UNAUTHORIZED);
		}
	}
	
	
}
