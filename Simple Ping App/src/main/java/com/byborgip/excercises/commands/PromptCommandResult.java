package com.byborgip.excercises.commands;

import com.byborgip.excercises.exceptions.InvalidAttemptToCreateResult;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class PromptCommandResult extends CommandResult{
    private final int exitCode;
    private final String result;

    @JsonCreator
    public PromptCommandResult(@JsonProperty("dateTime") LocalDateTime dateTime,
                               @JsonProperty("result") String result,
                               @JsonProperty("exitCode") int exitCode){
        super(dateTime);
        this.result = result;
        this.exitCode = exitCode;
    }
    public static PromptCommandResult create(LocalDateTime dateTime, String result, int exitCode){
        if(dateTime != null && result != null)
            return new PromptCommandResult(dateTime, result,exitCode);

        throw new InvalidAttemptToCreateResult();
    }

    public int getExitCode() {
        return exitCode;
    }

    public String getResult() {
        return result;
    }
}