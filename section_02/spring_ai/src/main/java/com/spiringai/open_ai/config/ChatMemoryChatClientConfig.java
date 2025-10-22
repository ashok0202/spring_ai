package com.spiringai.open_ai.config;

import com.spiringai.open_ai.advisors.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatMemoryChatClientConfig {

    /**
     * Bean to configure the chat memory for the chat client.
     *
     * The chat memory is responsible for storing the conversation history
     * and providing the context for the AI to generate responses.
     *
     * This configuration creates a chat memory that stores up to 10
     * messages in the conversation history. The chat memory is stored
     * in a database using the JDBC chat memory repository.
     *
     * @param jdbcChatMemoryRepository the JDBC chat memory repository
     * @return the chat memory configuration
     */
    @Bean
    ChatMemory chatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository) {
        return MessageWindowChatMemory.builder().maxMessages(10)
                .chatMemoryRepository(jdbcChatMemoryRepository).build();
    }

    /**
     * Bean to configure the chat client with the chat memory.
     *
     * The chat client is responsible for generating responses to user
     * messages. This configuration creates a chat client that uses the
     * chat memory to store the conversation history and provide context
     * for the AI to generate responses.
     *
     * The chat client is configured with two advisors: a
     * {@link SimpleLoggerAdvisor} that logs the conversation, and a
     * {@link MessageChatMemoryAdvisor} that stores the conversation history
     * in the chat memory.
     *
     * @param chatClientBuilder the chat client builder
     * @param chatMemory the chat memory
     * @return the configured chat client
     */
    @Bean("chatMemoryChatClient")
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory) {
        Advisor advisor = new SimpleLoggerAdvisor();
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return chatClientBuilder
                .defaultAdvisors(List.of(advisor, memoryAdvisor))
                .build();
    }
}
