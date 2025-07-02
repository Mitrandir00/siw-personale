package it.uniroma3.siwpersonale.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.siwpersonale.model.Authenticated;
import it.uniroma3.siwpersonale.model.Autore;
import it.uniroma3.siwpersonale.model.Libro;
import it.uniroma3.siwpersonale.model.Recensione;
import it.uniroma3.siwpersonale.model.Utente;
import it.uniroma3.siwpersonale.repository.AutoreRepository;
import it.uniroma3.siwpersonale.repository.LibroRepository;
import it.uniroma3.siwpersonale.service.LibroService;
import it.uniroma3.siwpersonale.service.UtenteService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class LibroController {

    @Autowired
    LibroService libroService;
    @Autowired
    LibroRepository libroRepository;
    @Autowired
    UtenteService utenteService;
    @Autowired
    AutoreRepository autoreRepository;
    @Autowired
    Authenticated authenticated;

    @GetMapping("/libri")
    public String home(@RequestParam(required = false) String query,
            Model model, HttpServletRequest request) {

        List<Libro> libri;
        if (query != null && !query.isBlank()) {
            libri = libroService.cercaLibriPerTitolo(query);
        } else {
            libri = (List<Libro>) libroService.getAllLibri();
            libri.sort(Comparator.comparing(Libro::getTitolo, String.CASE_INSENSITIVE_ORDER));
        }
        model.addAttribute("requestURI", request.getRequestURI());
        model.addAttribute("libri", libri);
        model.addAttribute("query", query);
        return "Libri";
    }

    @GetMapping("/libro/{id}")
    public String getLibro(@PathVariable("id") Long idLibro, Model model) {

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

        Libro libro = libroService.getLibroById(idLibro);

        boolean utenteHaLibro = false;
        boolean utenteHaRecensito = false;

        if (utente != null) {
            utenteHaLibro = utente.getLibriLetti().stream()
                    .anyMatch(l -> l.getId().equals(libro.getId()));

            if (utenteHaLibro) {
                for (Recensione recensione : libro.getRecensioni()) {
                    if (recensione.getAutore().getId().equals(utente.getId())) {
                        utenteHaRecensito = true;
                        model.addAttribute("voto", recensione.getVoto());
                        model.addAttribute("idRecensione", recensione.getId());
                        break;
                    }
                }
            }
        }
        model.addAttribute("libro", libro);
        model.addAttribute("utenteHaLibro", utenteHaLibro);
        model.addAttribute("utenteHaRecensito", utenteHaRecensito);

        return "Libro"; // il nome della tua view
    }

    @PostMapping("/libro/{id}")
    public String aggiungiLibroLetto(@PathVariable Long id) {
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

        if (utente == null) {
            // Puoi gestire il caso utente non trovato (ad esempio redirect a login)
            return "redirect:/login";
        }

        Libro libro = libroService.getLibroById(id);

        if (utente.hasLibro(id)) {
            utente.cancellaLibro(id);
        } else {
            utente.setLibroLetto(libro);
        }

        utenteService.addUtente(utente);

        return "redirect:/libro/" + id;
    }

    @GetMapping("/libroNuovo")
    public String getLibroNuovo(Model model, @AuthenticationPrincipal Utente utente) {
        model.addAttribute("utente", utente);
        model.addAttribute("libro", new Libro());
        model.addAttribute("autori", autoreRepository.findAll());
        return "LibroForm";
    }

    @GetMapping("/libroModifica/{libro}")
    public String getLibroModifica(@PathVariable("libro") Long idLibro,
            Model model,
            @AuthenticationPrincipal Utente utente) {
        Libro libro = this.libroService.getLibroById(idLibro);
        StringJoiner joiner = new StringJoiner(", ");
        for (Autore autore : libro.getAutori()) {
            joiner.add(autore.getNome() + " " + autore.getCognome());
        }

        model.addAttribute("libro", libro);
        model.addAttribute("utente", utente);
        model.addAttribute("autoriString", joiner.toString());
        model.addAttribute("autori", autoreRepository.findAll());
        return "LibroForm";
    }

    @PostMapping("/libroCancella/{libro}")
    public String postCancella(@PathVariable("libro") Long idLibro,
            Model model,
            @AuthenticationPrincipal Utente utente) {
        this.libroService.cancellaLibro(idLibro);
        model.addAttribute("libri", this.libroService.getAllLibri());
        model.addAttribute("utente", utente);
        return "Libri";
    }

    @PostMapping("/libro/salva")
    public String salvaLibro(
            @ModelAttribute Libro libro,
            @RequestParam("autoriIds") List<Long> autoriIds,
            @RequestParam(required = false) MultipartFile[] immagini,
            Model model,
            @AuthenticationPrincipal Utente utente) {

        model.addAttribute("utente", utente);

        try {
            Libro libroDaSalvare;

            // Se id presente, recupera esistente e aggiorna
            if (libro.getId() != null) {
                libroDaSalvare = libroService.getLibroById(libro.getId());
                libroDaSalvare.setTitolo(libro.getTitolo());
                libroDaSalvare.setData(libro.getData());
                libroDaSalvare.setTrama(libro.getTrama());
            } else {
                libroDaSalvare = new Libro();
                libroDaSalvare.setTitolo(libro.getTitolo());
                libroDaSalvare.setData(libro.getData());
                libroDaSalvare.setTrama(libro.getTrama());
            }

            // Gestione immagini
            List<String> immaginiSalvate = (libroDaSalvare.getUrlImage() != null)
                    ? new ArrayList<>(libroDaSalvare.getUrlImage())
                    : new ArrayList<>();

            String uploadDir = "C:\\Users\\182935\\Desktop\\giochino\\siwpersonale\\uploads\\images\\";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            if (immagini != null) {
                for (MultipartFile immagine : immagini) {
                    if (immagine != null && !immagine.isEmpty()) {
                        String nomeFile = UUID.randomUUID() + "_" + immagine.getOriginalFilename();
                        immagine.transferTo(new File(uploadDir + nomeFile));
                        immaginiSalvate.add("images/" + nomeFile);
                    }
                }
            }

            libroDaSalvare.setUrlImage(immaginiSalvate);

            // Recupera autori dal DB
            List<Autore> autori = (List<Autore>) autoreRepository.findAllById(autoriIds);
            if (autori.isEmpty()) {
                model.addAttribute("errore", "Devi selezionare almeno un autore.");
                model.addAttribute("libro", libroDaSalvare);
                model.addAttribute("autori", autoreRepository.findAll());
                return "LibroForm";
            }

            libroDaSalvare.setAutori(autori);

            // Salvataggio
            libroRepository.save(libroDaSalvare);

            return "redirect:/libri";

        } catch (Exception e) {
            model.addAttribute("errore", "Errore generico: " + e.getMessage());
            model.addAttribute("libro", libro);
            model.addAttribute("autori", autoreRepository.findAll());
            return "LibroForm";
        }
    }

}
