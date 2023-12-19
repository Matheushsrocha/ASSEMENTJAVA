import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class UsuarioApiTest {

    @Test
    public void testUserInsertion() throws IOException {

        URL randomUserApiUrl = new URL("https://randomuser.me/api/");
        HttpURLConnection randomUserConnection = (HttpURLConnection) randomUserApiUrl.openConnection();
        randomUserConnection.setRequestMethod("GET");

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode randomUserResponse = objectMapper.readTree(randomUserConnection.getInputStream());


        String userName = randomUserResponse.path("results").path(0).path("name").path("first").asText();
        String userEmail = randomUserResponse.path("results").path(0).path("email").asText();


        URL yourApiUrl = new URL("http://localhost:4567/usuarios");
        HttpURLConnection yourApiConnection = (HttpURLConnection) yourApiUrl.openConnection();
        yourApiConnection.setRequestMethod("POST");
        yourApiConnection.setRequestProperty("Content-Type", "application/json");
        yourApiConnection.setDoOutput(true);


        String userJson = "{ \"name\": \"" + userName + "\", \"email\": \"" + userEmail + "\" }";


        yourApiConnection.getOutputStream().write(userJson.getBytes());


        int responseCode = yourApiConnection.getResponseCode();
        assertEquals(201, responseCode);
    }
}
