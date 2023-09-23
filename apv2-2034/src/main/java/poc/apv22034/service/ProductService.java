package poc.apv22034.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import poc.apv22034.dao.ProductDao;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    ProductDao dao;
    private final WebClient webClient;

    public Mono<String> callWebClient() {
        log.info("4 Calling WebClient with the JWT token");
        webClient.get()
                .uri("http://localhost:8080/poc/validateJwtIsPresent")
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(token -> log.info("6 JWT received from WebClient call to validateJwtIsPresent: {}", token));

        return Mono.just("7 POC executed successfully!");
    }

}
