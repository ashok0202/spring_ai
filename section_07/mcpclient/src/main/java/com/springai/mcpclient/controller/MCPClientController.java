package com.springai.mcpclient.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/api")
public class MCPClientController {

    private  final ChatClient chatClient;

    public MCPClientController(@Qualifier("chatMemoryChatClient") ChatClient chatClientBuilder) {
        this.chatClient = chatClientBuilder;
    }

    @GetMapping("/chat")
    public String chat(@RequestHeader(value = "username",required = false) String username,
                       @RequestParam("message") String message) {
        return chatClient.prompt().user(message+ " My username is " + username).advisors(
                advisorSpec -> advisorSpec.param(CONVERSATION_ID,username)
                )
                .call().content();
    }
}