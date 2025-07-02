package it.uniroma3.siwpersonale;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestHashPassword {
    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "1234";
        String hashedPassword = encoder.encode(rawPassword);
        System.out.println("Hash generato: " + hashedPassword);
    }
}
