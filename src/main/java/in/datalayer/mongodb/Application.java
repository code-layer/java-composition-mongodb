package in.datalayer.mongodb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import in.datalayer.mongodb.models.Entity;
import in.datalayer.mongodb.models.part.Book;
import in.datalayer.mongodb.models.part.Car;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws JsonProcessingException {
        mapper.registerModule(new JavaTimeModule());

        MongoClient mc = MongoConfig.getClient();
        MongoDatabase md = mc.getDatabase("mymongo");
        MongoCollection<Document> collection = md.getCollection("test");

        Integer _id1 = 5002;
        addEntity(collection, _id1,"Car");
        Integer _id2 = 5003;
        addEntity(collection, _id2,"Book");

        List<Integer> _idList = new ArrayList<>();
        _idList.add(_id1);
        _idList.add(_id2);
        readDocuments(collection,_idList);
    }

    public static void readDocuments(MongoCollection<Document> collection, List<Integer> _idList){
        Bson filter = Filters.in("_id",_idList);
        FindIterable<Document> fIterator = collection.find(filter);
        MongoCursor<Document> mCursor = fIterator.cursor();
        while(mCursor.hasNext()){
            Document document = mCursor.next();
            System.out.println("mongo document: " + document);
        }
    }

    public static void addEntity(MongoCollection<Document> collection,Integer _id, String entityPartType) throws JsonProcessingException {
        Entity e = new Entity();
        e.set_id(_id);
        e.setPartType(entityPartType);
        e.setVersion((byte)0);
        if(entityPartType.equals("Car")) {
            e.setName("Maruthi 800 Fx");
            Car c = prepareSampleCar();
            e.setCdata(c.toDocument());
        }
        else if(entityPartType.equals("Book")) {
            e.setName("Java Developer ");
            Book b = prepareSampleBook();
            e.setCdata(b.toDocument());
        }

        collection.insertOne(e.toDocument());
    }

    public static Book prepareSampleBook(){
        Book b = new Book();
        b.setCategory("Programming");
        b.setAuthor("Kathy Sierra");
        return b;
    }

    public static Car prepareSampleCar(){
        Car c = new Car();
        c.setManufacturer("Maruthi Suzuki");
        c.setModel("2022 Year");
        return c;
    }
}