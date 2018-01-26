import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    private String url;
    private JsonNode responseJson;

    public JsonParser(String url) {
        this.url = url;
    }

    public void update() {
        responseJson = RestAssured.given()
                                  .contentType("application/json")
                                  .when()
                                  .get(url)
                                  .then()
                                  .extract().body().as(JsonNode.class);
    }

    public List<Coin> getCoins() {
        List<Coin> coins = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> nodes = new ArrayList<>();
        responseJson.get("coins").elements().forEachRemaining(nodes::add);
        nodes.forEach(JsonNode -> {
            try {
                coins.add(mapper.treeToValue(JsonNode, Coin.class));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return coins;
    }
}