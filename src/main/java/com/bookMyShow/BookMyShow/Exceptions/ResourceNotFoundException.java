package com.bookMyShow.BookMyShow.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
