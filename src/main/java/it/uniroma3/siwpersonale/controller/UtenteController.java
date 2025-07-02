package it.uniroma3.siwpersonale.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import it.uniroma3.siwpersonale.model.Libro;
import it.uniroma3.siwpersonale.model.Utente;
import it.uniroma3.siwpersonale.repository.UtenteRepository;
import it.uniroma3.siwpersonale.service.CustomUserDetailsService;
import it.uniroma3.siwpersonale.service.UtenteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);
        utente.setPassword(hashedPassword);
        if (this.utenteService.isAmministartore(codiceAmministratore)) {
            utente.setRuolo(Utente.Ruolo.STAFF);
        } else {
            utente.setRuolo(Utente.Ruolo.UTENTE);
        }

        this.utenteService.addUtente(utente);

        // Auto-login qui:
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(utente.getEmail());
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getPassword());

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);
        // 🔑 Salva il contesto nella sessione
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        return "redirect:/libri";
    }

    @GetMapping("/utente")
    public String getUtente(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utente utente = null;

        if (authentication != null && authentication.isAuthenticated() &&
                !(authentication.getPrincipal() instanceof String
                        && authentication.getPrincipal().equals("anonymousUser"))) {

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String usernameOrEmail = userDetails.getUsername();

            // Provo prima con email
            utente = utenteService.getUtenteByEmail(usernameOrEmail);

            // Se null, provo con nome
            if (utente == null) {
                utente = utenteService.getUtenteByNome(usernameOrEmail);
            }
        }
        List<Libro> libri = utente.getLibriLetti();
        libri.sort(Comparator.comparing(Libro::getTitolo, String.CASE_INSENSITIVE_ORDER));
        utente.setLibriLetti(libri);
        model.addAttribute("utente", utente);
        return "Utente";
    }

    @GetMapping("/impostazioni/{utente}")
    public String getImpostazioni(@PathVariable("utente") Long id, Model model) {
        model.addAttribute("utente", this.utenteService.getUtenteById(id));
        return "Impostazioni";
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
        if (this.utenteService.isAmministartore(codiceAmministratore) == true) {
            utente.setRuolo(Utente.Ruolo.STAFF);
        } else {
            utente.setRuolo(Utente.Ruolo.UTENTE);
        }
        this.utenteService.addUtente(utente);
        return "redirect:/utente/" + utente.getId();
    }

    @GetMapping("/impostazioni")
    public String getImpostazioniProfilo(Model model, @AuthenticationPrincipal Utente utente) {
        model.addAttribute("utente", utente);
        model.addAttribute("ruoli", Utente.Ruolo.values());
        return "Impostazioni";
    }

    @PostMapping("/utente/modifica")
    public String modificaProfilo(@ModelAttribute("utente") Utente modificato,
            @AuthenticationPrincipal Utente utenteLoggato,
            Model model) {

        utenteLoggato.setNome(modificato.getNome());
        utenteLoggato.setEmail(modificato.getEmail());
        utenteLoggato.setRuolo(modificato.getRuolo());

        if (modificato.getPassword() != null && !modificato.getPassword().isBlank()) {
            if (modificato.getPassword().equals(modificato.getPasswordBis())) {
                utenteLoggato.setPassword(passwordEncoder.encode(modificato.getPassword()));
            } else {
                model.addAttribute("errore", "Le password non coincidono");
                model.addAttribute("utente", utenteLoggato);
                model.addAttribute("ruoli", Utente.Ruolo.values());
                return "Impostazioni";
            }
        }

        utenteService.addUtente(utenteLoggato);
        return "redirect:/impostazioni?successo";
    }

    @PostMapping("/utente/cancella")
    public String cancellaProfilo(@AuthenticationPrincipal Utente utente, HttpServletRequest request) {
        utenteService.cancellaUtente(utente.getId());
        request.getSession().invalidate();
        return "redirect:/login?deleted";
    }

}
