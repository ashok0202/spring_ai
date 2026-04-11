package com.springai.mcpserverstdio.config;

import com.springai.mcpserverstdio.tools.HelpDeskTools;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class McpServerConfig {

    @Bean
    List<ToolCallback> toolCallbacks(HelpDeskTools helpDeskTools) {
        return List.of(ToolCallbacks.from(helpDeskTools));
    }

//    @Bean
//    public ToolCallbackProvider myTools(HelpDeskTools helpDeskTools) {
//        List<ToolCallback> tools = List.of(ToolCallbacks.from(helpDeskTools));
//        return ToolCallbackProvider.from(tools);
//    }
}
