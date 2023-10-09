package api.tpo_g04_reclamos.app;

import api.tpo_g04_reclamos.app.controller.dto.UsuarioDto;
import api.tpo_g04_reclamos.app.model.enums.TipoUsuario;
import api.tpo_g04_reclamos.app.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		UsuarioDto usuario = new UsuarioDto("admin", "admin", TipoUsuario.PERSONAL_INTERNO);
		usuarioService.save(usuario);
	}

}
