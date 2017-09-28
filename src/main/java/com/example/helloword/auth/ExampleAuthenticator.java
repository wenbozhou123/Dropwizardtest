package com.example.helloword.auth;

import com.example.helloword.core.User;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Created by zhoubo on 9/27/2017.
 */
public class ExampleAuthenticator implements Authenticator<BasicCredentials,User>{

    private static final Map<String,Set<String>> VALID_USERS = ImmutableMap.of(
            "guest", ImmutableSet.of(),
            "good-guy",ImmutableSet.of("BASIC_GUY"),
            "chief-wizard",ImmutableSet.of("ADMIN","BASIC_GUY")
    );
    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if(VALID_USERS.containsKey(credentials.getUsername())&& "secret".equals(credentials.getPassword())){
            return Optional.of(new User(credentials.getUsername(),VALID_USERS.get(credentials.getUsername())));
        }
        return Optional.empty();
    }
}
