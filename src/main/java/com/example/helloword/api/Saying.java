package com.example.helloword.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.Length;

/**
 * Created by zhoubo on 9/21/2017.
 */
public class Saying {

    private long id;

    private String content;

    public Saying(){

    }

    @Length(max = 3)
    public Saying(long id,String content){
        this.id = id;
        this.content = content;
    }
    @JsonProperty
    public long getId(){
        return this.id;
    }

    @JsonProperty
    public String getContent(){
        return this.content;
    }

    @Override
    public String toString(){
        return MoreObjects.toStringHelper(this)
                .add("id",this.id)
                .add("content",this.content)
                .toString();
    }
}
