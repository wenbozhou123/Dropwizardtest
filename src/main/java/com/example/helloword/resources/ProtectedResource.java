package com.example.helloword.resources;

import com.example.helloword.core.User;
import io.dropwizard.auth.Auth;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by zhoubo on 9/28/2017.
 */

@Path("protected")
@Produces(MediaType.TEXT_PLAIN)
public class ProtectedResource {
    @PermitAll
    @GET
    public String showSecret(@Auth User user){

        return String.format("Hey there, %s. You know the secret! %d",user.getName(),user.getId());
    }

    @RolesAllowed("ADMIN")
    @GET
    @Path("admin")
    public String showAdminSecret(@Auth User user){

        return String.format("Hey there, %s. It looks like you are an admin. %d",user.getName(),user.getId());
    }

}
