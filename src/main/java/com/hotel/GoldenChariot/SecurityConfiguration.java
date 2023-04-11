package com.hotel.GoldenChariot;

import com.hotel.GoldenChariot.security.CustomAuthenticationFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/resources/**", "/login/**").permitAll()
//                .antMatchers("/account/update", "/account/insert").hasAnyAuthority("Administrator")
//                .antMatchers("/student/**").hasAnyAuthority("Student", "Administrator")
//                .antMatchers("/tutor/**").hasAnyAuthority("Tutor", "Administrator")
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login/loginForm")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/loginProcess")
                .failureHandler(authenticationFailureHandler())
                .and().logout()
                .and().exceptionHandling().accessDeniedPage("/login/accessDenied");
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new CustomAuthenticationFailureHandler();
    }
}
