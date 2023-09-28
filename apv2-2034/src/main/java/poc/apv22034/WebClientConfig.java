package poc.apv22034;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class for WebClient with JWT token support.
 */
@Configuration
@Slf4j
public class WebClientConfig {

    /**
     * Instance of JwtStorage for JWT token management.
     */
    private final WebClientContext webClientContext;

    WebClientConfig(WebClientContext webClientContext) {
        this.webClientContext = webClientContext;
    }

    /**
     * Creates and returns a WebClient instance with an authorization header filter.
     *
     * @return WebClient instance
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .filter(addAuthorizationHeader())
                .build();
    }

    /**
     * Adds an authorization header to the WebClient request if a JWT token is present.
     * The filter function can modify the request and then call the next filter in the chain.
     * If the filter function does not modify the request, it can simply call the next filter in the chain
     *
     * @return ExchangeFilterFunction to add the authorization header
     */
    private ExchangeFilterFunction addAuthorizationHeader() {
        log.info("Adding reference to JWT token in WebClient header");
        return (clientRequest, next) -> {
           final String jwtToken = webClientContext.getJwtToken();
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
