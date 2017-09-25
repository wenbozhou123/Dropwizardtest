package com.example.helloword.health;

import com.codahale.metrics.health.HealthCheck;
import com.example.helloword.core.Template;

import java.util.Optional;

/**
 * Created by zhoubo on 9/25/2017.
 */
public class TemplateHealthCheck extends HealthCheck{

    private final Template template;

    public TemplateHealthCheck(Template template){
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        template.render(Optional.of("woo"));
        template.render(Optional.empty());

        return Result.healthy();
    }
}
