package org.example.chatbotservice.web;


import org.example.chatbotservice.agent.EbankIaAgent;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.client.ChatClient;
import reactor.core.publisher.Flux;

import java.awt.*;

@RestController

public class EbanKChatBotController {
    private EbankIaAgent ebankIaAgent ;


   public EbanKChatBotController(EbankIaAgent ebankIaAgent){
    this.ebankIaAgent=ebankIaAgent;
}

    @GetMapping(value = "/chat",produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat(
            @RequestParam(name = "query", defaultValue = "Bonjour") String query
          ) {

        System.out.println("query " + query);
        return ebankIaAgent.chat("1", query);
}

    @GetMapping(value = "/chatStream",produces = MediaType.TEXT_PLAIN_VALUE)
    public Flux<String> chatStream(
            @RequestParam(name = "query", defaultValue = "Bonjour") String query
    ) {

        System.out.println("query " + query);
        return ebankIaAgent.chatStream("1", query);
    }


}
