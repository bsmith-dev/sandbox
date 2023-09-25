package poc.apv22034;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Slf4j
public class Service {
    private final WebClient webClient;

    public Mono<String> webClientExecutor() {
        log.info("3 webClientExecutor() called");
        webClient.get()
                .uri("http://localhost:8080/poc/mockApi")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(token -> log.info("5 WebClient call completed with token: {}", token));

        return Mono.just("6 POC executed successfully!");
    }

}
