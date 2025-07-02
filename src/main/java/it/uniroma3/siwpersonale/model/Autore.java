package it.uniroma3.siwpersonale.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Autore implements Comparable<Autore> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private LocalDate dataMorte;
    private String nazionalita;
    private String urlImage;
    private String biografia;

    @ManyToMany(mappedBy = "autori", fetch = FetchType.EAGER)
    private List<Libro> libri = new ArrayList<>();

    public Autore() {
    }

    @Override
    public int compareTo(Autore altro) {
        return this.cognome.compareToIgnoreCase(altro.getCognome());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Autore aut = (Autore) obj;
        return this.nome.equals(aut.nome) && this.cognome.equals(aut.cognome);
    }

    @Override
    public int hashCode() {
        return this.nome.hashCode() + this.cognome.hashCode();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return this.cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return this.dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public LocalDate getDataMorte() {
        return this.dataMorte;
    }

    public void setDataMorte(LocalDate dataMorte) {
        this.dataMorte = dataMorte;
    }

    public String getNazionalita() {
        return this.nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public String getUrlImage() {
        return this.urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getBiografia() {
        return this.biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public List<Libro> getLibri() {
        return libri;
    }

    public void setLibri(List<Libro> libri) {
        this.libri = libri;
    }

    public void setLibro(Libro libro) {
        this.libri.add(libro);
    }
}
