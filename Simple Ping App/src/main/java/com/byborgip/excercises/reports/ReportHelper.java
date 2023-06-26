package com.byborgip.excercises.reports;

import com.byborgip.excercises.exceptions.RuntimeTechnicalException;
import com.byborgip.excercises.model.Host;
import com.byborgip.excercises.utils.URLUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReportHelper {
    private Logger LOGGER= LoggerFactory.getLogger(ReportHelper.class);
    private final Host host;
    private class ReportInfo{
        @SerializedName("host")
        private String hostName;

        @SerializedName("icmp_ping")
        private String icmpPingRes;

        @SerializedName("tcp_ping")
        private String tcpPingRes;

        @SerializedName("trace")
        private String tracePingRes;

        private ReportInfo(String hostName, String icmpPingRes, String tcpPingRes, String tracePingRes){
            this.hostName = hostName;
            this.icmpPingRes = icmpPingRes;
            this.tcpPingRes = tcpPingRes;
            this.tracePingRes = tracePingRes;
        }

    }

    private ReportHelper(Host host){
        this.host = host;
    }

    public static ReportHelper get(Host host){
        if(host==null)
            return null;
        return new ReportHelper(host);
    }

    public void report(){
        if(host == null || host.getUrl2Report() == null || host.getCommandResults() == null)
            return; //maybe thrown a custom exception

        String urlToReport = host.getUrl2Report();
        Host.HostCommandResult commandResult = host.getCommandResults();

        ReportInfo info = new ReportInfo(host.getName(),
                commandResult.getLastIcmpPingResult() != null ? commandResult.getLastIcmpPingResult().getResult() : StringUtils.EMPTY,
                commandResult.getLastTcpPingResult() != null ? commandResult.getLastTcpPingResult().toString() : StringUtils.EMPTY,
                commandResult.getLastTraceCommandResult() != null ? commandResult.getLastTraceCommandResult().getResult() : StringUtils.EMPTY);

        String jsonReport = new Gson().toJson(info);

        sendToUrl(urlToReport, jsonReport);
    }

    private String sendToUrl(String urlToReport, String jsonReport) {
        try {
            URL url = new URL(URLUtils.normaliseHostName(urlToReport));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.write(jsonReport.getBytes());
                outputStream.flush();
            }

            int responseCode = connection.getResponseCode();

            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            return StringUtils.join("Response Code: ",responseCode," response body: ",response.toString());

        } catch (IOException e) {
            LOGGER.error("error on send reporter to: "+urlToReport);
        }
        return StringUtils.EMPTY;
    }
}
