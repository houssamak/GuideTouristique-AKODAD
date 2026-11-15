package ma.emsi.akodad.guidetouristiqueakodad.resource;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ma.emsi.akodad.guidetouristiqueakodad.llm.LlmClientForGuideTouristique;

/**
 * Ressource REST qui joue le rôle de guide touristique.
 * Endpoint : /api/guide/lieu/{ville_ou_pays}
 * Les informations touristiques sont fournies par un LLM (Gemini).
 */
@Path("/guide")
@RequestScoped
public class GuideTouristiqueResource {

    /** Client qui communique avec le LLM, injecté par CDI. */
    @Inject
    private LlmClientForGuideTouristique llmClient;

    // ===== Version 1 (étape 3.a) : retourne simplement le paramètre =====
    // @GET
    // @Path("lieu/{ville_ou_pays}")
    // @Produces(MediaType.APPLICATION_JSON)
    // public String endroitsDeVisite(@PathParam("ville_ou_pays") String villeOuPays) {
    //     return villeOuPays;
    // }

    // ===== Version 2 (étape 3.b) : retourne un tableau de String =====
    // @GET
    // @Path("lieu/{ville_ou_pays}")
    // @Produces(MediaType.APPLICATION_JSON)
    // public String[] endroitsDeVisite(@PathParam("ville_ou_pays") String villeOuPays) {
    //     return new String[]{villeOuPays};
    // }

    /**
     * Donne les 2 principaux endroits à visiter dans une ville ou un pays,
     * ainsi que le prix moyen d'un repas dans la devise du pays.
     * Les informations sont demandées au LLM, qui répond au format JSON.
     *
     * @param villeOuPays le nom de la ville ou du pays, extrait de l'URL
     *                    (.../lieu/{ville_ou_pays})
     * @return le document JSON renvoyé par le LLM
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public String endroitsDeVisite(@PathParam("ville_ou_pays") String villeOuPays) {
        return llmClient.endroitsDeVisite(villeOuPays);
    }
}