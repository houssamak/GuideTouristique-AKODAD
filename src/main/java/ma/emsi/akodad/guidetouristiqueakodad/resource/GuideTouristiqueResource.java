package ma.emsi.akodad.guidetouristiqueakodad.resource;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.emsi.akodad.guidetouristiqueakodad.llm.LlmClientForGuideTouristique;

/**
 * Ressource REST qui joue le rôle de guide touristique.
 * Endpoint : /api/guide/lieu/{ville_ou_pays}?nb=...
 * Les informations touristiques sont fournies par un LLM (Gemini).
 */
@Path("/guide")
@RequestScoped
public class GuideTouristiqueResource {

    /** Nombre maximum d'endroits, pour limiter le nombre de tokens (et donc le coût). */
    private static final int NB_MAX = 10;

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

    // ===== Version 3 (étape 4.5) : LLM avec 2 endroits fixes =====
    // @GET
    // @Path("lieu/{ville_ou_pays}")
    // @Produces(MediaType.APPLICATION_JSON)
    // public String endroitsDeVisite(@PathParam("ville_ou_pays") String villeOuPays) {
    //     return llmClient.endroitsDeVisite(villeOuPays);
    // }

    /**
     * Donne les principaux endroits à visiter dans une ville ou un pays,
     * ainsi que le prix moyen d'un repas dans la devise du pays.
     * Le nombre d'endroits est donné par le paramètre de requête "nb"
     * (2 par défaut). Exemple : .../lieu/Paris?nb=4
     *
     * @param villeOuPays le nom de la ville ou du pays, extrait de l'URL
     *                    (.../lieu/{ville_ou_pays})
     * @param nb          le nombre d'endroits à visiter (paramètre de requête,
     *                    2 par défaut, entre 1 et 10)
     * @return le document JSON renvoyé par le LLM (statut 200), ou un message
     *         d'erreur JSON (statut 400) si nb n'est pas entre 1 et 10
     */
    @GET
    @Path("lieu/{ville_ou_pays}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response endroitsDeVisite(@PathParam("ville_ou_pays") String villeOuPays,
                                     @QueryParam("nb") @DefaultValue("2") int nb) {
        if (nb < 1 || nb > NB_MAX) {
            String erreur = "{\"erreur\": \"Le paramètre nb doit être compris entre 1 et "
                    + NB_MAX + ".\"}";
            return Response.status(Response.Status.BAD_REQUEST).entity(erreur).build();
        }
        String reponseJson = llmClient.endroitsDeVisite(villeOuPays, nb);
        return Response.ok(reponseJson).build();
    }
}