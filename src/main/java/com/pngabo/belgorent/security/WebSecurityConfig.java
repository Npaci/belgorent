package com.pngabo.belgorent.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(JwtProvider jwtProvider, UserDetailsService userDetailsService) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable();

        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(HttpMethod.POST, "/marque/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.GET, "/marque/**").hasAuthority("ADMIN")//Pas besoin d'etre connecté pour demander des marques
                .antMatchers(HttpMethod.PATCH, "/marque/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/marque/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST, "/modele/**").hasAuthority("ADMIN")
//                .antMatchers(HttpMethod.GET, "/modele/**").hasAuthority("ADMIN")//Pas besoin d'etre connecté pour demander des modeles
                .antMatchers(HttpMethod.PATCH, "/modele/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/modele/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST, "/voiture/filter").permitAll()
                .antMatchers(HttpMethod.POST, "/voiture/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/voiture/colors").permitAll()
                .antMatchers(HttpMethod.GET, "/voiture/fuels").permitAll()
                .antMatchers(HttpMethod.GET, "/voiture/types").permitAll()
                .antMatchers(HttpMethod.GET, "/voiture/ready").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/voiture/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/voiture/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/voiture/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.POST, "/location/**").hasAuthority("USER")
                .antMatchers(HttpMethod.GET, "/location/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/location/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/location/**").hasAuthority("ADMIN")

//                .antMatchers(HttpMethod.POST, "/client/**").hasAuthority("USER")//Pas besoin d'etre connecter pour créer un user
                .antMatchers(HttpMethod.GET, "/client/**").hasAuthority("ADMIN")//Pour l'instant
                .antMatchers(HttpMethod.PATCH, "/client/**").hasAuthority("ADMIN")//Pour l'instant
                .antMatchers(HttpMethod.DELETE, "/client/**").hasAuthority("ADMIN")//Pour l'instant

                .antMatchers(HttpMethod.POST, "/admin/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/admin/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/admin/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/admin/**").hasAuthority("ADMIN")


                .anyRequest().permitAll();

        http.addFilterBefore(new JwtAuthorizationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
