package sandpox.aspects;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class HelloWorldControllerTest {

    @Autowired
    private HelloWorldController controller;

    @MockBean
    private HelloWorldService service;

    @Test
    public void testHello() {
        when(service.appendWorld()).thenReturn("world");
        assertEquals("hello world", controller.hello());
    }
}


