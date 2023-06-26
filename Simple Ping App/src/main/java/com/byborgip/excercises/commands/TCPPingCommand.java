package com.byborgip.excercises.commands;

import com.byborgip.excercises.exceptions.RuntimeTechnicalException;
import com.byborgip.excercises.model.Host;
import com.byborgip.excercises.utils.URLUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.*;
import java.time.LocalDateTime;

public class TCPPingCommand extends PingCommand{

    private static final TCPPingCommand instance = new TCPPingCommand();

    protected TCPPingCommand() {
        super();
    }

    @Override
    protected void ping(Host host) {

        try {

            long startTime = System.currentTimeMillis();

            URL url = new URL(URLUtils.normaliseHostName(host.getName()));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(host.getTcpConnectionTimeOut());

            int responseCode = connection.getResponseCode();

            long endTime = System.currentTimeMillis();

            long executionTime = endTime - startTime;

            connection.disconnect();

            TCPPingCommandResult result = TCPPingCommandResult.create(LocalDateTime.now(), executionTime, responseCode);

            host.getCommandResults().setLastTCPPingResult(result);

        }catch(SocketTimeoutException | UnknownHostException e) {
            report(host);
        }
        catch (Exception exception){
            throw new RuntimeTechnicalException(exception);
        }

    }



    public static TCPPingCommand getInstance(){
        return instance;
    }

}
