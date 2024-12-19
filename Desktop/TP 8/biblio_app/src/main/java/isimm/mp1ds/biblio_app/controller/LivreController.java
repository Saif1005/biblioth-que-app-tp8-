package isimm.mp1ds.biblio_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import isimm.mp1ds.biblio_app.model.Livre;
import isimm.mp1ds.biblio_app.service.LivreService;

@CrossOrigin(origins = "*") // Permet toutes les origines pour faciliter le développement front-end
@RestController
@RequestMapping("/livres")
public class LivreController {

    @Autowired
    private LivreService livreService;

    /**
     * Ajoute un nouveau livre à la bibliothèque.
     * 
     * @param livre Les détails du livre à ajouter.
     * @return Le livre ajouté.
     */
    @PostMapping
    public Livre ajouterLivre(@RequestBody Livre livre) {
        try {
            System.out.println("Ajout d'un livre : " + livre);
            return livreService.ajouterLivre(livre);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur lors de l'ajout du livre", e);
        }
    }

    /**
     * Met à jour l'état d'un livre (emprunté ou retourné).
     * 
     * @param id L'identifiant du livre.
     * @param livre Les informations du livre avec l'état mis à jour.
     * @return Le livre mis à jour.
     */
    @PutMapping("/{id}")
    public Livre mettreAJourEtatLivre(@PathVariable int id, @RequestBody Livre livre) {
        try {
            System.out.println("Mise à jour du livre avec ID : " + id + ", Disponible : " + livre.isDisponible());
            return livreService.mettreAJourEtatLivre(id, livre.isDisponible());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livre introuvable", e);
        }
    }

    /**
     * Récupère la liste des livres disponibles dans la bibliothèque.
     * 
     * @return Une liste de livres disponibles.
     */
    @GetMapping("/disponibles")
    public List<Livre> recupererLivresDisponibles() {
        try {
            System.out.println("Récupération des livres disponibles");
            return livreService.recupererLivresDisponibles();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur lors de la récupération des livres", e);
        }
    }
}
