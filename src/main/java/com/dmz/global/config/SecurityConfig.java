package com.dmz.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dmz.global.filter.CorsFilter;
import com.dmz.global.filter.JwtFilter;
import com.dmz.global.jwt.JwtAccessDeniedHandler;
import com.dmz.global.jwt.JwtAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;

/**
 * packageName    : com.idenit.global.config
 * fileName       : SecurityConfig
 * author         : Hyuk Kim
 * date           : 2023-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Hyuk Kim       최초 생성
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtFilter jwtFilter;

	private final CorsFilter corsFilter;

	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(CsrfConfigurer::disable)
			.httpBasic(HttpBasicConfigurer::disable)
			.cors(cors -> cors
				.configurationSource(corsFilter.corsConfigurationSource())
			)
			.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.exceptionHandling(exception -> exception
				.accessDeniedHandler(jwtAccessDeniedHandler)
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			)
			.authorizeHttpRequests(request -> request
				.anyRequest()
				.permitAll()
			)
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
