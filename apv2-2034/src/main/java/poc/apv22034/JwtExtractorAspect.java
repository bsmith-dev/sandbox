package poc.apv22034;

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

    @Before("execution(* poc.apv22034.Controller.callWebClient(..)) && args(exchange,..)")
    public void beforeControllerMethod(JoinPoint joinPoint, ServerWebExchange exchange) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        jwtStorage.setJwtToken(token);
        log.info("1 Token extracted from header and stored {}", token);

    }

}

