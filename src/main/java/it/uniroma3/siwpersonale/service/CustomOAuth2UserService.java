package it.uniroma3.siwpersonale.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import it.uniroma3.siwpersonale.authentication.CustomOAuth2User;
import it.uniroma3.siwpersonale.model.Utente;
import it.uniroma3.siwpersonale.repository.UtenteRepository;

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
        System.out.println(">> CustomOAuth2UserService.loadUser() invocato - EMAIL: " + email);


        if (nome == null) {
            nome = (String) attributes.get("name"); // fallback
        }

        if (email == null || nome == null) {
            throw new IllegalArgumentException("Errore nel recupero dati utente da Google.");
        }

        Utente utente = utenteRepository.findByEmail(email);

        if (utente == null) {
            utente = new Utente();
            utente.setNome(nome);
            utente.setEmail(email);
            utente.setRuolo(Utente.Ruolo.UTENTE);
            utenteRepository.save(utente);
        } else {
            // opzionale: aggiorna nome se diverso
            if (!nome.equals(utente.getNome())) {
                utente.setNome(nome);
                utenteRepository.save(utente);
            }
        }

        return new CustomOAuth2User(oAuth2User, utente);
    }
}
