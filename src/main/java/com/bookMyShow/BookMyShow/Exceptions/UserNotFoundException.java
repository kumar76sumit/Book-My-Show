package com.bookMyShow.BookMyShow.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
