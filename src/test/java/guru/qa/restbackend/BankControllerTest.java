package guru.qa.restbackend;

import guru.qa.restbackend.domain.ClientInfo;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static io.restassured.RestAssured.with;

@SpringBootTest
public class BankControllerTest {
    private RequestSpecification spec =
            with()
            .baseUri("http://localhost:8080")
            .basePath("/");

    @Test
    void controllerTest(){
        ClientInfo[] client = spec.get("client/getAll")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(ClientInfo[].class);
        Stream.of(client).filter(ClientInfo -> ClientInfo.getClientName().equals("Maksim"));

    }
}
