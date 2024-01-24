package com.bookMyShow.BookMyShow.Exceptions;

public class UnAuthorized extends RuntimeException{
    public UnAuthorized(String msg) {
        super(msg);
    }
}
