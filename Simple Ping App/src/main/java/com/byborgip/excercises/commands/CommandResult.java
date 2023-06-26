package com.byborgip.excercises.commands;

import java.time.LocalDateTime;

public abstract class CommandResult {
    private LocalDateTime dateTime;

    public CommandResult(LocalDateTime dateTime){
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime(){
        return dateTime;
    }
}
