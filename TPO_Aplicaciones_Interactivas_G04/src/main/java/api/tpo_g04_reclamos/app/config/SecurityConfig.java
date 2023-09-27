package api.tpo_g04_reclamos.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
    	//Esto indica que todas las request requieren de autenticacion
		http.authorizeHttpRequests(
				(authz -> authz.anyRequest().authenticated()));
		return http.build();
	}
	
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
    	
    	//Aquellas urls que esten aca no requeriran autenticacion
    	return (web) -> web.ignoring().requestMatchers("auth/login");
    }
	
	
	
	
}
