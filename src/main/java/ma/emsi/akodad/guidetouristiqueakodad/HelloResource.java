package ma.emsi.akodad.guidetouristiqueakodad;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource {

    /**
     * Salue la personne dont le nom est donné dans l'URL.
     *
     * @param nom le nom de la personne, extrait de l'URL (.../personnes/{nom})
     * @return un message de salutation
     */
    @GET
    @Path("personnes/{nom}")
    @Produces("text/plain")
    public String hello(@PathParam("nom") String nom) {
        return "Hello, " + nom;
    }
}