package it.uniroma3.siwpersonale.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwpersonale.model.Libro;
import it.uniroma3.siwpersonale.model.Utente;
import it.uniroma3.siwpersonale.repository.LibroRepository;
import it.uniroma3.siwpersonale.repository.UtenteRepository;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private UtenteRepository utenteRepository;

    // Ottieni libro per id
   public Libro getLibroById(Long id) {
    Libro libro = libroRepository.findById(id).orElse(null);
    if(libro != null) {
        libro.getAutori().size();       // forza caricamento autori
        libro.getRecensioni().size();   // forza caricamento recensioni
    }
    return libro;  // restituisci direttamente il libro o null
}

    public List<Libro> cercaLibriPerTitolo(String titolo) {
        return libroRepository.findByTitoloContainingIgnoreCase(titolo);
    }

    // Ottieni tutti i libri
    public Iterable<Libro> getAllLibri() {
        return libroRepository.findAll();
    }

    // Aggiungi un libro (solo admin)
    public Libro aggiungiLibro(Libro libro, Utente utenteCheRichiede) {
        if (utenteService.getUtenteById(utenteCheRichiede.getId())==null) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        return libroRepository.save(libro);
    }

    // Modifica un libro (solo admin)
    public Libro modificaLibro(Libro libro, Utente utenteCheRichiede) {
        if (!utenteService.isAdmin(utenteCheRichiede)) {
            throw new RuntimeException("Accesso negato: non sei amministratore");
        }
        return libroRepository.save(libro);
    }

    
    public void cancellaLibro(Long libroId) {
    Libro libro = libroRepository.findById(libroId).orElseThrow();

    // Trova tutti gli utenti
    List<Utente> tuttiGliUtenti = (List<Utente>) utenteRepository.findAll();

    // Rimuovi il libro da tutti gli utenti che lo hanno in "libriLetti"
    for (Utente utente : tuttiGliUtenti) {
        if (utente.hasLibro(libroId)) {
            utente.cancellaLibro(libroId);
            utenteRepository.save(utente);
        }
    }

    // Solo dopo averlo rimosso da tutti, cancella il libro
    libroRepository.delete(libro);
}


}
