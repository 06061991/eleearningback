package com.esprit.springjwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer{
	
	@Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/ws");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/sba-websocket-chat").setAllowedOrigins("http://localhost:4200", "http://localhost:4200/").withSockJS();
        registry.addEndpoint("/sba-websocket-notification").setAllowedOrigins("http://localhost:4200", "http://localhost:4200/").withSockJS();
        registry.addEndpoint("/sba-websocket-forum").setAllowedOrigins("http://localhost:4200", "http://localhost:4200/").withSockJS();
    }

}
