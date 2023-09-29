package api.tpo_g04_reclamos.app.config;


import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
    	//Esto indica que todas las request requieren de autenticacion
		http.authorizeHttpRequests(
				(authz) -> authz.anyRequest().authenticated())
				.addFilterBefore(jwtAuth(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
    @Bean
    JwtAuthFilter jwtAuth() {
    	return new JwtAuthFilter(secretKey());
    }
    
    
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
    	
    	//Aquellas urls que esten aca no requeriran autenticacion
    	return (web) -> web.ignoring()
    			.requestMatchers("auth/login");
    			//.requestMatchers(HttpMethod.POST, "/usuarios");
    }
	
    
    @Bean
    InMemoryUserDetailsManager userDetailsService() {
    	UserDetails user = User.withDefaultPasswordEncoder()
    			.username("admin")
    			.password("password")
    			.build();
    	return new InMemoryUserDetailsManager(user);
    }
    
    
    /**
     * Genera la clave que sera utilizada para firmar el token
     * @return
     */
    @Bean
	SecretKey secretKey() {
		//Genera una clave por si solo para el algoritmo HS256
    	SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
		/*
		byte[] encodedKey = secretKey.getEncoded();
		String encodedKeyBase64 = Base64.getEncoder().encodeToString(encodedKey);
		System.out.println("Secret Key (Base64): " + encodedKeyBase64);
		*/
		
		return secretKey;
	}
	
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
	
}
