package com.keebraa.jbirth.rest;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.keebraa.jbirth.rest.objects.RSMessage;

@Path("/hello")
public class HelloResource {
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sayhello() {
        return Response.ok().entity("hello world").build();
    }

    @GET
    @Path("/secured")
    @RolesAllowed("ROLE_USER")
    @Produces(MediaType.APPLICATION_JSON)
    public RSMessage sayhelloSecured() {
        RSMessage helloObject = new RSMessage();
        helloObject.setId("some_id");
        helloObject.setMessage("Hello world (SECURED)");
        return helloObject;
    }
}
