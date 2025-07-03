package it.uniroma3.siwpersonale.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import it.uniroma3.siwpersonale.service.CustomOAuth2UserService;
import it.uniroma3.siwpersonale.service.CustomUserDetailsService;

@Configuration
public class AuthConfiguration {

        @Autowired
        private CustomUserDetailsService customUserDetailsService;

        private static final String ROLE_STAFF = "ROLE_STAFF";
        private static final String ROLE_UTENTE = "ROLE_UTENTE";

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authz -> authz
                                                .requestMatchers(HttpMethod.GET,
                                                                "/", "/index", "/login", "/autori", "/autore/**",
                                                                "/libri", "/libro/**", "/recensioneNuovo/**",
                                                                "/js/**", "/css/**", "/images/**", "/uploads/images/**",
                                                                "/error", "/registrazione", "/utente/**",
                                                                "/impostazioni", "/impostazioni/**")
                                                .permitAll()

                                                .requestMatchers(HttpMethod.HEAD, "/uploads/images/**").permitAll()

                                                .requestMatchers(HttpMethod.POST,
                                                                "/login", "/registrazione", "/recensione/salva",
                                                                "/utente/modifica")
                                                .permitAll()

                                                .requestMatchers(HttpMethod.POST, "/cancellaRecensione/**")
                                                .hasAuthority(ROLE_STAFF)

                                                .requestMatchers("/autoreNuovo", "/autoreModifica/**",
                                                                "/libroNuovo", "/libroModifica/**")
                                                .hasAuthority(ROLE_STAFF)

                                                .anyRequest().authenticated())

                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .usernameParameter("email")
                                                .passwordParameter("password")
                                                .defaultSuccessUrl("/libri", true)
                                                .failureUrl("/loginError")
                                                .permitAll())

                                .oauth2Login(oauth2 -> oauth2
                                                .loginPage("/login")
                                                .userInfoEndpoint(userInfo -> userInfo
                                                                .userService(customOAuth2UserServiceBean()))
                                                .successHandler((request, response, authentication) -> {
                                                        response.sendRedirect("/libri");
                                                }))

                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login?logout")
                                                .invalidateHttpSession(true)
                                                .deleteCookies("JSESSIONID")
                                                .permitAll());

                return http.build();
        }

        @Bean
        public CustomOAuth2UserService customOAuth2UserServiceBean() {
                return new CustomOAuth2UserService();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
                return authConfig.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
