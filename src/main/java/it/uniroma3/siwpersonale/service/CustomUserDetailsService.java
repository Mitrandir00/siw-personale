package it.uniroma3.siwpersonale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // Prima prova con l'email
        String sqlByEmail = "SELECT email, password, ruolo FROM utente WHERE email = ?";
        List<UserDetails> usersByEmail = jdbcTemplate.query(
                sqlByEmail,
                ps -> ps.setString(1, usernameOrEmail),
                (rs, rowNum) -> User.withUsername(rs.getString("email"))
                        .password(rs.getString("password"))
                        .roles(rs.getString("ruolo"))
                        .build());

        if (!usersByEmail.isEmpty()) {
            return usersByEmail.get(0);
        }

        // Poi prova con il nome (non username)
        String sqlByNome = "SELECT nome, password, ruolo FROM utente WHERE nome = ?";
        List<UserDetails> usersByNome = jdbcTemplate.query(
                sqlByNome,
                ps -> ps.setString(1, usernameOrEmail),
                (rs, rowNum) -> User.withUsername(rs.getString("nome"))
                        .password(rs.getString("password"))
                        .roles(rs.getString("ruolo"))
                        .build());

        if (!usersByNome.isEmpty()) {
            return usersByNome.get(0);
        }

        // Nessun utente trovato
        throw new UsernameNotFoundException("Utente non trovato con email o nome: " + usernameOrEmail);
    }
}
