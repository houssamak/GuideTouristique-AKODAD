package ma.emsi.akodad.guidetouristiqueakodad.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Ressource REST qui joue le rôle de guide touristique.
 * Endpoint : /api/guide/lieu/{ville_ou_pays}
 */
@Path("/guide")
public class GuideTouristiqueResource {

    // ===== Version 1 (étape 3.a) : retourne simplement le paramètre =====
    // @GET
    // @Path("lieu/{ville_ou_pays}")
    // @Produces(MediaType.APPLICATION_JSON)
    // public String endroitsDeVisite(@PathParam("ville_ou_pays") String villeOuPays) {
    //     return villeOuPays;
    // }

    /**
     * Donne les principaux endroits à visiter dans une ville ou un pays.
     * Version de test : retourne un tableau dont le seul élément est le nom
     * du lieu reçu dans l'URL. JAX-RS convertit automatiquement le tableau
     * en JSON grâce à l'annotation @Produces.
     *
     * @param villeOuPays le nom de la ville ou du pays, extrait de l'URL
     *                    (.../lieu/{ville_ou_pays})
     * @return un tableau contenant le nom du lieu, converti en tableau JSON
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public String[] endroitsDeVisite(@PathParam("ville_ou_pays") String villeOuPays) {
        return new String[]{villeOuPays};
    }
}