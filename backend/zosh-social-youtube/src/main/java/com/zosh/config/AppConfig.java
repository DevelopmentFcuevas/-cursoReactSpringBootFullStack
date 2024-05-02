
package com.zosh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //http.authorizeHttpRequests(Authorize -> Authorize
        //        .requestMatchers("/api/**").authenticated().anyRequest().permitAll())
        //        .csrf(csrf -> csrf.disable());

        //http.sessionManagement(
        //        management -> management.sessionCreationPolicy(
        //                SessionCreationPolicy.STATELESS))
        //        .authorizeHttpRequests(Authorize -> Authorize
        //                .requestMatchers("/api/**").authenticated()
        //                .anyRequest().permitAll())
        //        //.httpBasic().and()
        //        .csrf(csrf -> csrf.disable());
        //return http.build();

        //http
        //    .authorizeHttpRequests(Authorize -> Authorize
        //        .requestMatchers("/api/**").authenticated()
        //        .anyRequest().permitAll())
        //    .httpBasic().and()
        //    .csrf(csrf -> csrf.disable());
        //return http.build();

        http
            .sessionManagement(management -> management.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(Authorize -> Authorize
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll())
            .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
            .csrf(csrf -> csrf.disable());
        return http.build();

        /* return http
                .csrf(csrf -> csrf.disable())
                //.httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(httpSecurity -> {
                    //1-) Configurar los endpoints publicos
                    httpSecurity.requestMatchers(HttpMethod.GET, "/home/**").permitAll();
                    httpSecurity.requestMatchers(HttpMethod.GET, "/posts/**").permitAll();
                    httpSecurity.requestMatchers(HttpMethod.POST, "/posts/**").permitAll();
                    httpSecurity.requestMatchers(HttpMethod.GET, "/users/**").permitAll();
                    httpSecurity.requestMatchers(HttpMethod.POST, "/users/**").permitAll();
                    //2-) Configurar los endpoints privados
                    httpSecurity.requestMatchers(HttpMethod.GET, "/api/**").authenticated();
                    //httpSecurity.requestMatchers(HttpMethod.GET, "/auth/**");
                    //3-) Configurar el resto de endpoints - NO ESPECIFICADOS
                    //httpSecurity.anyRequest().denyAll(); //Rechaza tod@ lo que no se especifique. Es un poquito más restrictivo y más seguro
                })
                .build(); */

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
