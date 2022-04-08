package jm.task.core.jdbc.service.reader;


import jm.task.core.jdbc.service.exceptions.FileReaderException;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class ResourceFileReader implements Reader<List<String>>{

    private final Reader<List<String>> fileReader;

    public ResourceFileReader(String resourceFileName) {
        URL url = Optional.ofNullable(ResourceFileReader.class.getClassLoader().getResource(resourceFileName)).orElseThrow(() -> new IllegalArgumentException("Resource not found"));
        try {
            fileReader = new FileReader(Paths.get(url.toURI()));
        } catch (URISyntaxException e) {
            throw new FileReaderException(e.getMessage(), e);
        }
    }

    @Override
    public List<String> read() {
        return fileReader.read();
    }
}
