package it.uniroma3.siwpersonale.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Libro implements Comparable<Libro> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titolo;
    private LocalDate data;

    @Column(length = 2000) // limite a 2000 caratteri per la trama (puoi modificare)
    private String trama;

    @ElementCollection
    @CollectionTable(name = "libro_url_image", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "url_image")
    private List<String> urlImage = new ArrayList<>();

    @ManyToMany
    private List<Autore> autori;

    @OneToMany(mappedBy = "libro", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
    private List<Recensione> recensioni = new ArrayList<>();

    public Libro() {
    }

    @Override
    public int compareTo(Libro altro) {
        return this.titolo.compareToIgnoreCase(altro.getTitolo());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Libro libro = (Libro) obj;
        return this.data.equals(libro.data) && this.titolo.equals(libro.titolo);
    }

    @Override
    public int hashCode() {
        return this.titolo.hashCode() + this.data.hashCode();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return this.titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTrama() {
        return this.trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public List<Autore> getAutori() {
        return this.autori;
    }

    public void setAutori(List<Autore> autori) {
        this.autori = autori;
    }

    public List<String> getUrlImage() {
        return this.urlImage;
    }

    public void setUrlImage(List<String> urlImage) {
        this.urlImage = urlImage;
    }

    public List<Recensione> getRecensioni() {
        return this.recensioni;
    }

    public void addRecensione(Recensione recensione) {
        this.recensioni.add(recensione);
        recensione.setLibro(this);
    }
}
