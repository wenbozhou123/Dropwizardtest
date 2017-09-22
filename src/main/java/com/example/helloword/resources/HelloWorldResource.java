package com.example.helloword.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.helloword.api.Saying;
import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zhoubo on 9/21/2017.
 */
public class HelloWorldResource {

    private final String template;

    private final String defaultName;

    private final AtomicLong counter;


    public HelloWorldResource(String template,String defaultName,AtomicLong counter) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = counter;
    }

    @GET
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name){

        final String value = String.format(template,name.or(defaultName));
        return new Saying(counter.incrementAndGet(),value);
    }

}
