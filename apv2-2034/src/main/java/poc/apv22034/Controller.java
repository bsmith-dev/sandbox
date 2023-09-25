package poc.apv22034;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/poc")
public class Controller {

    private final Service service;

    @GetMapping("/")
    public Mono<String> callWebClient(ServerWebExchange exchange) {
        log.info("2 /poc endpoint called");
        return service.webClientExecutor();
    }

    @GetMapping(value = "/mockApi")
    public Mono<String>  validateJwtIsPresent(@RequestHeader("Authorization") String token) {
        log.info("4 /mockApi endpoint called with JWT: {}", token);
        return Mono.just(token);
    }

}
