package jm.task.core.jdbc.service.exceptions;

public class FileReaderException extends RuntimeException{
    public FileReaderException(String message) {
        super(message);
    }

    public FileReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
