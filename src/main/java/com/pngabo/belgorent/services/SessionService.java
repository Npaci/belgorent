package com.pngabo.belgorent.services;

import com.pngabo.belgorent.models.forms.LoginForm;
import com.pngabo.belgorent.models.entities.Utilisateur;
import com.pngabo.belgorent.repositories.UtilisateurRepository;
import com.pngabo.belgorent.security.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SessionService {
    private final UtilisateurRepository repository;
    private final AuthenticationManager auth;
    private final JwtProvider jwtProvider;

    public SessionService(UtilisateurRepository repository, AuthenticationManager auth, JwtProvider jwtProvider) {
        this.repository = repository;
        this.auth = auth;
        this.jwtProvider = jwtProvider;
    }

    public String login(LoginForm form) {
        Utilisateur user = repository.findByUsername(form.getUsername())
                .orElseThrow( () -> new UsernameNotFoundException("L'utilisateur n'existe pas"));

        // créer l'authentification
        Authentication authentication = new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword());
        //Tester l'authentication
        auth.authenticate(authentication);
        //-> Ok: Créer token et renvoyer

        return jwtProvider.createToken(user.getUsername(), user.getRoles().stream()
                .map((elm)-> {
                    return elm.getNom();
                })
                .collect(Collectors.toList()));
    }
}
