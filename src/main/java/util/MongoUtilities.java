package util;

import com.mongodb.MongoNamespace;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.MongoClient;
import com.mongodb.async.client.MongoClients;
import com.mongodb.async.client.MongoCollection;
import com.mongodb.async.client.MongoDatabase;

/**
 * Created by JSim on 2/25/17.
 */
public class MongoUtilities {

    public static MongoClient mongoClient;
    public static MongoDatabase mongoDatabase;
    public static MongoCollection mongoCollection;

    /**
     * Attempt to connect to a MongoDB Database using a connection string specification.
     * @param connectionString
     *      - mongodb://[username:password@]host1[:port1][,host2[:port2],...[,hostN[:portN]]][/[database][?options]]
     */
    public static void connect(String connectionString) {
        if (null != mongoClient) {
            return;
        }

        if (connectionString.length() < 1) {
            connectionString = "mongodb://localhost";
        }

        try {
            mongoClient = MongoClients.create(connectionString);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Will attempt to get the hard-coded Database/Collections.
     *
     * NOTE: if they do not exist, they will be created upon first insertion.
     */
    private static void initialize() {
        if (null == mongoDatabase) {
            String databaseName = "test";

            // If invalid name, will throw IllegalArgumentException
            mongoDatabase = mongoClient.getDatabase("test");
        }

        if (null == mongoCollection) {
            String collectionName = "oAuthCredentials";

            // If invalid name, will throw IllegalArgumentException
            mongoCollection = mongoDatabase.getCollection(collectionName);
        }
    }

    /**
     * This is a required callback method according to MongoDB Documentation.
     */
    SingleResultCallback<Void> callbackWhenFinished = new SingleResultCallback<Void>() {
        @Override
        public void onResult(final Void result, final Throwable t) {
            System.out.println("Operation Finished!");
        }
    };
}
