package isimm.mp1ds.biblio_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import isimm.mp1ds.biblio_app.model.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Integer> {

    // Vous pouvez ajouter des méthodes personnalisées ici
    // Exemple : Trouver un livre par son titre
    Livre findByTitre(String titre);

    // Exemple : Trouver tous les livres disponibles
    List<Livre> findByDisponibleTrue();

    // Exemple : Trouver tous les livres par auteur
    List<Livre> findByAuteur(String auteur);
}
