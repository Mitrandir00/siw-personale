package it.uniroma3.siwpersonale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.siwpersonale.model.Libro;
import it.uniroma3.siwpersonale.model.Utente;
import it.uniroma3.siwpersonale.model.Recensione;
import it.uniroma3.siwpersonale.service.LibroService;
import it.uniroma3.siwpersonale.service.RecensioneService;
import it.uniroma3.siwpersonale.service.UtenteService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecensioneController {

    @Autowired
    RecensioneService recensioneService;
    @Autowired
    UtenteService utenteService;
    @Autowired
    LibroService libroService;

    @GetMapping("/recensione/modifica/{id}")
    public String modificaRecensione(@PathVariable Long id, Model model) {
        Recensione recensione = recensioneService.getRecensioneById(id);
        model.addAttribute("recensione", recensione);
        model.addAttribute("idLibro", recensione.getLibro().getId());
        return "Recensione"; // o il nome aggiornato del template
    }

    @PostMapping("/recensione/salva")
    public String salvaRecensione(@ModelAttribute Recensione recensione) {
        Recensione recensioneDaSalvare = recensione;
        recensioneService.addRecensione(recensioneDaSalvare);
        return "redirect:/libro/" + recensioneDaSalvare.getLibro().getId();
    }

    @GetMapping("/recensioneNuova/{libro}")
    public String getRecensioneNuova(@PathVariable Long libro, @AuthenticationPrincipal Utente authenticated,
            Model model) {
        model.addAttribute("libro", this.libroService.getLibroById(libro));
        model.addAttribute("utente", authenticated);
        return "RecensioneNuova";
    }

    @PostMapping("/recensioneNuova/{libro}")
    public String pubblicaRecensione(@PathVariable("libro") Long idLibro,
            @RequestParam String titolo,
            @RequestParam Integer voto,
            @RequestParam String commento) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utente utente = null;

        if (authentication != null && authentication.isAuthenticated() &&
                !(authentication.getPrincipal() instanceof String
                        && authentication.getPrincipal().equals("anonymousUser"))) {

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String usernameOrEmail = userDetails.getUsername();

            utente = utenteService.getUtenteByEmail(usernameOrEmail);

            if (utente == null) {
                utente = utenteService.getUtenteByNome(usernameOrEmail);
            }
        }

        if (utente == null) {
            // Gestisci il caso utente non trovato, es. redirect login o errore
            return "redirect:/login";
        }

        Recensione recensione = new Recensione();
        Libro libro = libroService.getLibroById(idLibro);

        recensione.setTitolo(titolo);
        recensione.setVoto(voto);
        recensione.setCommento(commento);
        recensione.setLibro(libro);
        recensione.setAutore(utente);

        utente.setRecensione(recensione);
        libro.addRecensione(recensione);

        recensioneService.addRecensione(recensione);
        utenteService.addUtente(utente);
        libroService.aggiungiLibro(libro, utente);

        return "redirect:/libro/" + libro.getId();
    }

    @PostMapping("/cancellaRecensione/{libro}/{recensione}")
    public String postCancellaRecensione(@PathVariable("libro") Long idLibro,
            @PathVariable("recensione") Long idRecensione,
            Model model) {
        this.recensioneService.cancellaRecensione(idRecensione);
        return "redirect:/libro/" + idLibro;
    }

    @PostMapping("/cancellaRecensione/{recensione}")
    public String postCancellaRecensioneDaUtente(@PathVariable("recensione") Long idRecensione,
            @AuthenticationPrincipal Utente authenticated,
            Model model) {
        this.recensioneService.cancellaRecensione(idRecensione);
        return "redirect:/utente/" + authenticated.getId();
    }
}
