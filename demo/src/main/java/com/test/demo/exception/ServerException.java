package com.test.demo.exception;

public class ServerException extends Exception{

    public ServerException(final String description){
        super(String.format("Error in service server: %s", description));
    }

}
