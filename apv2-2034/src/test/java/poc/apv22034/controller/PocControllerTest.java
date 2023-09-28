package poc.apv22034.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;
import org.springframework.web.reactive.function.server.ServerRequest;
import poc.apv22034.Controller;
import poc.apv22034.Service;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@SpringBootTest
public class PocControllerTest {

    @Mock
    private Service pocService;

    @InjectMocks
    private Controller pocController;

    @Test
    public void testCallWebClient() {
        String jwtToken = "Bearer some-jwt-token";
        ServerRequest request = MockServerRequest.builder()
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .build();

        when(pocService.webClientExecutor()).thenReturn(Mono.just("Success"));

        Mono<String> result = pocController.callWebClient(request.exchange());
        StepVerifier.create(result)
                .expectNext("Success")
                .verifyComplete();
    }
}

