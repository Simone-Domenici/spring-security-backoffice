package org.backoffice.java.videogames_spring_backoffice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    @Bean
    @SuppressWarnings("removal")
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.httpBasic();
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/api/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE,"/api/**").hasAuthority("ADMIN")
                .requestMatchers("/videogames/create", "/videogames/edit/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/videogames/**").hasAuthority("ADMIN")
                .requestMatchers("/consoles", "/consoles/**").hasAuthority("ADMIN")
                .requestMatchers("/genres", "/genres/**").hasAuthority("ADMIN")
                .requestMatchers("/videogames", "/videogames/**").hasAnyAuthority("USER","ADMIN")
                .requestMatchers("/**").permitAll()
                .and().formLogin()
                .and().logout()
                .and().exceptionHandling()
                .and().csrf().disable();
                
        return http.build();
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailService());

        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    DataBaseUserDetailService userDetailService(){
        return new DataBaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
        }
    };
}
}
