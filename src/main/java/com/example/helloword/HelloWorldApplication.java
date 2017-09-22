package com.example.helloword;

import com.example.helloword.cli.RenderCommand;
import com.example.helloword.core.Person;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.views.ViewBundle;

import java.util.Map;

/**
 * Created by zhoubo on 9/20/2017.
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration>{

    public static void main(String[] args) throws Exception{
        new HelloWorldApplication().run();
    }

    public final HibernateBundle<HelloWorldConfiguration> hibernateBundle =
            new HibernateBundle<HelloWorldConfiguration>(Person.class){
                @Override
                public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration){
                    return configuration.getDataSourceFactory();
                }
            };

    public String getName(){
        return "Hello world!";
    }
    public void initiallize(Bootstrap<HelloWorldConfiguration> bootstarp){
        //enable variable substitution with environment variables
        bootstarp.setConfigurationSourceProvider(new SubstitutingSourceProvider(
                bootstarp.getConfigurationSourceProvider(),
                new EnvironmentVariableSubstitutor(false)
                )
        );
        bootstarp.addCommand(new RenderCommand());
        bootstarp.addBundle(new AssetsBundle());
        bootstarp.addBundle(new MigrationsBundle<HelloWorldConfiguration>(){
            @Override
            public DataSourceFactory getDataSourceFactory(HelloWorldConfiguration configuration){
                return configuration.getDataSourceFactory();
            }
        });

        bootstarp.addBundle(hibernateBundle);
        bootstarp.addBundle(new ViewBundle<HelloWorldConfiguration>(){
            @Override
            public Map<String,Map<String,String>> getViewConfiguration(HelloWorldConfiguration configuration){
                return configuration.getViewRendererConfiguration();
            }
        });

    }

    @Override
    public void run(HelloWorldConfiguration helloWorldConfiguration, Environment environment) throws Exception {

        


    }
}
