package com.scm.helper;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String userNotFound) {
        super(userNotFound);
    }
    public ResourceNotFoundException() {
        super("Resource not found");
    }

}
