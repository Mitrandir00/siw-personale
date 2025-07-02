package it.uniroma3.siwpersonale.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Utente implements UserDetails {

    public static final Ruolo DEFAULT_ROLE = Utente.Ruolo.UTENTE;

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
    private List<Libro> libriLetti = new ArrayList<>();

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "autore")
    private List<Recensione> recensioni = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    public enum Ruolo {
        UTENTE, STAFF
    }

    public Utente() {
        this.ruolo = Ruolo.UTENTE;
    }

    // Getters & Setters
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

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public Ruolo getRuolo() {
        return this.ruolo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public List<Libro> getLibriLetti() {
        return this.libriLetti;
    }

    public void setLibroLetto(Libro libro) {
        this.libriLetti.add(libro);
    }

    public void setLibriLetti(List<Libro> libri) {
        this.libriLetti = libri;
    }

    public boolean hasLibro(Long id) {
        return this.libriLetti.stream().anyMatch(l -> l.getId().equals(id));
    }

    public void cancellaLibro(Long id) {
        this.libriLetti.removeIf(l -> l.getId().equals(id));
    }

    public void setRecensione(Recensione rec) {
        this.recensioni.add(rec);
    }

    public void setrecensioni(List<Recensione> rec) {
        this.recensioni = rec;
    }

    public List<Recensione> getRecensioni() {
        return this.recensioni;
    }

    public boolean haveRecensione(Long id) {
        return this.recensioni.stream().anyMatch(r -> r.getId().equals(id));
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return email; // Spring Security usa questo come identificativo
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

    // === UserDetails Implementation ===

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + this.ruolo.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
