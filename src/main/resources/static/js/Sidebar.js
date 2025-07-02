function toggleSidebar() {
    document.getElementById("sidebar").classList.toggle("show");
}

// Chiude la sidebar se clicchi fuori
document.addEventListener("click", function(event) {
    const sidebar = document.getElementById("sidebar");
    const toggleButton = document.getElementById("menu-toggle"); // Assicurati che il bottone abbia questo ID

    const isClickInsideSidebar = sidebar.contains(event.target);
    const isClickOnToggleButton = toggleButton && toggleButton.contains(event.target);

    if (sidebar.classList.contains("show") && !isClickInsideSidebar && !isClickOnToggleButton) {
        sidebar.classList.remove("show");
    }
});

function toggleSidebar() {
    document.getElementById("sidebar").classList.toggle("show");
}

// Chiude la sidebar se clicchi fuori
document.addEventListener("click", function(event) {
    const sidebar = document.getElementById("sidebar");
    const toggleButton = document.getElementById("menu-toggle"); 

    const isClickInsideSidebar = sidebar.contains(event.target);
    const isClickOnToggleButton = toggleButton && toggleButton.contains(event.target);

    if (sidebar.classList.contains("show") && !isClickInsideSidebar && !isClickOnToggleButton) {
        sidebar.classList.remove("show");
    }
});

function toggleSearch() {
    const searchForm = document.getElementById('search-form');
    searchForm.classList.toggle('active');

    if (searchForm.classList.contains('active')) {
        document.getElementById('search-bar').focus();
    }
}

function submitSearch() {
    const searchBar = document.getElementById('search-bar');
    if (searchBar.value.trim() === '') {
        // Se vuoto, chiudo barra e non invio form
        document.getElementById('search-form').classList.remove('active');
        return false;
    }
    return true;
}


