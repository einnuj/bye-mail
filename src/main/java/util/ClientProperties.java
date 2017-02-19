package util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * A Singleton Object representing our Google Client JSON properties.
 *
 * Created by JSim on 2/19/17.
 */
public class ClientProperties {
    private static ClientProperties ourInstance = null;

    // File Path
    private static String filePath = "./res/properties.json";

    // JSON Variables
    private static String clientId;
    private static String clientSecret;

    private ClientProperties() {}

    private static ClientProperties getProperties() {
        try {

            JsonParser parser = new JsonParser();
            JsonObject jsonProperties = parser.parse(new BufferedReader(new FileReader(filePath))).getAsJsonObject();

            clientId = jsonProperties.get("clientId").getAsString();
            clientSecret = jsonProperties.get("clientSecret").getAsString();

            ourInstance = new ClientProperties();

            return ourInstance;
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException);
        }

        return null;
    }

    public static ClientProperties getInstance() {
        return (ourInstance != null) ? ourInstance : getProperties();
    }

    public static String getClientId() {
        return clientId;
    }

    public static String getClientSecret() {
        return clientSecret;
    }
}
