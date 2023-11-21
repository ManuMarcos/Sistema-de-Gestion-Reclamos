package api.tpo_g04_reclamos.app;

import api.tpo_g04_reclamos.app.controller.dto.UsuarioDto;
import api.tpo_g04_reclamos.app.model.enums.RoleType;
import api.tpo_g04_reclamos.app.model.enums.TipoUsuario;
import api.tpo_g04_reclamos.app.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TpoAplicacionesInteractivasG04Application implements CommandLineRunner{

	@Autowired 
	private IUsuarioService usuarioService;
	
	public static void main(String[] args) {
		SpringApplication.run(TpoAplicacionesInteractivasG04Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		UsuarioDto usuario = new UsuarioDto("admin", "admin", RoleType.ADMIN, TipoUsuario.PERSONAL_INTERNO);
		usuarioService.save(usuario);

		UsuarioDto usuario2 = new UsuarioDto("no_admin", "no_admin", RoleType.NO_ADMIN, TipoUsuario.PERSONAL_INTERNO);
		usuarioService.save(usuario2);
		
		UsuarioDto usuario3 = new UsuarioDto("prop", "prop", RoleType.NO_ADMIN, TipoUsuario.PROPIETARIO);
		usuarioService.save(usuario3);

		UsuarioDto usuario4 = new UsuarioDto("inqui", "inqui", TipoUsuario.INQUILINO);
		usuarioService.save(usuario4);

	}

	@Bean
	WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
				.addMapping("/**")
				.allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
				.allowedOrigins("http://localhost:3000");
			}
		};
	}
	
}
