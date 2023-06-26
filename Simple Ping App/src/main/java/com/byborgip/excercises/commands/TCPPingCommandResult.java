package com.byborgip.excercises.commands;

import com.byborgip.excercises.exceptions.InvalidAttemptToCreateResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TCPPingCommandResult extends CommandResult{

    private Duration responseTime;
    private int httpStatusCode;

    private TCPPingCommandResult(@JsonProperty("dateTime") LocalDateTime dateTime,
                                 @JsonProperty("responseTime") Duration responseTime,
                                 @JsonProperty("httpStatusCode") int httpStatusCode){
        super(dateTime);
        this.responseTime = responseTime;
        this.httpStatusCode = httpStatusCode;
    }
    public static TCPPingCommandResult create(LocalDateTime dateTime, long responseTime, int httpStatusCode){
        if(dateTime != null){
            return new TCPPingCommandResult(dateTime, Duration.ofMillis(responseTime), httpStatusCode);
        }


        throw new InvalidAttemptToCreateResult();
    }

    @Override
    public String toString(){
        return StringUtils.join( "TCP ping concluded on ",
                responseTime.getSeconds(),
                " seconds with http status code ",
                httpStatusCode);
    }

    public Duration getResponseTime() {
        return responseTime;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}