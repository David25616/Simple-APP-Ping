package com.byborgip.excercises.exceptions;

public class InvalidAttemptToCreateResult extends RuntimeException{
    public InvalidAttemptToCreateResult(){
        super("Can't create result with given inputs");
    }
}
