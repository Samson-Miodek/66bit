package ru.denisss.footballplayerscatalog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
//        app.js MainController
//        подписываеся и слушаем /topic/channel-name
        config.enableSimpleBroker("/topic");
//        app.js
//        используется только как префикс, его можно не писать, но без него не понятно
//        MainController
//        @MessageMapping("{/app не должно быть}/hello")
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        используется в app.js, для подключения
//        var socket = new SockJS('/gs-guide-websocket');
        registry.addEndpoint("/websocket").withSockJS();
    }

}
