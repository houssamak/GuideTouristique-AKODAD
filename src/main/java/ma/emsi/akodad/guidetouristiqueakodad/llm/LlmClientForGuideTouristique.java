package ma.emsi.akodad.guidetouristiqueakodad.llm;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import dev.langchain4j.model.chat.request.ResponseFormat;

/**
 * Client qui gère la communication avec le LLM (Gemini) pour le guide touristique.
 * Adapté de la classe LlmClient du TP 2 : le rôle système est maintenant fixe
 * (défini par @SystemMessage dans l'interface GuideTouristique), donc il n'y a
 * plus de setter pour le rôle système ni de mémoire de conversation.
 * Une seule instance est créée pour toute l'application (@ApplicationScoped).
 */
@ApplicationScoped
public class LlmClientForGuideTouristique {

    /** Service implémenté par LangChain4j à partir de l'interface GuideTouristique. */
    private GuideTouristique guideTouristique;

    /**
     * Crée le modèle Gemini et le service GuideTouristique.
     * Appelée une seule fois par CDI, après la création de l'instance.
     */
    @PostConstruct
    public void init() {
        String apiKey = System.getenv("GEMINI_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "La variable d'environnement GEMINI_KEY n'est pas définie.");
        }

        ChatModel model = GoogleGenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-3.8-flash")
                // Demande à l'API Gemini de ne répondre qu'avec du JSON (sans Markdown)
                .responseFormat(ResponseFormat.JSON)
                .build();

        guideTouristique = AiServices.builder(GuideTouristique.class)
                .chatModel(model)
                .build();
    }

    /**
     * Demande au LLM les informations touristiques sur une ville ou un pays.
     *
     * @param villeOuPays le nom de la ville ou du pays
     * @return la réponse du LLM au format JSON (sous forme de String)
     */
    public String endroitsDeVisite(String villeOuPays) {
        return guideTouristique.chat(villeOuPays);
    }
}