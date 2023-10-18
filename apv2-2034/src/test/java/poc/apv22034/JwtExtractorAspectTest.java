package poc.apv22034;

import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.http.server.reactive.MockServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.adapter.DefaultServerWebExchange;
import org.springframework.web.server.i18n.LocaleContextResolver;
import org.springframework.web.server.session.DefaultWebSessionManager;
import org.springframework.web.server.session.WebSessionManager;
import org.springframework.http.codec.ServerCodecConfigurer;

import static org.mockito.Mockito.*;

class JwtExtractorAspectTest {

    @Mock
    private WebClientContext webClientContext;

    @InjectMocks
    private JwtExtractorAspect jwtExtractorAspect;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void beforeControllerMethod() {
        // Arrange
        String jwtToken = "Bearer sample_token";
        ServerHttpRequest request = MockServerHttpRequest.get("/some-url")
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .build();
        ServerHttpResponse response = new MockServerHttpResponse();
        WebSessionManager sessionManager = new DefaultWebSessionManager();
        ServerCodecConfigurer codecConfigurer = ServerCodecConfigurer.create();
        LocaleContextResolver localeContextResolver = mock(LocaleContextResolver.class);

        ServerWebExchange exchange = new DefaultServerWebExchange(request, response, sessionManager, codecConfigurer, localeContextResolver);

        // Act
        jwtExtractorAspect.beforeControllerMethod(exchange);

        // Assert
        verify(webClientContext, times(1)).setJwtToken(jwtToken);
    }
}
