package com.example.helloword.core;

import java.util.Optional;

import static java.lang.String.format;

/**
 * Created by zhoubo on 9/22/2017.
 */
public class Template {
    private final String content;
    private final String defaultName;

    public Template(String content,String defaultName){
        this.content = content;
        this.defaultName =  defaultName;

    }

    public String render(Optional<String> name){

        return format(content,name.orElse(defaultName));
    }
}
