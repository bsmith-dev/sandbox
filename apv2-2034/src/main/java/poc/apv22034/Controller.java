package poc.apv22034;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Controller class for handling API requests.
 */
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/poc")
public class Controller {

    /**
     * Service instance for handling business logic.
     */
    private final Service service;

    /**
     * Handles GET requests to the root endpoint.
     *
     * @param exchange ServerWebExchange instance.
     * @return Mono<String> response.
     */
    @GetMapping("/")
    public Mono<String> callWebClient(ServerWebExchange exchange) {
        log.info("2 /poc endpoint called");
        return service.webClientExecutor();
    }

    /**
     * Validates JWT token from the Authorization header.
     *
     * @param token JWT token from the Authorization header.
     * @return Mono<String> response containing the token.
     */
    @GetMapping(value = "/mockApi")
    public Mono<String> validateJwtIsPresent(@RequestHeader("Authorization") String token) {
        log.info("4 /mockApi endpoint called with JWT: {}", token);
        return Mono.just(token);
    }
}
