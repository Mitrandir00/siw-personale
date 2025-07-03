package it.uniroma3.siwpersonale.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.siwpersonale.model.Libro;
import it.uniroma3.siwpersonale.model.Utente;
import it.uniroma3.siwpersonale.repository.UtenteRepository;
import it.uniroma3.siwpersonale.service.CustomUserDetailsService;
import it.uniroma3.siwpersonale.service.UtenteService;
import it.uniroma3.siwpersonale.util.AutenticazioneHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

@Controller
public class UtenteController {

    @Autowired
    UtenteService utenteService;

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AutenticazioneHelper autenticazioneHelper;

    @GetMapping("/")
    public String inizia() {
        return "Login";
    }

    @GetMapping("/registrazione")
    public String mostraRegistrazione() {
        return "Registrazione";
    }

    @PostMapping("/registrazione")
    public String registrazione(@RequestParam String email,
            @RequestParam String nome,
            @RequestParam String password,
            @RequestParam String codiceAmministratore,
            @RequestParam String passwordBis,
            Model model,
            HttpServletRequest request) {

        if (!password.equals(passwordBis)) {
            model.addAttribute("errore", "La conferma Password non combacia con la Password inserita");
            return "Registrazione";
        }

        Utente utente = new Utente();
        utente.setEmail(email);
        utente.setNome(nome);
        String hashedPassword = passwordEncoder.encode(password);
        utente.setPassword(hashedPassword);

        if (this.utenteService.isAmministartore(codiceAmministratore)) {
            utente.setRuolo(Utente.Ruolo.STAFF);
        } else {
            utente.setRuolo(Utente.Ruolo.UTENTE);
        }

        this.utenteService.addUtente(utente);

        // Auto-login
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(utente.getEmail());
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        return "redirect:/libri";
    }

    @GetMapping("/utente")
    public String getUtente(Model model) {
        Utente utente = autenticazioneHelper.getUtenteAutenticato();

        if (utente == null)
            return "redirect:/login";

        List<Libro> libri = utente.getLibriLetti();
        libri.sort(Comparator.comparing(Libro::getTitolo, String.CASE_INSENSITIVE_ORDER));
        utente.setLibriLetti(libri);
        model.addAttribute("utente", utente);
        return "Utente";
    }

    @PostMapping("/impostazioni/{utente}")
    public String impostazioni(@RequestParam String email,
            @RequestParam String nome,
            @RequestParam String password,
            @RequestParam String codiceAmministratore,
            @RequestParam String passwordBis,
            Model model) {

        if (!password.equals(passwordBis)) {
            model.addAttribute("errore", "La conferma Password non combacia con la Password inserita");
            return "Registrazione";
        }

        Utente utente = new Utente();
        utente.setEmail(email);
        utente.setNome(nome);
        utente.setPassword(password);

        if (this.utenteService.isAmministartore(codiceAmministratore)) {
            utente.setRuolo(Utente.Ruolo.STAFF);
        } else {
            utente.setRuolo(Utente.Ruolo.UTENTE);
        }

        this.utenteService.addUtente(utente);
        return "redirect:/utente/" + utente.getId();
    }

    @GetMapping("/impostazioni")
    public String getImpostazioniProfilo(Model model) {
        Utente utente = autenticazioneHelper.getUtenteAutenticato();

        if (utente == null)
            return "redirect:/login";

        model.addAttribute("utente", utente);
        model.addAttribute("ruoli", Utente.Ruolo.values());
        return "Impostazioni";
    }

    @PostMapping("/utente/modifica")
    public String modificaProfilo(@ModelAttribute("utente") Utente modificato, Model model) {
        Utente utenteLoggato = autenticazioneHelper.getUtenteAutenticato();

        if (utenteLoggato == null)
            return "redirect:/login";

        utenteLoggato.setNome(modificato.getNome());
        utenteLoggato.setEmail(modificato.getEmail());
        utenteLoggato.setRuolo(modificato.getRuolo());

        if (!modificato.getPassword().equals(modificato.getPasswordBis())) {
            model.addAttribute("errore", "La conferma Password non combacia con la Password inserita");
            return "Impostazioni";
        }

        utenteLoggato.setPassword(modificato.getPassword());
        utenteLoggato.setPasswordBis(modificato.getPasswordBis());
        utenteLoggato.setrecensioni(modificato.getRecensioni());

        utenteService.addUtente(utenteLoggato);
        return "Utente";
    }

    @PostMapping("/utente/cancella")
    public String cancellaProfilo(HttpServletRequest request) {
        Utente utente = autenticazioneHelper.getUtenteAutenticato();

        if (utente == null) {
            return "redirect:/login";
        }

        // Rimuove o disassocia entità collegate se necessario
        utenteService.cancellaUtente(utente.getId());

        // Invalida la sessione corrente
        request.getSession().invalidate();

        return "redirect:/login?deleted";
    }

}
