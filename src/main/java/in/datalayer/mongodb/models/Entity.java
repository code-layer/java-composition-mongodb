package in.datalayer.mongodb.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import in.datalayer.mongodb.Application;
import org.bson.Document;

import java.io.Serializable;

public class Entity implements Serializable {
    private Integer _id;
    private String name,partType;
    private Document cdata;
    private Byte version;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public Document getCdata() {
        return cdata;
    }

    public void setCdata(Document cdata) {
        this.cdata = cdata;
    }

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }


    public Document toDocument() throws JsonProcessingException {
        Document d = new Document();
        String json =  Application.mapper.writeValueAsString(this);
        d = Document.parse(json);
        return d;
    }
}
