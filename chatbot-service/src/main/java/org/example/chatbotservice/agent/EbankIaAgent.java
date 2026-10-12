package org.example.chatbotservice.agent;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EbankIaAgent {

    private ChatClient chatClient;

    public EbankIaAgent(
            ChatClient.Builder chatclient,
            ChatMemory chatMemory,
            ToolCallbackProvider mcpTools) {

        this.chatClient = chatclient
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                .defaultTools(mcpTools)
                .build();
    }

    public String chat(String conversationId, String query) {

        return chatClient.prompt()
                .user(query)
                .options(
                        OpenAiChatOptions.builder()
                                .extraBody(
                                        Map.of("include_reasoning", false)
                                )
                )
                .advisors(advisor -> advisor
                        .param(ChatMemory.CONVERSATION_ID, conversationId)
                )
                .call()
                .content();
    }


    // .prompt(query) : prépare le message à envoyer au LLM
    // .call()        : envoie la requête au LLM via Spring AI
    // .content()     : récupère le texte de la réponse du LLM
}
