package in.datalayer.mongodb.models.part;

import com.fasterxml.jackson.core.JsonProcessingException;
import in.datalayer.mongodb.Application;
import org.bson.Document;

import java.io.Serializable;

public class Book implements Serializable {

    private String category,author;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Document toDocument() throws JsonProcessingException {
        Document d = new Document();
        String json =  Application.mapper.writeValueAsString(this);
        d = Document.parse(json);
        return d;
    }

    public Book fromDocument(Document document) throws JsonProcessingException {
        String json =  document.toJson();
        Book b = Application.mapper.readValue(json,Book.class);
        return b;
    }
}
