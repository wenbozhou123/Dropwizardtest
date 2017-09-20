package com.example.helloword;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by zhoubo on 9/20/2017.
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration>{

    public static void main(String[] args) throws Exception{
        new HelloWorldApplication().run();
    }

    public String getName(){
        return "Heel world!";
    }
    public void initiallize(Bootstrap<HelloWorldConfiguration> bootstarp){


    }

    public void run(HelloWorldConfiguration helloWorldConfiguration, Environment environment) throws Exception {

    }
}
