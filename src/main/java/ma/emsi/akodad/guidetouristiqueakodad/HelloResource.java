package ma.emsi.akodad.guidetouristiqueakodad;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/hello-world")
public class HelloResource {

    // ===== Version 1 : type retour String (étape 2.3) =====
    // @GET
    // @Path("personnes/{nom}")
    // @Produces("text/plain")
    // public String hello(@PathParam("nom") String nom) {
    //     return "Hello, " + nom;
    // }

    // ===== Version 2 : type retour Response avec statut OK (étape 2.4.a) =====
    // @GET
    // @Path("personnes/{nom}")
    // @Produces("text/plain")
    // public Response hello(@PathParam("nom") String nom) {
    //     return Response.ok("Hello, " + nom).build();
    // }

    /**
     * Salue la personne dont le nom est donné dans l'URL.
     * Version 3 : renvoie volontairement une erreur 500 (Internal Server Error)
     * pour montrer que Response permet de choisir le statut HTTP.
     *
     * @param nom le nom de la personne, extrait de l'URL (.../personnes/{nom})
     * @return une réponse HTTP avec le statut 500
     */
    @GET
    @Path("personnes/{nom}")
    @Produces("text/plain")
    public Response hello(@PathParam("nom") String nom) {
        // return Response.ok("Hello, " + nom).build();
        return Response.serverError()
                .entity("Erreur interne simulée pour " + nom)
                .build();
    }
}