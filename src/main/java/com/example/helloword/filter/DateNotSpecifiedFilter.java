package com.example.helloword.filter;

import org.eclipse.jetty.http.HttpHeader;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by zhoubo on 9/25/2017.
 */
public class DateNotSpecifiedFilter implements ContainerRequestFilter{
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        final  String dataHeader = containerRequestContext.getHeaderString(HttpHeaders.DATE);
        if(dataHeader == null){
            throw new WebApplicationException(new IllegalAccessException(""), Response.Status.BAD_REQUEST);
        }
    }
}
