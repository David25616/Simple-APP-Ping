package com.byborgip.excercises.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class JksonContextDB {

    static final Logger LOGGER = LoggerFactory.getLogger(JksonContextDB.class);
    static final String FILE_NAME = "app.ctx.json";
    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static{
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }
    public static ApplicationContext read() {
        try {
            return OBJECT_MAPPER.readValue(new File(FILE_NAME), ApplicationContext.class);
        } catch (IOException e) {
            LOGGER.error("can't read file app.ctx.json");
        }
        return null;
    }

    public static void write(ApplicationContext context){
        try {
            OBJECT_MAPPER.writeValue(new File(FILE_NAME), context);
        } catch (IOException e) {
            LOGGER.error("can't write to file app.ctx.json");
        }
    }
}
