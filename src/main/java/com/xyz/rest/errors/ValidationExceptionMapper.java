package com.xyz.rest.errors;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.xyz.model.ResponseEntity;

/**
 * Unfortunately this {@code ValidationExceptionMapper} is never used in Jersey since Jersey 2.15.
 * 
 * @author Grzegorz Demecki
 * @since Aug 11, 2016
 */
@Provider
public class ValidationExceptionMapper implements
        ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {
        final ResponseEntity resp = ResponseEntity.builder()
                .message("Hurray, my custom ValidationExceptionMapper was called!")
                .build();

        return Response.status(Status.BAD_REQUEST).entity(resp).build();
    }
}