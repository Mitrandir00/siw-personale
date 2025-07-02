package it.uniroma3.siwpersonale.authentication;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import it.uniroma3.siwpersonale.model.Utente;
import it.uniroma3.siwpersonale.repository.UtenteRepository;

import java.util.Collections;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String email = (String) attributes.get("email");
        String nome = (String) attributes.get("given_name");
        String cognome = (String) attributes.get("family_name");

        Utente utente = utenteRepository.findByEmail(email);

        if (utente == null) {
            utente = new Utente();
            utente.setNome(nome);
            utente.setEmail(email);
            utente.setRuolo(Utente.DEFAULT_ROLE); // ad esempio ROLE_UTENTE
            utenteRepository.save(utente);
        }

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + utente.getRuolo().name())),
                attributes,
                "email");

    }
}
