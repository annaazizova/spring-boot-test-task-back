package com.aazizova.springboottesttask.config;

import com.aazizova.springboottesttask.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * SecurityConfig.
 */
@EnableWebSecurity(debug = true)
@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * Configure http security.
     */
    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf()
                .disable()
                .formLogin().permitAll()
                .defaultSuccessUrl("/api/products/")
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER);
    }

    /**
     * CORS configuration.
     *
     * @return CorsConfigurationSource
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource src =
                new UrlBasedCorsConfigurationSource();
        src.registerCorsConfiguration("/**", config);
        return src;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
