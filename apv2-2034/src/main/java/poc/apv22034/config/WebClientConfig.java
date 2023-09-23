package poc.apv22034.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import poc.apv22034.aspect.JwtStorage;

@Configuration
@AllArgsConstructor
@Slf4j
public class WebClientConfig {

    private final JwtStorage jwtStorage;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .filter(addAuthorizationHeader())
                .build();
    }

    private ExchangeFilterFunction addAuthorizationHeader() {
        log.info("0 Adding reference to JWT token in WebClient header");
        return (clientRequest, next) -> {
            String jwtToken = jwtStorage.getJwtToken();
            if (jwtToken != null) {
                ClientRequest newClientRequest = ClientRequest.from(clientRequest)
                        .header("Authorization", jwtToken)
                        .build();
                return next.exchange(newClientRequest);
            } else {
                return next.exchange(clientRequest);
            }
        };
    }
}
