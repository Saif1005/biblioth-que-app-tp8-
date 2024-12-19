package isimm.mp1ds.biblio_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isimm.mp1ds.biblio_app.model.Livre;
import isimm.mp1ds.biblio_app.repository.LivreRepository;

@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    /**
     * Ajoute un livre à la base de données.
     * 
     * @param livre Le livre à ajouter.
     * @return Le livre ajouté.
     */
    public Livre ajouterLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    /**
     * Met à jour l'état de disponibilité d'un livre.
     * 
     * @param id L'identifiant du livre.
     * @param disponible L'état de disponibilité du livre.
     * @return Le livre mis à jour.
     */
    public Livre mettreAJourEtatLivre(int id, boolean disponible) {
        Optional<Livre> livreOpt = livreRepository.findById(id);
        if (livreOpt.isPresent()) {
            Livre livre = livreOpt.get();
            livre.setDisponible(disponible);
            return livreRepository.save(livre);
        } else {
            throw new RuntimeException("Livre non trouvé");
        }
    }

    /**
     * Récupère tous les livres disponibles.
     * 
     * @return Une liste de livres disponibles.
     */
    public List<Livre> recupererLivresDisponibles() {
        return livreRepository.findByDisponibleTrue();
    }
}
