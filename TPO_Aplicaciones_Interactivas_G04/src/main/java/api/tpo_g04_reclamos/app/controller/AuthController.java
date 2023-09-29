package api.tpo_g04_reclamos.app.controller;

import java.security.PrivateKey;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.tpo_g04_reclamos.app.controller.dto.UsuarioDto;
import api.tpo_g04_reclamos.app.service.IUsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final int EXPIRATION_TIME_IN_MIN = 1;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private SecretKey secretKey;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UsuarioDto credentials){
		
		//Se validan las credenciales
		if(usuarioService.findUser(credentials.getNombre(), credentials.getPassword()).isPresent()) {
			String token = 
					Jwts.builder()
					.setSubject(credentials.getNombre())
					.setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MIN * 60 * 1000))
					.signWith(secretKey, SignatureAlgorithm.HS256)
					.compact();
					
			return new ResponseEntity<>(token, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Credenciales invalidas", HttpStatus.UNAUTHORIZED);
		}
	}
	
	
}
