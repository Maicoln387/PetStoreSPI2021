// 1 - Pacote
package petstore;

// 2 - Bibliotecas

import org.testng.annotations.Test;
import sun.security.mscapi.CPublicKey;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;


// 3 - Classe

public class Pet {
    // 3.1 - Atributos
    String uri = "https://petstore.swagger.io/v2/pet"; // endere�o da entidade Pet


    // 3.2 - M�todos e Fun��es
    public String lerJson(String caminhoJson) throws IOException {

        return new String(Files.readAllBytes(Paths.get(caminhoJson)));

    }


    // Incluir - Create - Post
    @Test(priority = 1) // Identifica o m�todo ou fun��o como teste para o TestNG
    public void IncluirPet() throws IOException {
        String jsonBody = lerJson("db/pet1.json");

        // Sintaxe Gherkin
        // Dado - Quando - Ent�o
        // Given - When - Than

        given() // Dado
                .contentType("application/json") // comum em API REST - antigas era "text/xml
                .log().all()
                .body(jsonBody)
        .when() // Quando
                .post(uri)
        .then() // Ent�o
                .log().all()
                .statusCode(200)
                .body("name", is ("maicon"))
                .body("status", is("available"))
                .body("category.name", is ("abraco"))
                .body("tags.name", contains("pedro"))


        ;


    }
   @Test(priority = 2)
   public void consultarPet(){
        String petId = "2005199520";

        String token =
        given()
                .contentType("aplication/json")
                .log().all()
        .when()
                .get(uri + "/" + petId)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is ("maicon"))
                .body("category.name", is ("abraco"))
                .body("status", is("available"))
        .extract()
                .path("category.name")


        ;
        System.out.println("o token � "+ token);


   }
}







