package com.example.helloword.resources;

import com.example.helloword.core.Person;
import com.example.helloword.db.PersonDAO;
import com.example.helloword.views.PersonView;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by zhoubo on 9/28/2017.
 */
@Path("/people/{personId}")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
    private final PersonDAO peopleDAO;

    public  PersonResource(PersonDAO peopleDAO){
        this.peopleDAO = peopleDAO;
    }

    @GET
    @UnitOfWork
    public Person getPerson(@PathParam("personId") LongParam personId){
        return findSafely(personId.get());
    }

    @GET
    @Path("/view_freemarker")
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML)
    public PersonView getPersonViewFreemarker(@PathParam("personId") LongParam personId){
        return new PersonView(PersonView.Template.FREEMARKER,findSafely(personId.get()));
    }

    @GET
    @Path("/view_mustache")
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML)
    public PersonView getPersonViewMustache(@PathParam("personId") LongParam personId){
        return new PersonView(PersonView.Template.MUSTACHE,findSafely(personId.get()));
    }

    private Person findSafely(long personId){
        return peopleDAO.findById(personId).orElseThrow(() -> new NotFoundException("No such user"));
    }
}
