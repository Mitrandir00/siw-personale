package it.uniroma3.siwpersonale.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Recensione {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recensione_seq")
    @SequenceGenerator(name = "recensione_seq", sequenceName = "recensione_seq", allocationSize = 1)
    private Long id;
    private String titolo;
    private Integer voto;
    private String commento;
    @ManyToOne
    private Utente autore;
    @ManyToOne
    private Libro libro;

    public Recensione() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTitolo() {
        return this.titolo;
    }

    public void setVoto(Integer voto) {
    if (voto>=1 && voto<=5)
        this.voto = voto;
    }

    public Integer getVoto() {
        return this.voto;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public String getCommento() {
        return this.commento;
    }

    public void setAutore( Utente aut) {
        this.autore = aut;
    }

    public Utente getAutore() {
        return this.autore;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Libro getLibro() {
        return this.libro;
    }
}
