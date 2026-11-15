# Utilisation de l'IA – TP 3 : Application REST avec LangChain4j

## Vue d'ensemble

Pour ce TP, j'ai utilisé Claude comme assistant pour m'aider à comprendre certaines difficultés rencontrées pendant le développement de mon application REST avec Jakarta EE et LangChain4j.

J'ai réalisé moi-même les modifications dans IntelliJ, les déploiements sur Payara et les tests. Lorsque je rencontrais un problème que je ne comprenais pas, je transmettais mon code ou les messages d'erreur à l'IA pour obtenir des explications.

Voici les situations dans lesquelles l'IA m'a été particulièrement utile.

---

## 1. Le serveur exécutait une ancienne version de mon code

**Problème rencontré :** après avoir modifié `HelloResource` pour renvoyer une erreur 500 avec `Response.serverError()`, le navigateur affichait toujours un statut 200 avec l'ancien résultat.

**Pourquoi j'ai utilisé l'IA :** mon code semblait correct, mais je ne comprenais pas pourquoi mes modifications n'étaient pas prises en compte.

**Prompt utilisé :** « meme probleme voila mon code : » (suivi du code de `HelloResource.java`)

**Réponse de l'IA :** l'IA m'a expliqué que Payara exécutait probablement une ancienne version de l'artifact `war exploded`. Elle m'a conseillé de reconstruire le projet et l'artifact avant de redéployer l'application.

**Ce que j'ai appris :** j'ai compris la différence entre modifier le code source et déployer une nouvelle version de l'application. Un serveur peut continuer à exécuter une ancienne version si l'artifact n'a pas été reconstruit. J'ai aussi appris à utiliser `curl` pour vérifier les réponses HTTP.

---

## 2. Adapter la classe LLM du TP 2 à une application REST

**Problème rencontré :** je devais réutiliser ma classe `LlmClient` du TP 2, mais celle-ci contenait une mémoire de conversation, un setter pour le rôle système et utilisait `@Dependent`. Je ne savais pas quelles parties conserver.

**Pourquoi j'ai utilisé l'IA :** je voulais comprendre comment adapter cette classe à une application REST où plusieurs utilisateurs peuvent envoyer des requêtes indépendantes.

**Prompt utilisé :** j'ai transmis à l'IA le code de `LlmClient`, l'interface `Assistant` et le `pom.xml` du TP 2.

**Réponse de l'IA :** l'IA m'a conseillé de supprimer la mémoire de conversation, de définir le rôle système avec `@SystemMessage` et d'utiliser `@ApplicationScoped` avec une initialisation dans `@PostConstruct`.

**Ce que j'ai appris :** j'ai compris qu'une mémoire de conversation partagée entre plusieurs utilisateurs pouvait mélanger leurs échanges. Comme chaque requête REST est indépendante, il n'est pas nécessaire de conserver cet état. J'ai également mieux compris le choix des portées CDI selon le fonctionnement de l'application.

---

## 3. Vérifier le fonctionnement de `responseFormat` avec Gemini

**Problème rencontré :** après avoir ajouté `.responseFormat(ResponseFormat.JSON)`, les réponses obtenues étaient identiques à celles d'avant. Je ne savais pas si la modification était réellement prise en compte.

**Pourquoi j'ai utilisé l'IA :** je voulais vérifier que cette méthode était compatible avec `GoogleGenAiChatModel` et que le serveur exécutait bien le nouveau code.

**Prompt utilisé :** j'ai envoyé la réponse JSON obtenue pour Nice et une capture de l'autocomplétion IntelliJ sur le builder.

**Réponse de l'IA :** l'IA m'a expliqué qu'un résultat identique ne prouvait pas que le nouveau code était exécuté. Elle m'a proposé de vérifier l'autocomplétion, la compilation et les modifications avec `git diff`.

**Ce que j'ai appris :** j'ai compris la différence entre demander au modèle de répondre en JSON dans le prompt et configurer directement le format de réponse dans l'API. J'ai aussi appris qu'un résultat correct ne suffit pas à prouver qu'une modification fonctionne : il faut vérifier le code compilé et déployé.

---

## 4. Bonus : rendre le nombre d'endroits dynamique

**Problème rencontré :** pour le bonus, je devais permettre à l'utilisateur de choisir le nombre d'endroits à visiter, alors que le message système de mon assistant était fixe.

**Pourquoi j'ai utilisé l'IA :** je ne savais pas comment transmettre une valeur dynamique dans un message système LangChain4j tout en conservant la ville comme message utilisateur.

**Prompt utilisé :** suite de la conversation avec l'IA lors du passage à l'exercice bonus.

**Réponse de l'IA :** l'IA m'a proposé d'utiliser `{{nb}}` dans `@SystemMessage`, associé à `@V("nb")`, ainsi que `@UserMessage` pour identifier le paramètre contenant la ville. Pour l'API REST, elle m'a conseillé `@QueryParam` et `@DefaultValue`, avec une validation du nombre entre 1 et 10.

**Ce que j'ai appris :** j'ai compris comment utiliser les variables de template dans LangChain4j et comment gérer plusieurs paramètres dans une méthode d'assistant. J'ai aussi appris à valider les paramètres d'une API REST et à renvoyer une erreur HTTP 400 lorsqu'une valeur est incorrecte.

---

## Bilan

L'IA m'a surtout aidé à comprendre des problèmes que je n'arrivais pas à résoudre seul, notamment le déploiement sur Payara, l'adaptation d'une classe avec état à une API REST et le fonctionnement des paramètres dynamiques dans LangChain4j.

Elle m'a permis de mieux comprendre les solutions au lieu de simplement modifier le code sans savoir pourquoi. J'ai ensuite appliqué les modifications et vérifié leur fonctionnement avec mes propres tests.