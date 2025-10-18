package com.wecp.progressive.exception;

public class TeamDoesNotExistException extends RuntimeException{
    public TeamDoesNotExistException(String msg){
        super(msg);
    }
}