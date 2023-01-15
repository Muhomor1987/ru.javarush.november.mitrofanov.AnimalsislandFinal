package util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import entities.ConstantsAnimals;

import java.io.File;
import java.io.IOException;

public class Mapper {
    ObjectMapper objectMapper  = new ObjectMapper(new JsonFactory());
    File settingsFile = new File("src/main/resources/settingsAnimal.json");

    public void writeSettingsFile(ConstantsAnimals constants) throws IOException {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(settingsFile,constants);
    }
}
