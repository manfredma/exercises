package model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonPropertyOrder({"properties", "name", "id"})
@JsonRootName(value = "customer")
public class AnnotatedCustomer {

    public int id;

    public String name;
    private Map<String, String> properties = new HashMap<>();

    @JsonRawValue
    public String json;

    public TypeEnumWithValue type = TypeEnumWithValue.TYPE2;

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date eventDate = new Date();

    public Date birthDay = new Date();

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    @JsonGetter("theName")
    public String getName() {
        return name;
    }

    public enum TypeEnumWithValue {
        TYPE1(1, "Type A"), TYPE2(2, "Type 2");

        private Integer id;
        private String name;

        TypeEnumWithValue(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @JsonValue
        public String getName() {
            return name;
        }
    }


}
