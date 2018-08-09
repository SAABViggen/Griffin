package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TrelloConfig {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.api.username}")
    private String username;

    @Value("${trello.api.key}")
    private String trelloApiKey;

    @Value("${trello.api.token}")
    private String trelloToken;
}