package poc.apv22034;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Service class responsible for executing web client calls.
 */
@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Slf4j
public class Service {

    /**
     * WebClient instance for making HTTP calls.
     */
    private final WebClient webClient;

    /**
     * Executes a web client call with JWT in header and logs the received token.
     *
     * @return Mono<String> A Mono containing a success message.
     */
    public Mono<String> webClientExecutor() {
        log.info("3 webClientExecutor() called");

        // Execute web client call and log the received token
        webClient.get()
                .uri("http://localhost:8080/poc/mockApi")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(token -> log.info("5 WebClient call completed with token: {}", token));

        return Mono.just("6 POC executed successfully!");
    }
}
