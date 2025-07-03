package it.uniroma3.siwpersonale.authentication;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import it.uniroma3.siwpersonale.model.Utente;

/**
 * Wrapper per integrare OAuth2User con il modello Utente del sistema.
 */
public class CustomOAuth2User implements OAuth2User, UserDetails {

    private final OAuth2User oauth2User;
    private final Utente utente;

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
    public String getUsername() {
        return utente.getEmail(); // usato da Spring Security
    }

    @Override
    public String getPassword() {
        return null; // login Google: non serve password
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        // ritorna l'identificativo univoco: usiamo l'email
        return utente.getEmail();
    }
}
