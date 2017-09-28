package com.example.helloword.resources;

import com.example.helloword.core.Person;
import com.example.helloword.db.PersonDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by zhoubo on 9/28/2017.
 */
@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResource {
    private final PersonDAO peopleDAO;

    public PeopleResource(PersonDAO personDAO){

        this.peopleDAO = personDAO;
    }

    @POST
    @UnitOfWork
    public Person createPerson(Person person){
        return peopleDAO.create(person);
    }

    @GET
    @UnitOfWork
    public List<Person> listPeople(){
        return peopleDAO.findAll();
    }
}
