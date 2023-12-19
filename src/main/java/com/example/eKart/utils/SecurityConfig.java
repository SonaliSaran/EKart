package com.example.eKart.utils;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtUtils jwtUtils; // Inject your JWT utility class

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    /* @Autowired
     private JwtAuthenticationProvider authenticationProvider;*/
    @Autowired
    private JwtFilter jwtFilter;
    // @Autowired
    // private ClientRegistrationRepository clientRegistrationRepository;
    Logger logger = LoggerFactory.getLogger(getClass());

    /*
     * OidcClientInitiatedLogoutSuccessHandler oidLogoutSuccessHandler() {
     * OidcClientInitiatedLogoutSuccessHandler successHandler = new
     * OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
     * successHandler.setPostLogoutRedirectUri(URI.create(
     * "http://localhost:8080/signOut")); return successHandler; }
     */

    // To access the in-memory authentication using AuthenticationManagerBuilder
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        authenticationManagerBuilder.userDetailsService(jwtAuthenticationProvider);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // to disable the authorization for particular APIs
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("entered override configuration");
        http.csrf().disable().authorizeRequests()
                .antMatchers("/login","/signUp")
                .permitAll().anyRequest().authenticated().and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logOut"));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
                "/configuration/security", "/swagger-ui.html", "/webjars/**");

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(jwtAuthenticationProvider);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        Scanner sc = new Scanner(System.in);
        sc.close();
        return new BCryptPasswordEncoder(20);
    }
}
