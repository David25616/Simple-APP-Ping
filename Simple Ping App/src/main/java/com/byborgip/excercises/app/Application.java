package com.byborgip.excercises.app;

import com.byborgip.excercises.gui.GUI;
import com.byborgip.excercises.services.HostService;
import org.apache.commons.lang3.StringUtils;

public class Application {

    public static Application APP;
    private ApplicationContext context;

    public Application(ApplicationContext context){
        this.context = context != null ? context :  new ApplicationContext();
        HostService.init(this.context.getHosts().size());
    }

    private void start(String... args){
        GUI.startApp(args);
    }

    public void save(){

        if(!StringUtils.equals("test", ApplicationContext.bundle.getString("app.scope")))
            JksonContextDB.write(context);

    }

    public static void main(String... args){

        ApplicationContext context = null;

        if(!StringUtils.equals("test", ApplicationContext.bundle.getString("app.scope")))
            context = JksonContextDB.read();

        APP = new Application(context);

        APP.start(args);

    }

    public ApplicationContext getContext() {
        return context;
    }

    public static void saveApp(){
        if(APP != null)
            APP.save();
    }
}
