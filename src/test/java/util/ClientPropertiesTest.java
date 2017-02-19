package util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.*;

/**
 * Test Class for ClientProperties.
 *
 * Created by JSim on 2/19/17.
 */
public class ClientPropertiesTest {

    String propertiesFilePath = "./res/properties.json";

    @Test
    public void testGetInstance() {
        assertNotNull(ClientProperties.getInstance());
    }

    @Test
    public void testGetClientId() throws FileNotFoundException {
        ClientProperties.getInstance();

        String clientId = ClientProperties.getClientId();
        JsonParser parser = new JsonParser();
        JsonObject jsonProperties = parser.parse(new BufferedReader(new FileReader(propertiesFilePath))).getAsJsonObject();

        assertNotEquals(clientId, "");
        assertEquals(clientId, jsonProperties.get("clientId").getAsString());
    }

    @Test
    public void testGetClientSecret() throws FileNotFoundException {
        ClientProperties.getInstance();

        String clientSecret = ClientProperties.getClientSecret();
        JsonParser parser = new JsonParser();
        JsonObject jsonProperties = parser.parse(new BufferedReader(new FileReader(propertiesFilePath))).getAsJsonObject();

        assertNotEquals(clientSecret, "");
        assertEquals(clientSecret, jsonProperties.get("clientSecret").getAsString());
    }
}
