package poc.apv22034;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ControllerTest {

    @Mock
    private Service service;

    @Mock
    private ServerWebExchange exchange;

    @InjectMocks
    private Controller controller;

    @Test
    public void testCallWebClient() {
        String expectedResponse = "6 POC executed successfully!";
        when(service.webClientExecutor()).thenReturn(Mono.just(expectedResponse));

        Mono<String> result = controller.callWebClient(exchange);

        StepVerifier.create(result)
                .expectNext(expectedResponse)
                .verifyComplete();
    }

    @Test
    public void testValidateJwtIsPresent() {
        String token = "some-jwt-token";
        Mono<String> result = controller.validateJwtIsPresent(token);

        StepVerifier.create(result)
                .expectNext(token)
                .verifyComplete();
    }
}
