package ma.emsi.akodad.guidetouristiqueakodad.llm;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 * Service d'IA « guide touristique », implémenté automatiquement
 * par LangChain4j (AiServices).
 * Le rôle système est fixé par l'annotation @SystemMessage.
 */
public interface GuideTouristique {

    // ===== Version 1 (étape 4.3) : nombre d'endroits fixé à 2 =====
    // @SystemMessage("""
    //         Tu es un guide touristique.
    //         Pour la ville ou le pays donné par l'utilisateur, indique les 2 principaux
    //         endroits à visiter, ainsi que le prix moyen d'un repas dans la devise du pays.
    //         N'utilise pas Markdown, réponds uniquement avec du JSON.
    //         Ta réponse doit respecter exactement ce format :
    //         {
    //           "ville_ou_pays": "nom de la ville ou du pays",
    //           "endroits_a_visiter": ["endroit 1", "endroit 2"],
    //           "prix_moyen_repas": "<prix> <devise du pays>"
    //         }
    //         """)
    // String chat(String villeOuPays);

    /**
     * Demande au LLM les principaux endroits à visiter dans une ville ou un pays
     * (le nombre d'endroits est choisi par l'utilisateur), ainsi que le prix moyen
     * d'un repas dans la devise du pays.
     *
     * @param villeOuPays le nom de la ville ou du pays (message utilisateur)
     * @param nb          le nombre d'endroits à visiter demandés
     * @return la réponse du LLM, un document JSON sous forme de String
     */
    @SystemMessage("""
            Tu es un guide touristique.
            Pour la ville ou le pays donné par l'utilisateur, indique les {{nb}} principaux
            endroits à visiter, ainsi que le prix moyen d'un repas dans la devise du pays.
            N'utilise pas Markdown, réponds uniquement avec du JSON.
            Ta réponse doit respecter exactement ce format :
            {
              "ville_ou_pays": "nom de la ville ou du pays",
              "endroits_a_visiter": ["endroit 1", "endroit 2", ...],
              "prix_moyen_repas": "<prix> <devise du pays>"
            }
            Le tableau "endroits_a_visiter" doit contenir exactement {{nb}} éléments.
            """)
    @UserMessage("{{villeOuPays}}")
    String chat(@V("villeOuPays") String villeOuPays, @V("nb") int nb);
}