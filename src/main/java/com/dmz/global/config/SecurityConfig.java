package com.dmz.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dmz.global.filter.CorsFilter;
import com.dmz.global.filter.JwtFilter;
import com.dmz.global.jwt.JwtAccessDeniedHandler;
import com.dmz.global.jwt.JwtAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;

/**
 * packageName    : om.dmz.global.config
 * fileName       : SecurityConfig
 * author         : Minkyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-09-07        Minkyu Park       최초 생성
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
	// private final SuccessHandler successHandler;
	// private final FailureHandler failureHandler;


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.csrf(
				AbstractHttpConfigurer::disable
			)
			.cors(cors -> cors
				.configurationSource(corsFilter.corsConfigurationSource())
			)
			.headers(
				(headerConfig) ->
					headerConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
			)
			.authorizeHttpRequests((authorizeRequests) ->
				authorizeRequests
					// .requestMatchers(PathRequest.toH2Console()).permitAll()
					.anyRequest().permitAll()
			)
			.exceptionHandling((exceptionConfig) ->
				exceptionConfig
					.authenticationEntryPoint(jwtAuthenticationEntryPoint)
					.accessDeniedHandler(jwtAccessDeniedHandler)
			)
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
			// .oauth2Login(oauth2LoginConfigurer -> oauth2LoginConfigurer
			// 	.userInfoEndpoint(service -> service.userService(oAuth2UserService))
			// 	.successHandler(successHandler)
			// 	.failureHandler(failureHandler)
			// )
		;

		return http.build();
	}
}
