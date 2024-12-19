const API_BASE_URL = "http://localhost:8082/livres";

document.getElementById("addBookForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const titre = document.getElementById("titre").value;
    const auteur = document.getElementById("auteur").value;

    fetch(API_BASE_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            titre: titre,
            auteur: auteur,
            disponible: true, // par défaut, le livre est disponible
        }),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erreur HTTP : ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            alert("Livre ajouté avec succès !");
            document.getElementById("addBookForm").reset();
            chargerLivresDisponibles();
        })
        .catch(error => console.error("Erreur:", error));
});

function chargerLivresDisponibles() {
    fetch(`${API_BASE_URL}/disponibles`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erreur HTTP : ${response.status}`);
            }
            return response.json();
        })
        .then(books => {
            const booksList = document.getElementById("booksList");
            booksList.innerHTML = "";

            books.forEach(book => {
                const li = document.createElement("li");
                li.innerHTML = `
                    <strong>${book.titre}</strong> par ${book.auteur}
                    <button class="emprunter" onclick="changerEtat(${book.id}, false)">Emprunter</button>
                    <button onclick="changerEtat(${book.id}, true)">Rendre</button>
                `;
                booksList.appendChild(li);
            });
        })
        .catch(error => console.error("Erreur lors du chargement des livres :", error));
}

function changerEtat(id, disponible) {
    fetch(`${API_BASE_URL}/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            disponible: disponible,  // État du livre mis à jour
        }),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erreur HTTP : ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            alert("L'état du livre a été mis à jour !");
            chargerLivresDisponibles();
        })
        .catch(error => console.error("Erreur lors de la mise à jour :", error));
}

document.addEventListener("DOMContentLoaded", chargerLivresDisponibles);
