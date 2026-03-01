package com.spiringai.open_ai.controller;

import com.spiringai.open_ai.model.CountryCities;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StructuredOutputController {

    private final ChatClient chatClient;

    public StructuredOutputController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    /**
     * Endpoint to test structured output converter in spring AI.
     * Returns a bean of type CountryCity, which contains a country and a list of cities.
     * @param message the message to be sent to the AI
     * @return a ResponseEntity containing a CountryCity object
     */
    @GetMapping("/chat-bean")
    public ResponseEntity<CountryCities> chatBean(@RequestParam("message") String message) {
        CountryCities countryCities = chatClient.prompt()
                .user(message).call()
                //.entity(CountryCities.class);
                .entity(new BeanOutputConverter<>(CountryCities.class));
        return ResponseEntity.ok(countryCities);
    }

    /**
     * Endpoint to test structured output converter in spring AI.
     * Returns a list of strings, which represents a list of country-city pairs.
     * @param message the message to be sent to the AI
     * @return a ResponseEntity containing a list of strings
     */
    @GetMapping("/chat-list")
    public ResponseEntity<List<String>> chatList(@RequestParam("message") String message) {
        List<String> countryCities = chatClient.prompt().
                user(message).call().entity(new ListOutputConverter());
        return ResponseEntity.ok(countryCities);
    }

    /**
     * Endpoint to test structured output converter in spring AI.
     * Returns a map of key-value pairs, where the key is a string and the value is an object.
     * @param message the message to be sent to the AI
     * @return a ResponseEntity containing a map of key-value pairs
     */
    @GetMapping("/chat-map")
    public ResponseEntity<Map<String,Object>> chatMap(@RequestParam("message") String message) {
        Map<String,Object> countryCities = chatClient.prompt().
                user(message).call().entity(new MapOutputConverter());
        return ResponseEntity.ok(countryCities);
    }
}
