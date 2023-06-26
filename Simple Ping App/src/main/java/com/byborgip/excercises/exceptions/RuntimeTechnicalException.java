package com.byborgip.excercises.exceptions;

public class RuntimeTechnicalException extends RuntimeException{
    public RuntimeTechnicalException(Throwable exception){
        super("Technical Error", exception);
    }
}
