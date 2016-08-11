package com.xyz.rest.resources;

import java.util.Arrays;
import java.util.List;

import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/admins")
@Produces("application/json")
@Consumes("application/json")
public class AdminEndpoint {

    @GET
    public Response getAdmins() {

        if (true) {
            throw new ValidationException("I want to inform user about Bad Request. This message should not be visible.");
        }

        @SuppressWarnings("unused")
        List<String> admins = Arrays.asList("John", "Jane");
        return Response.status(Status.OK).entity(admins).build();
    }

}