package it.uniroma3.siwpersonale.authentication;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import it.uniroma3.siwpersonale.model.Utente;

public class CustomOAuth2User implements OAuth2User {

    private OAuth2User oauth2User;
    private Utente utente;

    public CustomOAuth2User(OAuth2User oauth2User, Utente utente) {
        this.oauth2User = oauth2User;
        this.utente = utente;
    }

    public Utente getUtente() {
        return this.utente;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oauth2User.getName();
    }
}
