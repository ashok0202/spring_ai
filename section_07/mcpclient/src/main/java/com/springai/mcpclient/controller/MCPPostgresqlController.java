package com.springai.mcpclient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class MCPPostgresqlController {

    private static final Logger LOGGER= LoggerFactory.getLogger(MCPPostgresqlController.class);

    private final ChatClient chatClient;

    @Value("classpath:/promptTemplates/systemPromptTemplate.st")
    Resource systemPromptTemplate;

    private static final String SYSTEM_PROMPT = """
            You are a helpful PostgreSQL database assistant.
            You have access to a PostgreSQL database via the 'query' tool.
            
            IMPORTANT RULES:
            - ALWAYS use standard PostgreSQL syntax only.
            - To list tables, use:
              SELECT table_name FROM information_schema.tables WHERE table_schema = 'public';
            - To describe a table, use:
              SELECT column_name, data_type FROM information_schema.columns WHERE table_name = 'your_table';
            - NEVER use MySQL syntax like SHOW TABLES or DESCRIBE.
            - NEVER use SHOW for anything except SHOW search_path.
            - Always wrap queries in proper SELECT statements.
            - For date filtering, use: WHERE column_name BETWEEN 'YYYY-MM-DD' AND 'YYYY-MM-DD'
            """;

    public MCPPostgresqlController(ChatClient.Builder chatClientBuilder,
                               ToolCallbackProvider toolCallbackProvider) {

        Arrays.stream(toolCallbackProvider.getToolCallbacks()).forEach(tool->{
            LOGGER.info("Tools Callback found: {}",tool);
        });
        this.chatClient = chatClientBuilder.defaultToolCallbacks(toolCallbackProvider)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .defaultSystem(SYSTEM_PROMPT)
                .build();
    }

    @GetMapping("/databaseChat")
    public String chatMessage(@RequestParam("message")String message){
        return chatClient.prompt().user(message).call().content();
    }
}