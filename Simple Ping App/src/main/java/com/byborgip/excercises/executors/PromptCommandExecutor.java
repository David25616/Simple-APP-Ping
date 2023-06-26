package com.byborgip.excercises.executors;

import com.byborgip.excercises.commands.PromptCommandResult;
import com.byborgip.excercises.exceptions.RuntimeTechnicalException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class PromptCommandExecutor implements IExecutor<PromptCommandResult, String>{

    private static final int SUCCESS_CODE = 0;
    private static final PromptCommandExecutor instance = new PromptCommandExecutor();
    public static PromptCommandExecutor getInstance(){
        return instance;
    }

    public PromptCommandResult execute(String command, Runnable execIfError){

        StringBuilder result = new StringBuilder();
        boolean isSuccess = false;

        int exitCode = -1;

        try{
            Process process = Runtime.getRuntime().exec(command);

            exitCode = process.waitFor();

            isSuccess = SUCCESS_CODE == exitCode;

            getResult(process, result);

        } catch (IOException | InterruptedException e) {
            isSuccess = false;
        }

        if(!isSuccess && execIfError != null)
            execIfError.run();

        return PromptCommandResult.create(LocalDateTime.now(),result.toString(), exitCode);

    }

    private void getResult(Process process, StringBuilder result)  {

        if(result == null || result == null)
            return;

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        try {
            line = reader.readLine();
            while (line != null) {

                result.append(line);

                if((line = reader.readLine()) != null)
                {
                    result.append(System.lineSeparator());
                }

            }
        } catch (IOException e) {
            throw new RuntimeTechnicalException(e);
        }

    }
}
