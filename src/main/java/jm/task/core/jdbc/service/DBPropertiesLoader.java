package jm.task.core.jdbc.service;


import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;

public class DBPropertiesLoader {

    public Properties load(String fileName) throws IOException {
        Properties properties = new Properties();
        URL resource = Optional.ofNullable(DBPropertiesLoader.class.getClassLoader().getResource(fileName))
                .orElseThrow(() -> new IOException("Properties file not found"));

        String propertiesFilePath = resource.getFile();

        try (FileReader fileReader = new FileReader(propertiesFilePath)) {
            properties.load(fileReader);
        } catch (IOException e) {
            throw new IOException("Properties load exception", e);
        }

        return properties;
    }
}
