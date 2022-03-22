package com.example.test;

import javax.ejb.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;

/**
 * Using a Singleton session bean to implement a RESTful resource. Since a Singleton session bean is being used to implement
 * the resource, the counter variable will be initialized only once and all clients requesting this resource will get the persisted
 * value for it across the other clients i.e. the same instance is being used to serve all clients. The result would be the same
 * for a Stateless session bean.
 * If a stateful session bean were being used to implement this resource, then the counter variable would be initialized for a
 * client each time the client requested for this resource, hence they would all get the value as 0.
 * That is, a new instance would be created for each new client request.
 */
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Singleton
@Path("/hello")
public class HelloResource {
    private int counter = 0;

    /**
     * The method called whenever a client makes a GET request to this resource. The @Lock(LockType.READ) annotation ensures
     * concurrent access by multiple clients to this resource.
     * @return The number of times this resource was accessed by clients.
     */
    @GET
    @Produces("text/plain")
    @Lock(LockType.READ)
    public String hello() {
        counter++;
        return "Hello, user! This resource has been accessed for " + counter + " time(s)";
    }

    /**
     * The method called whenever a client makes a POST request to set a custom value for the counter variable. The @Lock(LockType.WRITE)
     * annotation ensures that when a client accesses this method, other clients will be locked out of this bean. The @AccessTimeout(value = 60, unit = TimeUnit.SECONDS)
     * annotation ensures that when a client accesses this method, he can keep the bean access locked for at most 60 seconds.
     * To send a POST request from the client side to set the value of the counter variable, open your command prompt and use the CURL command as follows:
     * curl -X POST http://localhost:8080/Test-1.0-SNAPSHOT/api/hello/set/0
     * @param value The value a client wants to assign to the counter variable.
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/set/{newCounterValue}")
    @Lock(LockType.WRITE)
    @AccessTimeout(value = 60, unit = TimeUnit.SECONDS)
    public void setCounter(@PathParam("newCounterValue") String value)
    {
        this.counter = Integer.parseInt(value);
        System.out.println("The counter value has been set to " + counter);
    }
}