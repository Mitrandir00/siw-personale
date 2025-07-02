package it.uniroma3.siwpersonale.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siwpersonale.model.Autore;
import it.uniroma3.siwpersonale.model.Libro;
import it.uniroma3.siwpersonale.repository.AutoreRepository;
import it.uniroma3.siwpersonale.repository.LibroRepository;

@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private LibroService libroService;

    public Autore getAutoreById(Long id) {
        return autoreRepository.findById(id).orElse(null);
    }

    public List<Autore> cercaAutoriPerNome(String nome) {
        return autoreRepository.findByNomeContainingIgnoreCase(nome);
    }
    
    public List<Autore> cercaAutoriPerCognome(String cognome) {
        return autoreRepository.findByCognomeContainingIgnoreCase(cognome);
    }

    public List<Autore> cercaAutoriPerNomeECognome(String query){
        return autoreRepository.cercaPerNomeECognome(query);
    }

    public Iterable<Autore> getAllAutori() {
        return autoreRepository.findAll();
    }

    // Aggiungi un autore (solo admin)
    public Autore aggiungiAutore(Autore autore) {
        return autoreRepository.save(autore);
    }

    // Modifica un autore (solo admin)
    public Autore modificaAutore(Autore autore) {
        return autoreRepository.save(autore);
    }

       @Transactional
        public boolean cancellaAutore(Long id) {
            Optional<Autore> autoreOpt = autoreRepository.findById(id);
            if (autoreOpt.isEmpty()) {
                return false; // autore non trovato
            }

            Autore autore = autoreOpt.get();

            // Trova tutti i libri che hanno questo autore
            List<Libro> libri = libroRepository.findByAutoriContaining(autore);
            for(Libro libro : libri) {
                this.libroService.cancellaLibro(libro.getId());
            }

            // Ora cancella l'autore
            autoreRepository.delete(autore);

            return true;
        }

}
