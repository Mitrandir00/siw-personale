package it.uniroma3.siwpersonale.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siwpersonale.model.Utente;


public interface UtenteRepository extends CrudRepository<Utente,Long> {
    Utente findByNome(String nome);
    Utente findByNomeAndPassword(String nome, String password);
    Utente findByEmail(String email);
}
