package kz.com.ars.message_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WasenderWebClient {
    @Bean
    public WebClient.Builder wasenderWebClientBuilder(){
        return WebClient.builder()
                .baseUrl("https://wasenderapi.com");
    }
}
