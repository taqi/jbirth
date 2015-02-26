package com.keebraa.jbirth.rest;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.security.core.context.SecurityContextHolder;

import com.keebraa.jbirth.dao.cassandra.hector.CDBUserDAO;
import com.keebraa.jbirth.dao.objects.DBUser;
import com.keebraa.jbirth.rest.objects.RSUserInfo;
import com.keebraa.jbirth.services.AuthService;
import com.sun.jersey.api.client.ClientResponse.Status;

@Path("/auth")
public class AuthResource {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(RSUserInfo rsUserInfo, @Context HttpServletRequest request) throws URISyntaxException,
            UnsupportedEncodingException {
        DBUser user = CDBUserDAO.get().getUserByLogin(rsUserInfo.getLogin()); 
        if (user == null) {
            return Response.status(Status.FORBIDDEN).entity("wrong credentials").build();
        }
        if (!user.getPassword().equals(rsUserInfo.getPassword())) {
            return Response.status(Status.FORBIDDEN).entity("wrong credentials").build();
        }
        AuthService.authorizeUser(user);
        return Response.ok().build();
    }

    @GET
    @Path("/logout")
    public Response logout(@Context HttpServletRequest request) throws UnsupportedEncodingException, URISyntaxException {
        SecurityContextHolder.getContext().setAuthentication(null);
        request.getSession().invalidate();
        return Response.ok().build();
    }
}
