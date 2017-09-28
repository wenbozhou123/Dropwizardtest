package com.example.helloword.views;

import com.example.helloword.core.Person;
import io.dropwizard.views.View;

/**
 * Created by zhoubo on 9/28/2017.
 */
public class PersonView extends View{

    private final Person person;

    public enum Template{
        FREEMARKER("freemarker/person.ftl"),
        MUSTACHE("mustache/person.mustache");

        private String templateName;
         Template(String templateName){
             this.templateName = templateName;
         }

         public String getTemplateName(){
             return templateName;
         }

    }

    public PersonView(PersonView.Template template, Person person){
        super(template.getTemplateName());
        this.person = person;
    }

    public Person getPerson(){
        return person;
    }



}
