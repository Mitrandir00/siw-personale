package it.uniroma3.siwpersonale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siwpersonale.model.Autore;


public interface AutoreRepository extends CrudRepository<Autore,Long> {
    List<Autore> findByNomeContainingIgnoreCase(String nome);
    List<Autore> findByCognomeContainingIgnoreCase(String cognome);
    List<Autore> findByNomeAndCognome(String nome, String cognome);
    List<Autore> findByNomeContainingIgnoreCaseOrCognomeContainingIgnoreCase(String nome, String cognome);
    @Query("SELECT a FROM Autore a WHERE " +
       "LOWER(CONCAT(a.nome, ' ', a.cognome)) LIKE LOWER(CONCAT('%', :query, '%')) " +
       "OR LOWER(CONCAT(a.cognome, ' ', a.nome)) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Autore> cercaPerNomeECognome(@Param("query") String query);
}
