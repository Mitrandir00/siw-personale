package it.uniroma3.siwpersonale.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @Transient
	private String passwordBis;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Libro> libriLetti = new ArrayList<Libro>();
    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "autore")
    private List<Recensione> recensioni = new ArrayList<Recensione>();
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    public enum Ruolo {
        UTENTE, STAFF
    }

    public Utente() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setRuolo(Ruolo f) {
        this.ruolo = f;
    }

    public Ruolo getRuolo() {
        return this.ruolo;
    }

    public void setPassword(String psw) {
        this.password = psw;
    }

    public String getPassword() {
        return this.password;
    }

    public List<Libro> getLibriLetti() {
        return this.libriLetti;
    }

    public void setLibroLetto(Libro libro) {
        this.libriLetti.add(libro);
    }

    public void setLibriLetti(List<Libro> libro) {
        this.libriLetti = libro;
    }

    public boolean hasLibro(Long id) {
        for (Libro libro : this.libriLetti) {
            if (libro.getId() == id)
                return true;
        }
        return false;
    }

    public void cancellaLibro(Long id) {
        libriLetti.removeIf(libro -> libro.getId().equals(id));
    }

    public void setRecensione(Recensione rec) {
        this.recensioni.add(rec);
    }

    public List<Recensione> getRecensioni() {
        return this.recensioni;
    }

    public boolean haveRecensione(Long id) {
        for (Recensione recensione : this.recensioni) {
            if (recensione.getId() == id)
                return true;
        }
        return false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordBis() {
		return passwordBis;
	}

	public void setPasswordBis(String passwordBis) {
		this.passwordBis = passwordBis;
	}

}
