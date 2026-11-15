package ma.emsi.akodad.guidetouristiqueakodad;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configuration de l'application REST (JAX-RS).
 * Toutes les ressources REST de l'application sont accessibles
 * à partir du chemin /api.
 */
@ApplicationPath("/api")
public class RestConfiguration extends Application {
}