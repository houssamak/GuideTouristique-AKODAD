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

    /**
     * Donne les principaux endroits à visiter dans une ville ou un pays.
     * Version de test : retourne simplement le nom du lieu reçu dans l'URL.
     *
     * @param villeOuPays le nom de la ville ou du pays, extrait de l'URL
     *                    (.../lieu/{ville_ou_pays})
     * @return le nom du lieu passé en paramètre
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public String endroitsDeVisite(@PathParam("ville_ou_pays") String villeOuPays) {
        return villeOuPays;
    }
}