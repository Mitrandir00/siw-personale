package it.uniroma3.siwpersonale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwpersonale.model.Libro;
import it.uniroma3.siwpersonale.model.Recensione;
import it.uniroma3.siwpersonale.model.Utente;
import it.uniroma3.siwpersonale.repository.LibroRepository;
import it.uniroma3.siwpersonale.repository.RecensioneRepository;
import it.uniroma3.siwpersonale.repository.UtenteRepository;

@Service
public class RecensioneService {


    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private RecensioneRepository recensioneRepository;
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private UtenteRepository utenteRepository;

    public Recensione getRecensioneById(Long id) {
        return recensioneRepository.findById(id).get();
    }

    public Iterable<Recensione> getAllRecencioni() {
        return recensioneRepository.findAll();
    }

    public Recensione addRecensione(Recensione recensione) {
        return recensioneRepository.save(recensione);
    }

    public void cancellaRecensione(Long idRecensione) {
        Recensione recensione = recensioneRepository.findById(idRecensione).get();

        // 1️⃣ Rimuovi dai genitori (Libro e Utente) per evitare problemi di FK
        Libro libro = recensione.getLibro();
        Utente autore = recensione.getAutore();

        if (libro != null) {
            libro.getRecensioni().remove(recensione);
            libroRepository.save(libro);
        }
        if (autore != null) {
            autore.getRecensioni().remove(recensione);
            utenteRepository.save(autore);
        }

        // 2️⃣ Cancella la recensione
        recensioneRepository.delete(recensione);  
    }
}