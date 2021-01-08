package org.springframework.samples.petclinic.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author japarejo
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**","/webjars/**","/h2-console/**").permitAll()
				.antMatchers(HttpMethod.GET, "/","/oups").permitAll()
				.antMatchers("/error").permitAll()
				.antMatchers("/403").permitAll()
				.antMatchers("/403/**").permitAll()
				.antMatchers("/duenosAdoptivos/findAll").permitAll()
				.antMatchers("/duenosAdoptivos/**").permitAll()
				.antMatchers("/duenosAdoptivos").permitAll()
				.antMatchers("/duenoAdoptivo/new").permitAll()
				.antMatchers("/admin/**").hasAnyAuthority("director")
				.antMatchers("/duenosAdoptivos/edit/**").hasAnyAuthority("duenoAdoptivo","director")	
				.antMatchers("/cuidadores/edit/**").hasAnyAuthority("cuidador","director")
				.antMatchers("/duenosAdoptivos/editByName/**").hasAnyAuthority("duenoadoptivo","director")
				.antMatchers("/cuidadores/nuevo").hasAnyAuthority("cuidador","director")
				.antMatchers("/cuidador/nuevo").hasAnyAuthority("cuidador","director")
				.antMatchers("/centros").hasAnyAuthority("director","cuidador","duenoAdoptivo")
				.antMatchers("/centros/show/**").hasAnyAuthority("director","cuidador","duenoadoptivo")
				.antMatchers("/adopcion/findAll").hasAnyAuthority("director")
				.antMatchers("/animales/show/**").permitAll()
				.antMatchers("/animales/edit/**").hasAnyAuthority("director")
				.antMatchers("/animales").permitAll()
				.antMatchers("/adopcion").hasAnyAuthority("director")
				.antMatchers("/adopcion/misSolicitudesDeAdopcion").hasAnyAuthority("duenoadoptivo")
				.antMatchers("/adopcion/findAllByDuenoAdoptivo/**").hasAnyAuthority("director","cuidador")
				.antMatchers("/adopcion/new").hasAnyAuthority("duenoadoptivo")
				.antMatchers("/visitas/misVisitas").hasAnyAuthority("duenoadoptivo")
				.antMatchers("/cuidadores/show/**").hasAnyAuthority("director","cuidador","duenoadoptivo")
				.antMatchers("/cuidadores/findAllByCentro/**").hasAnyAuthority("director")
				.antMatchers("/cuidadores").hasAnyAuthority("director")
				.antMatchers("/eventos/nuevo").hasAnyAuthority("director")
				.antMatchers("/eventos/director/misEventos").hasAnyAuthority("director")
				.antMatchers("/eventos/show/**").hasAnyAuthority("director","cuidador","duenoadoptivo")
				.antMatchers("/eventos/*/quitarCuidador/*").hasAnyAuthority("director")
				.antMatchers("/eventos/*/añadirCuidador/*").hasAnyAuthority("director")
				.antMatchers("/eventos/misEventos").hasAnyAuthority("duenoadoptivo")
				.antMatchers("/eventos").hasAnyAuthority("duenoadoptivo,cuidador,director")
				.antMatchers("/eventos/*/borrarse").hasAnyAuthority("duenoadoptivo")
				.antMatchers("/eventos/*/inscribirse").hasAnyAuthority("duenoadoptivo")
				.antMatchers("/cuidadores/show/**").hasAnyAuthority("director","cuidador","duenoadoptivo")
				.antMatchers("/cuidadores/**/directorEdit").hasAnyAuthority("director")
				.antMatchers("/comentario/new/**").authenticated()
				.antMatchers("/visitas/cuidador/misVisitas").hasAuthority("cuidador")
				.antMatchers("/visitas/show/**").authenticated()
				.antMatchers("/enfermedad/nuevo/**").hasAnyAuthority("director","cuidador")
				.antMatchers("/enfermedad/edit/**").hasAnyAuthority("director","cuidador")
				.antMatchers("/enfermedad/show/**").hasAnyAuthority("director","cuidador")
				.antMatchers("/categoria/nuevo/**").hasAnyAuthority("director")
				.antMatchers("/animales/nuevo/**").hasAnyAuthority("director")
				.antMatchers("/animales/reincorporar/**").hasAnyAuthority("director")
				.antMatchers("/animales/todos").hasAnyAuthority("director")
				.antMatchers("/adopcion/new/**").hasAnyAuthority("duenoadoptivo")
				.antMatchers("/adopcion/pendientes").hasAnyAuthority("director")
				.antMatchers("/adopcion/show/**").hasAnyAuthority("director","duenoadoptivo")
				.antMatchers("/adopcion/actualizarEstado/**").hasAnyAuthority("director")
				.anyRequest().denyAll()
				.and()
				 	.formLogin()
				 	/*.loginPage("/login")*/
				 	.failureUrl("/login-error")
				.and()
					.logout()
						.logoutSuccessUrl("/"); 
                // Configuración para que funcione la consola de administración 
                // de la BD H2 (deshabilitar las cabeceras de protección contra
                // ataques de tipo csrf y habilitar los framesets si su contenido
                // se sirve desde esta misma página.
                http.csrf().ignoringAntMatchers("/h2-console/**");
                http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .usersByUsernameQuery(
	       "select username,password,enabled "
	        + "from users "
	        + "where username = ?")
	      .authoritiesByUsernameQuery(
	       "select username, authority "
	        + "from authorities "
	        + "where username = ?")	      	      
	      .passwordEncoder(passwordEncoder());	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {	    
		PasswordEncoder encoder =  NoOpPasswordEncoder.getInstance();
	    return encoder;
	}
	
}

