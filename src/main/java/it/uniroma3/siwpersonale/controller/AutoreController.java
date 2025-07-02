package it.uniroma3.siwpersonale.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
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
import it.uniroma3.siwpersonale.service.UtenteService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AutoreController {

    @Autowired
    AutoreService autoreService;

    @Autowired
    UtenteService utenteService;

    @GetMapping("/autore/{id}")
    public String getAutore(@PathVariable Long id, Model model) {

        model.addAttribute("autore", autoreService.getAutoreById(id));
        return "Autore";
    }

    @GetMapping("/autori")
    public String autoriHome(@RequestParam(required = false) String query, Model model, Principal principal,
            HttpServletRequest request) {

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
    public String getAutoreNuovo(Model model, Principal principal) {
        model.addAttribute("autore", new Autore());
        return "AutoreForm";
    }


    @GetMapping("/autoreModifica/{autore}")
    public String getAutoreModifica(@PathVariable("autore") Long idAutore, Model model, Principal principal) {
        model.addAttribute("autore", autoreService.getAutoreById(idAutore));
        model.addAttribute("utente", utenteService.getUtenteByNome(principal.getName()));
        return "AutoreForm";
    }

    @PostMapping("/autore/elimina/{autore}")
    public String postCancella(@PathVariable("autore") Long idAutore, Principal principal, Model model) {
        autoreService.cancellaAutore(idAutore);
        model.addAttribute("autori", autoreService.getAllAutori());
        model.addAttribute("utente", utenteService.getUtenteByNome(principal.getName()));
        return "Autori";
    }

    @PostMapping("/autore/salva")
    public String salvaAutore(
            @ModelAttribute Autore autore,
            @RequestParam(required = false) MultipartFile immagine,
            Model model,
            Principal principal) {

        Utente utente = utenteService.getUtenteByNome(principal.getName());
        model.addAttribute("utente", utente);

        // Caricamento immagine se presente
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
                return "AutoreModifica"; // o AutoreForm se hai unificato anche il template
            }
        }

        // Gestione differenziata tra creazione e modifica
        if (autore.getId() == null) {
            autoreService.aggiungiAutore(autore);
        } else {
            Autore autoreEsistente = autoreService.getAutoreById(autore.getId());
            autoreEsistente.setId(autore.getId());
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
