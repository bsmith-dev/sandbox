package sandpox.aspects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HelloWorldServiceTest {

    @Test
    public void testAppendWorld() {
        HelloWorldService service = new HelloWorldService();
        assertEquals("world", service.appendWorld());
    }
}
