package pl.edu.agh.to.cinemawiet.utils;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;


@Component
public class JSONReader {


    private final JsonParser parser;

    public JSONReader() {
        this.parser = JsonParserFactory.getJsonParser();
    }

    public Map<String, Object> parse(long screeningId) throws FileNotFoundException {
        String path = "/screenings/" + screeningId + ".json";
        String file = Objects.requireNonNull(getClass().getResource(path)).getFile();
        String myJson = new Scanner(new File(file))
                .useDelimiter("\\Z").next();
        return parser.parseMap(myJson);
    }
}
