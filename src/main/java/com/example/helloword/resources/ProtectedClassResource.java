package com.example.helloword.resources;

import com.example.helloword.core.User;
import io.dropwizard.auth.Auth;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * Created by zhoubo on 9/28/2017.
 */
@Path("/protected")
@RolesAllowed("BASIC_GUY")
public class ProtectedClassResource {

    @GET
    @PermitAll
    @Path("guest")
    public String showSecret(@Auth User user){
        return String.format("Hey there, %s. You know the secret! %d",user.getName(),user.getId());
    }

    /* Access to this method is authorized by the class level annotation */
    @GET
    public String showBasicUserSecret(@Context SecurityContext context){
        User user = (User) context.getUserPrincipal();
        return String.format("Hey there, %s. You seem to be a basic user. %d",user.getName(),user.getId());
    }
    @GET
    @RolesAllowed("ADMIN")
    @Path("admin")
    public String showAdminSecret(@Auth User user){
        return String.format("Hey there, %s. It looks like you are an admin. %d", user.getName(), user.getId());
    }

}
