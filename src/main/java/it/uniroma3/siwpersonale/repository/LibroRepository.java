package it.uniroma3.siwpersonale.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siwpersonale.model.Autore;
import it.uniroma3.siwpersonale.model.Libro;


public interface LibroRepository extends CrudRepository<Libro,Long> {
    @Query("SELECT l FROM Libro l LEFT JOIN FETCH l.recensioni WHERE l.id = :id")
    List<Libro> findByIdWithRecensioni(@Param("id") Long id);
    List<Libro> findByTitoloContainingIgnoreCase(String titolo);
    List<Libro> findByAutoriContaining(Autore autore);
}
