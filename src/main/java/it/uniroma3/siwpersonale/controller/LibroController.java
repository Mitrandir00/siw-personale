package it.uniroma3.siwpersonale.controller;

import java.io.File;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import it.uniroma3.siwpersonale.model.*;
import it.uniroma3.siwpersonale.repository.AutoreRepository;
import it.uniroma3.siwpersonale.repository.LibroRepository;
import it.uniroma3.siwpersonale.service.LibroService;
import it.uniroma3.siwpersonale.service.UtenteService;
import it.uniroma3.siwpersonale.util.AutenticazioneHelper;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private AutoreRepository autoreRepository;

    @Autowired
    private AutenticazioneHelper autenticazioneHelper;

    @GetMapping("/libri")
    public String home(@RequestParam(required = false) String query, Model model, HttpServletRequest request) {
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
        Utente utente = autenticazioneHelper.getUtenteAutenticato();
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
        model.addAttribute("utente", utente);
        model.addAttribute("libro", libro);
        model.addAttribute("utenteHaLibro", utenteHaLibro);
        model.addAttribute("utenteHaRecensito", utenteHaRecensito);

        return "Libro";
    }

    @PostMapping("/libro/{id}")
    public String aggiungiLibroLetto(@PathVariable Long id, Model model) {
        Utente utente = autenticazioneHelper.getUtenteAutenticato();
        if (utente == null)
            return "redirect:/login";

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
    public String getLibroNuovo(Model model) {
        Utente utente = autenticazioneHelper.getUtenteAutenticato();
        model.addAttribute("utente", utente);
        model.addAttribute("libro", new Libro());
        model.addAttribute("autori", autoreRepository.findAll());
        return "LibroForm";
    }

    @GetMapping("/libroModifica/{libro}")
    public String getLibroModifica(@PathVariable("libro") Long idLibro, Model model) {
        Utente utente = autenticazioneHelper.getUtenteAutenticato();
        Libro libro = libroService.getLibroById(idLibro);

        StringJoiner joiner = new StringJoiner(", ");
        for (Autore autore : libro.getAutori()) {
            joiner.add(autore.getNome() + " " + autore.getCognome());
        }

        model.addAttribute("utente", utente);
        model.addAttribute("libro", libro);
        model.addAttribute("autoriString", joiner.toString());
        model.addAttribute("autori", autoreRepository.findAll());
        return "LibroForm";
    }

    @PostMapping("/libroCancella/{libro}")
    public String postCancella(@PathVariable("libro") Long idLibro, Model model) {
        Utente utente = autenticazioneHelper.getUtenteAutenticato();
        libroService.cancellaLibro(idLibro);
        model.addAttribute("libri", libroService.getAllLibri());
        model.addAttribute("utente", utente);
        return "Libri";
    }

    @PostMapping("/libro/salva")
    public String salvaLibro(
            @ModelAttribute Libro libro,
            @RequestParam("autoriIds") List<Long> autoriIds,
            @RequestParam(required = false) MultipartFile[] immagini,
            Model model) {

        Utente utente = autenticazioneHelper.getUtenteAutenticato();
        model.addAttribute("utente", utente);

        try {
            Libro libroDaSalvare;

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

            List<Autore> autori = (List<Autore>) autoreRepository.findAllById(autoriIds);
            if (autori.isEmpty()) {
                model.addAttribute("errore", "Devi selezionare almeno un autore.");
                model.addAttribute("libro", libroDaSalvare);
                model.addAttribute("autori", autoreRepository.findAll());
                return "LibroForm";
            }

            libroDaSalvare.setAutori(autori);
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
