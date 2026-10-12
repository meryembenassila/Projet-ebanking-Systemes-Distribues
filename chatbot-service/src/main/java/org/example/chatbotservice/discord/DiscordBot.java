package org.example.chatbotservice.discord;

import com.zgamelogic.discord.annotations.DiscordController;
import com.zgamelogic.discord.annotations.DiscordMapping;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.example.chatbotservice.agent.EbankIaAgent;

@DiscordController
public class DiscordBot {
    EbankIaAgent ebankIaAgent;
    public DiscordBot(EbankIaAgent ebankIaAgent){
        this.ebankIaAgent=ebankIaAgent;
    }
    @DiscordMapping
    private void perform(MessageReceivedEvent event) {
        System.out.println(">>> MESSAGE DISCORD REÇU <<<");

        // Vérifie si le message vient d'un autre bot.
        // Si oui, on arrête immédiatement pour éviter que
        // les bots se répondent entre eux indéfiniment.
        if (event.getAuthor().isBot()) {
            return;
        }

        // Récupère le contenu du message envoyé par l'utilisateur.
        // Exemple :
        // Utilisateur écrit : "Quel est mon solde ?"
        // query = "Quel est mon solde ?"
        String query = event.getMessage().getContentRaw();
        System.out.println("QUERY = " + query);

        // Envoie la question à notre agent IA.
        // L'agent utilise ensuite le modèle d'IA pour générer une réponse.
        String response = ebankIaAgent.chat("1",query);
        System.out.println("Response = " + response);

        // Envoie la réponse générée par l'IA dans le même canal Discord.
        // .queue() permet à JDA d'effectuer l'envoi de manière asynchrone.
        event.getChannel().sendMessage(response).queue();
    }
}
