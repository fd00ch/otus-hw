package ru.otus.serialization.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;

public class CsvSerializer implements Serializer {
    private static final String JSON_EXTENSION = ".csv";
    private final CsvMapper csvMapper = new CsvMapper();
    private final ObjectMapper jsonMapper = new ObjectMapper();

    public CsvSerializer() {
//        csvMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN,true);
    }

    @Override
    public String serialize(Object obj) throws JsonProcessingException {
        var json = jsonMapper.writeValueAsString(obj);

        JsonNode jsonTree = jsonMapper.readTree(json);

        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode firstObject = jsonTree.elements().next();
        firstObject.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn(fieldName);} );
        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();

        var res = csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValueAsString(jsonTree);

        return res;
//                .writeValue(new File("src/main/resources/orderLines.csv"), jsonTree);

//        return csvMapper.writerFor(JsonNode.class)
//                .with(csvSchema)
//                .writeValueAsString(obj);
//        var schema = csvMapper.schemaFor(obj.getClass());
//        var schema = csvMapper.typedSchemaFor(obj.getClass());
//        return csvMapper.writer(schema.withUseHeader(true)).writeValueAsString(obj);
    }

    @Override
    public void serialize(File file, Object obj) throws IOException {

    }

    @Override
    public <T> T deserialize(String serialized, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> T deserialize(File file, Class<T> clazz) throws IOException {
        return null;
    }
}
