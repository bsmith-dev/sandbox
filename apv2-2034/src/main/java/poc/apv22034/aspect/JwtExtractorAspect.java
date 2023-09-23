package poc.apv22034.aspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class JwtExtractorAspect {

    private final JwtStorage jwtStorage;

    @Before("execution(* poc.apv22034.controller.ProductController.callWebClient(..)) && args(exchange,..)")
    public void beforeControllerMethod(JoinPoint joinPoint, ServerWebExchange exchange) {
        log.info("1 Aspect called to extract JWT token from request header");
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        jwtStorage.setJwtToken(token);
        log.info("2 Token stored {}", token);

    }

}

