package poc.apv22034;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * Aspect class for extracting JWT tokens from incoming requests.
 *
 * @author Your Name
 * @version 1.0
 */
@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class JwtExtractorAspect {

    /**
     * JwtStorage instance for storing JWT tokens.
     */
    private final JwtStorage jwtStorage;

    /**
     * Executes before the callWebClient method in the Controller class.
     * Extracts the JWT token from the Authorization header and stores it.
     *
     * @param joinPoint JoinPoint instance.
     * @param exchange ServerWebExchange instance.
     */
    @Before("execution(* poc.apv22034.Controller.callWebClient(..)) && args(exchange,..)")
    public void beforeControllerMethod(JoinPoint joinPoint, ServerWebExchange exchange) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        jwtStorage.setJwtToken(token);
        log.info("1 Token extracted from header and stored {}", token);
    }
}
