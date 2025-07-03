package it.uniroma3.siwpersonale.controller;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siwpersonale.model.Autore;
import it.uniroma3.siwpersonale.model.Utente;
import it.uniroma3.siwpersonale.service.AutoreService;
import it.uniroma3.siwpersonale.util.AutenticazioneHelper;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AutoreController {

    @Autowired
    private AutoreService autoreService;

    @Autowired
    private AutenticazioneHelper autenticazioneHelper;

    @GetMapping("/autore/{id}")
    public String getAutore(@PathVariable Long id, Model model) {
        model.addAttribute("autore", autoreService.getAutoreById(id));
        return "Autore";
    }

    @GetMapping("/autori")
    public String autoriHome(@RequestParam(required = false) String query, Model model, HttpServletRequest request) {
        List<Autore> autori;

        if (query != null && !query.isBlank()) {
            autori = autoreService.cercaAutoriPerNomeECognome(query);
        } else {
            autori = (List<Autore>) autoreService.getAllAutori();
            autori.sort(Comparator.comparing(Autore::getCognome, String.CASE_INSENSITIVE_ORDER));
        }

        model.addAttribute("requestURI", request.getRequestURI());
        model.addAttribute("autori", autori);
        model.addAttribute("query", query);
        return "Autori";
    }

    @GetMapping("/autoreNuovo")
    public String getAutoreNuovo(Model model) {
        model.addAttribute("autore", new Autore());
        return "AutoreForm";
    }

    @GetMapping("/autoreModifica/{autore}")
    public String getAutoreModifica(@PathVariable("autore") Long idAutore, Model model) {
        Utente utente = autenticazioneHelper.getUtenteAutenticato();
        model.addAttribute("utente", utente);
        model.addAttribute("autore", autoreService.getAutoreById(idAutore));
        return "AutoreForm";
    }

    @PostMapping("/autore/elimina/{autore}")
    public String postCancella(@PathVariable("autore") Long idAutore, Model model) {
        autoreService.cancellaAutore(idAutore);
        model.addAttribute("autori", autoreService.getAllAutori());
        model.addAttribute("utente", autenticazioneHelper.getUtenteAutenticato());
        return "Autori";
    }

    @PostMapping("/autore/salva")
    public String salvaAutore(
            @ModelAttribute Autore autore,
            @RequestParam(required = false) MultipartFile immagine,
            Model model) {

        Utente utente = autenticazioneHelper.getUtenteAutenticato();
        model.addAttribute("utente", utente);

        if (immagine != null && !immagine.isEmpty()) {
            String uploadDir = "C:\\Users\\182935\\Desktop\\giochino\\siwpersonale\\uploads\\images\\";
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            try {
                String nomeFile = UUID.randomUUID() + "_" + immagine.getOriginalFilename();
                immagine.transferTo(new File(uploadDir + nomeFile));
                autore.setUrlImage("images/" + nomeFile);
            } catch (IOException e) {
                model.addAttribute("errore", "Errore nel caricamento immagine: " + e.getMessage());
                model.addAttribute("autore", autore);
                return "AutoreForm";
            }
        }

        if (autore.getId() == null) {
            autoreService.aggiungiAutore(autore);
        } else {
            Autore autoreEsistente = autoreService.getAutoreById(autore.getId());
            autoreEsistente.setNome(autore.getNome());
            autoreEsistente.setCognome(autore.getCognome());
            autoreEsistente.setDataNascita(autore.getDataNascita());
            autoreEsistente.setDataMorte(autore.getDataMorte());
            autoreEsistente.setNazionalita(autore.getNazionalita());
            autoreEsistente.setBiografia(autore.getBiografia());

            if (autore.getUrlImage() != null) {
                autoreEsistente.setUrlImage(autore.getUrlImage());
            }

            autoreService.aggiungiAutore(autoreEsistente);
        }

        return "redirect:/autori";
    }
}
