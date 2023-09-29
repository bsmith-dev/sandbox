package sandpox.aspects;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;

public class HelloWorldAspectTest {

    @Test
    public void testAround() throws Throwable {
        HelloWorldAspect aspect = new HelloWorldAspect();
        ProceedingJoinPoint joinPoint = mock(ProceedingJoinPoint.class);
        when(joinPoint.proceed()).thenReturn("world");
        assertEquals("hello world", aspect.aroundAdvice(joinPoint));
    }
}
