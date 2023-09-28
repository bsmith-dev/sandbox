package poc.apv22034;

import org.springframework.stereotype.Component;

/**
 * This class is responsible for storing JWT tokens in a thread-local storage.
 * It is annotated as a Spring component, allowing it to be automatically managed by the Spring container.
 *
 */
@Component
public class WebClientContext {

    /**
     * Thread-local storage for JWT tokens.
     * This ensures that each thread has its own isolated storage for the token.
     */
    private ThreadLocal<String> jwtToken = new ThreadLocal<>();

    /**
     * Sets the JWT token for the current thread.
     *
     * @param token The JWT token to be stored.
     */
    public void setJwtToken(String token) {
        this.jwtToken.set(token);
    }

    /**
     * Retrieves the JWT token for the current thread.
     *
     * @return The JWT token stored for the current thread, or null if not set.
     */
    public String getJwtToken() {
        return this.jwtToken.get();
    }
}
