package com.RAGBasedSystem.EdugenaiRAGSystem.config;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.RAGBasedSystem.EdugenaiRAGSystem.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        System.out.println("SECURITY CONFIG LOADED");

        http
            .csrf(csrf -> csrf.disable())

            .formLogin(form -> form.disable())

            .httpBasic(basic -> basic.disable())

            .sessionManagement(session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            )

            .exceptionHandling(exception -> exception

              
                .authenticationEntryPoint(
                    new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
                )

             
                .accessDeniedHandler(
                    (request, response, accessDeniedException) -> {
                        response.setStatus(
                            HttpStatus.FORBIDDEN.value()
                        );
                    }
                )
            )

            .authorizeHttpRequests(auth -> auth

                // Public
                .requestMatchers(
                    "/auth/register",
                    "/auth/login"
                ).permitAll()

               
                .requestMatchers(
                	    "/chatbot/**",
                	    "/advanced-rag/**",
                	    "/api/documents",
                	    "/api/search",
                	    "/api/download"
                	).hasAnyRole(
                	    "STUDENT",
                	    "FACULTY",
                	    "ADMIN"
                	)

               
                .requestMatchers(
                    "/api/upload",
                    "/question-paper/**"
                ).hasAnyRole(
                    "FACULTY",
                    "ADMIN"
                )

                .anyRequest().authenticated()
            )

            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}


