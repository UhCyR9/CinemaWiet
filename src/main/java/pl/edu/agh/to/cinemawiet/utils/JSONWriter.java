package pl.edu.agh.to.cinemawiet.utils;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;


@Component
public class JSONWriter {

    private final ObjectMapper objectMapper;

    public JSONWriter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void write(Map<String, Object> map, long screeningId) throws IOException {
        String path = "/screenings/" + screeningId + ".json";
        String file = Objects.requireNonNull(getClass().getResource(path)).getFile();
        String json = objectMapper.writeValueAsString(map);
        try (FileWriter fileToWrite = new FileWriter(file)) {
            fileToWrite.write(json);
        }
    }
}
