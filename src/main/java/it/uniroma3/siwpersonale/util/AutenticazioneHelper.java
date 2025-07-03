package it.uniroma3.siwpersonale.util;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;

import it.uniroma3.siwpersonale.model.Utente;
import it.uniroma3.siwpersonale.model.Utente.Ruolo;
import it.uniroma3.siwpersonale.service.UtenteService;

@Component
public class AutenticazioneHelper {

    @Autowired
    private UtenteService utenteService;

    public Utente getUtenteAutenticato() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()
                && !(auth.getPrincipal() instanceof String && auth.getPrincipal().equals("anonymousUser"))) {

            String identificativo = null;
            String nome = null;

            if (auth.getPrincipal() instanceof UserDetails userDetails) {
                identificativo = userDetails.getUsername(); // può essere email o nome
                nome = identificativo;
                System.out.println("castoro " + identificativo);
            } else if (auth.getPrincipal() instanceof DefaultOidcUser oidcUser) {
                identificativo = oidcUser.getEmail();
                nome = (String) oidcUser.getAttributes().get("name");
                System.out.println("porcellino " + identificativo);
            }

            if (identificativo != null) {
                // Tenta prima per email, poi per nome
                Utente utente = utenteService.getUtenteCompletoByEmail(identificativo);
                if (utente == null) {
                    utente = utenteService.getUtenteCompletoByNome(identificativo);
                }

                // Se ancora non esiste, crealo (tipicamente per Google)
                if (utente == null) {
                    utente = new Utente();
                    utente.setEmail(identificativo);
                    utente.setNome(nome != null ? nome : identificativo);
                    utente.setRuolo(Ruolo.UTENTE);
                    utente.setLibriLetti(new ArrayList<>());
                    utente.setrecensioni(new ArrayList<>());
                    utenteService.addUtente(utente);
                }

                System.out.println("Utente: " + utente.getNome());
                System.out.println("Libri letti: " + utente.getLibriLetti().size());
                System.out.println("Recensioni: " + utente.getRecensioni().size());
                return utente;
            }
        }

        return null;
    }
}
