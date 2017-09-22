package com.example.helloword.db;

import com.example.helloword.core.Person;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

/**
 * Created by zhoubo on 9/22/2017.
 */
public class PersonDAO extends AbstractDAO<Person>{
    public PersonDAO(SessionFactory factory){
        super(factory);
    }
    public Optional<Person> findById(Long id){
            return Optional.ofNullable(get(id));
    }

    public Person create(Person person){
        return persist(person);
    }

    public List<Person> findAll(){
        return list(namedQuery("com.example.helloword.core.Person.findAll"));
    }
}
