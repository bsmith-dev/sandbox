package sandpox.aspects;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    public String appendWorld() {
        return "world";
    }
}
