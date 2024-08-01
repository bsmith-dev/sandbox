package sandbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    public Flux<User> getAllUsers() {
        return Flux.just(new User(1, username, password));
    }

    public Mono<User> getUserById(String id) {
        return Mono.just(new User(Integer.parseInt(id), username, password));
    }
}
