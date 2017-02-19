package util;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * A Singleton Object representing our Google Client JSON properties.
 *
 * Created by JSim on 2/19/17.
 */
public class ClientProperties {
    private static ClientProperties ourInstance = null;

    public static ClientProperties getInstance() {
        return (ourInstance != null) ? ourInstance : getProperties();
    }

    // File Path
    private static String filePath = "~/res/properties.json";

    // JSON Variables
    private static String clientId;
    private static String clientSecret;

    private ClientProperties() {}

    private static ClientProperties getProperties() {
        try {
            File propertiesFile = new File(filePath);

            if (propertiesFile == null) {
                throw new FileNotFoundException();
            }

            Gson gson = new Gson();
            String json = gson.toJson(propertiesFile);
            ourInstance = gson.fromJson(json, ClientProperties.class);

            return ourInstance;
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.out.println(fileNotFoundException);
            System.exit(-1);
        }

        return null;
    }
}
