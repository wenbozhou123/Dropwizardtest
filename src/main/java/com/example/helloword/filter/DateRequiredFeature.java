package com.example.helloword.filter;


import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

/**
 * Created by zhoubo on 9/25/2017.
 */
public class DateRequiredFeature implements DynamicFeature {


    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {

        if(resourceInfo.getResourceMethod().getAnnotation(DateRequired.class) != null){
            context.register(DateNotSpecifiedFilter.class);

        }
    }
}
