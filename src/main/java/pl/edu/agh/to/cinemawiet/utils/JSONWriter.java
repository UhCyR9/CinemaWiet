package pl.edu.agh.to.cinemawiet.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.*;
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
        String targetFile = Objects.requireNonNull(getClass().getResource(path)).getFile();
        String json = objectMapper.writeValueAsString(map);;

        PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources" + path));
        writer.write(json);
        writer.close();

        PrintWriter targetWriter = new PrintWriter(new FileWriter(targetFile));
        targetWriter.write(json);
        targetWriter.close();
    }
}
