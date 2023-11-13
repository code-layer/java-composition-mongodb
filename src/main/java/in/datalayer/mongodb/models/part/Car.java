package in.datalayer.mongodb.models.part;

import com.fasterxml.jackson.core.JsonProcessingException;
import in.datalayer.mongodb.Application;
import org.bson.Document;

import java.io.Serializable;

public class Car implements Serializable {
    private String model,manufacturer;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Document toDocument() throws JsonProcessingException {
        Document d = new Document();
        String json =  Application.mapper.writeValueAsString(this);
        d = Document.parse(json);
        return d;
    }

    public Car fromDocument(Document document) throws JsonProcessingException {
        String json =  document.toJson();
        Car c = Application.mapper.readValue(json,Car.class);
        return c;
    }

}
