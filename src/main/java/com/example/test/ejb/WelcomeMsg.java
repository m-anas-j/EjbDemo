package com.example.test.ejb;

import javax.ejb.Local;

/**
 * An interface that defines a single method, returnWelcomeMsg(). The @Local annotation ensures that the beans implementing this interface
 * must have local access.
 */
@Local
public interface WelcomeMsg {
    public String returnWelcomeMsg();
}