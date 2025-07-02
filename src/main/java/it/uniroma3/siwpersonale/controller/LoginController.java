package it.uniroma3.siwpersonale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "Login"; // il nome del file HTML senza estensione
    }

    @GetMapping("/loginError")
    public String loginError(Model model) {
        model.addAttribute("errore", "Credenziali non valide");
        return "Login";
    }
}
