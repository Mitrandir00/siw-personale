package it.uniroma3.siwpersonale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siwpersonale.model.Utente;
import it.uniroma3.siwpersonale.repository.UtenteRepository;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Utente getUtenteById(Long id) {
        return utenteRepository.findById(id).get();
    }

     public Utente getUtenteByEmail(String Email) {
        return utenteRepository.findByEmail(Email);
    }

    public Iterable<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    public Utente getUtenteByNomeUtenteEPassword(String nome, String password) {
        return this.utenteRepository.findByNomeAndPassword(nome, password);
    }

    public Utente getUtenteByNome(String nome) {
        return this.utenteRepository.findByNome(nome);
    }

    public boolean isAdmin(Utente utente) {
        return utente != null && utente.getRuolo().equals(Utente.Ruolo.STAFF);
    }

    // Cancella un utente (solo admin)
    public void cancellaUtente(Long id, Utente utenteCheRichiede) {
        utenteRepository.deleteById(id);
    }

    public boolean isAmministartore (String codice) {
        return codice.equals("Panino_con_pomodori");
    }

    public void addUtente (Utente utente) {
        this.utenteRepository.save(utente);
    }
}
