package com.example.helloword;

import com.example.helloword.core.Template;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.google.common.collect.ImmutableMap;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Map;

/**
 * Created by zhoubowen on 9/20/2017.
 */
public class HelloWorldConfiguration extends Configuration {

    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName="Stranger";

    @Valid
    @NotNull
    private DataSourceFactory database=new DataSourceFactory();

    @NotNull
    private Map<String,Map<String,String>> viewRendererConfiguration = Collections.emptyMap();

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public Template buildTemplate(){
        return new Template(template,defaultName);
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("viewRendererConfiguration")
    public Map<String, Map<String, String>> getViewRendererConfiguration() {
        return viewRendererConfiguration;
    }

    @JsonProperty("database")
    public void setDatabase(DataSourceFactory database) {
        this.database = database;
    }

    @JsonProperty("viewRendererConfiguration")
    public void setViewRendererConfiguration(Map<String, Map<String, String>> viewRendererConfiguration) {
        final ImmutableMap.Builder<String,Map<String,String>> builder = new ImmutableMap.Builder();
        for(Map.Entry<String,Map<String,String>> entry : viewRendererConfiguration.entrySet()){
            builder.put(entry.getKey(),ImmutableMap.copyOf(entry.getValue()));
        }
        this.viewRendererConfiguration=builder.build();
    }


}
