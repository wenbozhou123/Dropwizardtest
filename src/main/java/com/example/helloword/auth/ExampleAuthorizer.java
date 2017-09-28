package com.example.helloword.auth;

import com.example.helloword.core.User;
import io.dropwizard.auth.Authorizer;

/**
 * Created by zhoubo on 9/28/2017.
 */
public class ExampleAuthorizer implements Authorizer<User> {
    @Override
    public boolean authorize(User user, String role) {
        return user.getRoles() != null && user.getRoles().contains(role);
    }
}
