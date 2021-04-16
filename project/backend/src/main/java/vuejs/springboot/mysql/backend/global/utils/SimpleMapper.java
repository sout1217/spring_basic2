package vuejs.springboot.mysql.backend.global.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Writer;

@Slf4j
public class SimpleMapper {

    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();

    public static String toJson(Object object) {
        try {
            return OBJECTMAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert object to JSON string", e);
            return null;
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return OBJECTMAPPER.readValue(json, clazz);
        } catch (IOException e) {
            log.error("Failed to convert string `" + json + "` class `" + clazz.getName() + "`", e);
            return null;
        }
    }

    public static void write(Writer writer, Object value) throws IOException {
        OBJECTMAPPER.writeValue(writer, value);
    }
}
