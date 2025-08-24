package com.spiringai.open_ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChartController {

    private final ChatClient chatClient;

    public ChartController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @RequestMapping("/chat")
    public String getChart(@RequestParam("message") String message) {

        return  chatClient.prompt(message).call().content();
    }
}
