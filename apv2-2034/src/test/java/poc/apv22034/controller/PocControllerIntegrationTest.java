package poc.apv22034.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class PocControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCallWebClient() {
        String token = "Bearer aaJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.S5CmpNYnebDC8AoIK9RzLHafUdlWkb5KQ79lDF1J0io";
        webTestClient.get()
                .uri("/poc/")
                .exchange()
                .expectHeader().valueEquals("Authorization", token)
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("7 POC executed successfully!");
    }
}
