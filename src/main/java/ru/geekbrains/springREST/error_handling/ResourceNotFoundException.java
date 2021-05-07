package ru.geekbrains.springREST.error_handling;

import jdk.jshell.spi.ExecutionControl;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){
        super(message);
    }
}
