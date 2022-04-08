package jm.task.core.jdbc.service.reader;

import jm.task.core.jdbc.service.exceptions.FileReaderException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader implements Reader<List<String>>{

    private final Path path;

    public FileReader(Path path) {
        this.path = path;
    }

    @Override
    public List<String> read() {
        try {
            return Files.lines(path).collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileReaderException(e.getMessage(), e);
        }
    }
}
