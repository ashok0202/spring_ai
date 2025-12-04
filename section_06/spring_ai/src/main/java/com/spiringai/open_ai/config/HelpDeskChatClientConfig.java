package com.spiringai.open_ai.config;


import com.spiringai.open_ai.advisors.TokenUsageAuditAdvisor;
import com.spiringai.open_ai.tools.TimeTools;
import org.springframework.core.io.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class HelpDeskChatClientConfig {

    @Value("classpath:/promptTemplates/helpDeskSystemPromptTemplate")
    Resource systemPromptTemplate;

    @Bean("helpdeskChatClient")
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory
    , TimeTools timeTools) {
        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        Advisor tokenUsageAdvisor = new TokenUsageAuditAdvisor();
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return chatClientBuilder
                .defaultSystem(systemPromptTemplate)
                .defaultTools(timeTools)
                .defaultAdvisors(List.of(loggerAdvisor, memoryAdvisor, tokenUsageAdvisor))
                .build();
    }
}
