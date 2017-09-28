package com.example.helloword.resources;

import com.example.helloword.filter.DateRequired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by zhoubo on 9/28/2017.
 */
@Path("/filtered")
public class FilteredResource {
    @GET
    @DateRequired
    @Path("hello")
    public String sayHello(){
        return "hello";
    }
}
