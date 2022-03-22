package com.example.test.ejb;

import javax.ejb.Local;
import javax.ejb.Stateful;

/**
 * An EJB that implements the WelcomeMsg interface.
 */
@Local (WelcomeMsg.class)
@Stateful
public class WelcomeMsgBean implements WelcomeMsg{
    /**
     *
     * @return A simple message stating which bean the message is coming from.
     */
    @Override
    public String returnWelcomeMsg() {
        return "This is a message from WelcomeMsgBean";
    }
}
