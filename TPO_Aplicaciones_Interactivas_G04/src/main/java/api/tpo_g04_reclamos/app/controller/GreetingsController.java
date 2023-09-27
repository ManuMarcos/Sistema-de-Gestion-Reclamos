package api.tpo_g04_reclamos.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludos")
public class GreetingsController {
	
	@GetMapping("/hola")
	public ResponseEntity<String> hola(){
		return new ResponseEntity<>("Hola mundo!", HttpStatus.OK);
	}
	
	

}
