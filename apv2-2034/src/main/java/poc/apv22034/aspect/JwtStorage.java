package poc.apv22034.aspect;

import org.springframework.stereotype.Component;

@Component
public class JwtStorage {

    private ThreadLocal<String> jwtToken = new ThreadLocal<>();

    public void setJwtToken(String token) {
        this.jwtToken.set(token);
    }

    public String getJwtToken() {
        return this.jwtToken.get();
    }
}
