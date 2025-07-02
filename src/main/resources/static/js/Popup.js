function apriPopup() {
  const popup = document.getElementById('popupRecensione');
  popup.style.display = 'flex';  // mostra il popup (usa flex per centrare)
}

function chiudiPopup() {
  const popup = document.getElementById('popupRecensione');
  popup.style.display = 'none';  // nasconde il popup
}

// Opzionale: chiudi il popup cliccando fuori dal contenuto
document.getElementById('popupRecensione').addEventListener('click', function(event) {
  if (event.target.id === 'popupRecensione') {
    chiudiPopup();
  }
});
