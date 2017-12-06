/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.service;

import java.util.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.auth.basic.BasicCredentials;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.lifestyle.api.model.User;
import org.lifestyle.api.persistence.UserDAO;

/**
 *
 * @author Peter van Vliet
 */
@Singleton
public class AuthenticationService implements Authenticator<BasicCredentials, User>, Authorizer<User>
{
    private final UserDAO userDAO;
    
    @Inject
    public AuthenticationService(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException
    {
        User user = userDAO.getByEmailAddress(credentials.getUsername());
        //if (user != null && PasswordService.isExpectedPassword(credentials.getPassword().toCharArray(), user.getSalt(), user.getHash()))
        if (user != null && new String(user.getPassword()).equals(credentials.getPassword()))
        {
            return Optional.of(user);
        }
        
        return Optional.empty();
    }

    @Override
    public boolean authorize(User user, String roleName)
    {
        return user.hasRole(roleName);
    }
}
