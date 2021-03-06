package com.example.helloword.cli;

import com.example.helloword.HelloWorldConfiguration;
import com.example.helloword.core.Template;
import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.Configuration;
import java.util.Optional;

/**
 * Created by zhoubo on 9/22/2017.
 */
public class RenderCommand extends ConfiguredCommand<HelloWorldConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RenderCommand.class);

    public RenderCommand() {
        super("render","Render the template data to console");
    }

    @Override
    public void configure(Subparser subparser){
        super.configure(subparser);
        subparser.addArgument("-i","--include-default")
                .action(Arguments.store())
                .dest("include-default")
                .help("Also render the template with the default name");
        subparser.addArgument("names").nargs("*");
    }
    @Override
    protected void run(Bootstrap<HelloWorldConfiguration> bootstrap,
                       Namespace namespace,
                       HelloWorldConfiguration configuration) throws Exception {

        final Template template  = configuration.buildTemplate();

        if(namespace.getBoolean("include-default")){
            LOGGER.info("DEFAULT => {}",template.render(Optional.empty()));
        }

        for(String name : namespace.<String>getList("names")){
            for(int i = 0; i<1000; i++){
                LOGGER.info("{} => {}",name,template.render(Optional.of(name)));
                Thread.sleep(1000);
            }
        }
    }
}
