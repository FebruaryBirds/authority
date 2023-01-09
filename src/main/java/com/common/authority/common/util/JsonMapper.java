package com.common.authority.common.util;


import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class JsonMapper {

    @Resource
    ObjectMapper objectMapper;

    public ObjectNode createObjectNode(){
        return new ObjectMapper().createObjectNode();
    }

    public JsonNode toJsonNode(String jsonString){
        try {
//            return ObjectMapperFactory.getInstance().readTree(jsonString);
            return objectMapper.readTree(jsonString);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public JsonNode toJsonNode(Object o){
        try {
            return objectMapper.readTree(toJSONString(o));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public String toJSONString(Object o){
        if(o==null){
            return null;
        }
        try {
            return objectMapper.writeValueAsString(o);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public byte[] toJSONBytes(Object o){
        if(o==null){
            return null;
        }
        try {
            return objectMapper.writeValueAsBytes(o);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public String toJSONStringNoSymbol(Object o){
        if(o==null){
            return null;
        }
        try {
            return this.toJsonNode(o).toString();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public String toJSONHasUnescaped(Object o){
        if(o==null){
            return null;
        }
        try {
            return new String(JsonStringEncoder.getInstance().quoteAsString(toJSONString(o)));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public String toPrettyJSONString(Object o){
        if(o==null){
            return null;
        }
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> toMap(Object o) {
        if (o == null) {
            return null;
        }
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(o), Map.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ObjectNode parseJSONObject(String jsonString) {
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            ObjectNode node = (ObjectNode) new ObjectMapper().readTree(jsonNode.toString());
            return node;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public <T> T parseObject(String jsonString , Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public <T,K> T parseObject(String jsonString , Class<T> clazz1, Class<K> clazz2) {
        try {
            return objectMapper.readValue(jsonString, getCollectionType(clazz1, clazz2));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> parseList(@NonNull String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, getCollectionType(List.class, clazz));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
