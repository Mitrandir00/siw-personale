
# SIW Personale - Casi d'Uso Implementati

Questo progetto Spring Boot gestisce opere, autori, recensioni e utenti con autenticazione.  
Di seguito sono descritti almeno 4 **casi d'uso** richiesti dal progetto.

---

## ✅ Inserimento (Create)
### ➤ Aggiunta di una nuova recensione
- **Attore:** Utente autenticato
- **Descrizione:** L'utente può inserire una nuova recensione per un libro esistente, specificando titolo, commento e voto.
- **Metodo HTTP:** `POST`
- **Rotta:** `/recensione`
- **Entità coinvolte:** `Recensione`, `Utente`, `Libro`

---

## 🔄 Aggiornamento (Update)
### ➤ Modifica dei dati di un libro
- **Attore:** Amministratore
- **Descrizione:** L’amministratore può modificare titolo, trama e data di pubblicazione di un libro.
- **Metodo HTTP:** `POST`
- **Rotta:** `/libro/edit/{id}`
- **Entità coinvolte:** `Libro`

---

## ❌ Cancellazione (Delete)
### ➤ Eliminazione di una recensione
- **Attore:** Utente autenticato (autore della recensione) o amministratore
- **Descrizione:** È possibile eliminare una recensione associata a un libro.
- **Metodo HTTP:** `GET` (reindirizzamento)
- **Rotta:** `/recensione/delete/{id}`
- **Entità coinvolte:** `Recensione`

---

## 📖 Lettura (Read)
### ➤ Visualizzazione della lista dei libri
- **Attore:** Utente anonimo o autenticato
- **Descrizione:** Visualizzazione dell’elenco di tutti i libri disponibili con relative informazioni.
- **Metodo HTTP:** `GET`
- **Rotta:** `/libri`
- **Entità coinvolte:** `Libro`

### ➤ Dettagli di un autore
- **Attore:** Utente
- **Descrizione:** L’utente può visualizzare la pagina dettagliata di un autore, incluse le opere collegate.
- **Metodo HTTP:** `GET`
- **Rotta:** `/autore/{id}`
- **Entità coinvolte:** `Autore`, `Libro`

---

## ℹ️ Note
- L'autenticazione e la registrazione dell'utente **non sono considerati** casi d'uso secondo il regolamento.
- Tutti i form sono protetti da CSRF token.
- I dati iniziali vengono caricati da `import.sql`.
