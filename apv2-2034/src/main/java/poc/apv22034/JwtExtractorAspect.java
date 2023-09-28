package poc.apv22034;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * Aspect class for extracting JWT tokens from incoming requests.
 */
@Aspect
@Component
@Slf4j
public class JwtExtractorAspect {

    /**
     * JwtStorage instance for storing JWT tokens.
     */
    private final WebClientContext webClientContext;

    JwtExtractorAspect(WebClientContext webClientContext) {
        this.webClientContext = webClientContext;
    }

    /**
     * Executes before the callWebClient method in the Controller class.
     * Extracts the JWT token from the Authorization header and stores it.
     *
     * @param joinPoint JoinPoint instance.
     * @param exchange  ServerWebExchange instance.
     */
    @Before("execution(* poc.apv22034.Controller.callWebClient(..)) && args(exchange,..)")
    public void beforeControllerMethod(JoinPoint joinPoint, ServerWebExchange exchange) {
        final String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        webClientContext.setJwtToken(token);
        log.info("1 Token extracted from header and stored {}", token);
    }
}
