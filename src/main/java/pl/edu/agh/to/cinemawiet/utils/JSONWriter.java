package pl.edu.agh.to.cinemawiet.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;
import java.util.Objects;


@Component
public class JSONWriter {

    private final ObjectMapper objectMapper;

    private final JSONReader jsonReader;

    public JSONWriter(ObjectMapper objectMapper, JSONReader jsonReader) {
        this.objectMapper = objectMapper;
        this.jsonReader = jsonReader;
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

    public void writeToNewFile(long screeningId, long hallId) throws IOException {
        String path = "src/main/resources/screenings/" + screeningId + ".json";
        String targetFile = Objects.requireNonNull(getClass().getResource("/screenings")).getFile();

        String json = jsonReader.getString(hallId);

        File newFile = new File(path);
        newFile.createNewFile();
        PrintWriter writer = new PrintWriter(new FileWriter(path));
        writer.write(json);
        writer.close();

        File newTargetFile = new File(targetFile + "/" + screeningId + ".json");
        newTargetFile.createNewFile();
        PrintWriter targetWriter = new PrintWriter(new FileWriter(targetFile+ "/" + screeningId + ".json"));
        targetWriter.write(json);
        targetWriter.close();
    }
}
