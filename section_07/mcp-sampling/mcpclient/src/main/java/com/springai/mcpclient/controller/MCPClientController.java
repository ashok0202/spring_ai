package com.springai.mcpclient.controller;

import com.springai.mcpclient.util.ToolUtil;
import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/api")
public class MCPClientController {

    private  final ChatClient chatClient;

    private final List<McpSyncClient> mcpSyncClients;

    public MCPClientController(@Qualifier("chatMemoryChatClient") ChatClient chatClientBuilder,
     List<McpSyncClient> mcpClients) {
        this.chatClient = chatClientBuilder;
        this.mcpSyncClients = mcpClients;
    }

    @GetMapping("/chat")
    public String chat(@RequestHeader(value = "username",required = false) String username,
                       @RequestParam("message") String message) {
        ToolCallback[] toolCallbacks = ToolUtil.selectToolsFor(mcpSyncClients,"helpdesk-mcp-server",null);
        return chatClient.prompt().user(message+ " My username is " + username).advisors(
                advisorSpec -> advisorSpec.param(CONVERSATION_ID,username)
                ).tools(toolCallbacks)
                .call().content();
    }
}