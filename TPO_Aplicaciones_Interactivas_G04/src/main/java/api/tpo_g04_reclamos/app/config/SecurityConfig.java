package api.tpo_g04_reclamos.app.config;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

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
    	return (web) -> web.ignoring().requestMatchers("auth/login", "saludos/hola");
    }
	
    @Bean
	SecretKey secretKey() {
		//Genera una clave por si solo para el algoritmo HS256
		SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		
		byte[] encodedKey = secretKey.getEncoded();
		String encodedKeyBase64 = Base64.getEncoder().encodeToString(encodedKey);
		System.out.println("Secret Key (Base64): " + encodedKeyBase64);
		
		return secretKey;
	}
	
	
}
