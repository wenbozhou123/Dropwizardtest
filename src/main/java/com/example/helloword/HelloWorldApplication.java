package com.example.helloword;

import com.example.helloword.auth.ExampleAuthenticator;
import com.example.helloword.auth.ExampleAuthorizer;
import com.example.helloword.cli.RenderCommand;
import com.example.helloword.core.Person;
import com.example.helloword.core.Template;
import com.example.helloword.core.User;
import com.example.helloword.db.PersonDAO;
import com.example.helloword.filter.DateRequiredFeature;
import com.example.helloword.health.TemplateHealthCheck;
import com.example.helloword.resources.*;
import com.example.helloword.tasks.EchoTask;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.views.ViewBundle;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

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
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {

        final PersonDAO dao = new PersonDAO(hibernateBundle.getSessionFactory());
        final Template template= configuration.buildTemplate();
        environment.healthChecks().register("template",new TemplateHealthCheck(template));
        environment.admin().addTask(new EchoTask());
        environment.jersey().register(DateRequiredFeature.class);
        environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                                    .setAuthenticator(new ExampleAuthenticator())
                                    .setAuthorizer(new ExampleAuthorizer())
                                    .setRealm("SUPER SECRET STUFF")
                                    .buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new HelloWorldResource(template));
        environment.jersey().register(new ViewResource());
        environment.jersey().register(new ProtectedResource());
        environment.jersey().register(new PeopleResource(dao));
        environment.jersey().register(new PersonResource(dao));
        environment.jersey().register(new FilteredResource());
    }
}
