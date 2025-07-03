package it.uniroma3.siwpersonale.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siwpersonale.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long> {
    Utente findByNome(String nome);

    Utente findByNomeAndPassword(String nome, String password);

    Utente findByEmail(String email);

    @Query("SELECT u FROM Utente u LEFT JOIN FETCH u.libriLetti WHERE u.email = :email")
    public Utente findUtenteWithLibriLetti(@Param("email") String email);

    @Query("SELECT u FROM Utente u LEFT JOIN FETCH u.recensioni WHERE u.email = :email")
    public Utente findUtenteWithRecensioni(@Param("email") String email);

    @Query("SELECT u FROM Utente u LEFT JOIN FETCH u.libriLetti WHERE u.nome = :nome")
    Utente findUtenteWithLibriLettiByNome(@Param("nome") String nome);

    @Query("SELECT u FROM Utente u LEFT JOIN FETCH u.recensioni WHERE u.nome = :nome")
    Utente findUtenteWithRecensioniByNome(@Param("nome") String nome);

}
