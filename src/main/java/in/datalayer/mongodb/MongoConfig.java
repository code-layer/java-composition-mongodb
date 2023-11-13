package in.datalayer.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoConfig {
    private static String uri = "mongodb://userid:password@3.7.104.192:27017/admin";

    public static MongoClient getClient(){
        MongoClient mc = MongoClients.create(uri);
        return mc;
    }

}