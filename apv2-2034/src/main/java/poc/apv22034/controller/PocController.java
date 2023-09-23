package poc.apv22034.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import poc.apv22034.service.PocService;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/poc")
public class PocController {

    private final PocService service;

    @GetMapping("/")
    public Mono<String> callWebClient(ServerWebExchange exchange) {
        log.info("3 Received request from Postman and calling web client service");
        return service.callWebClient();
    }

    @GetMapping(value = "/validateJwtIsPresent")
    public Mono<String>  validateJwtIsPresent(@RequestHeader("Authorization") String token) {
        log.info("5 JWT received from WebClient header");
        return Mono.just(token);
    }

}
