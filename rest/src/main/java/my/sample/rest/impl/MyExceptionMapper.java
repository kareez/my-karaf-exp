package my.sample.rest.impl;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class MyExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        StringBuilder message = new StringBuilder();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            message.append(violation.getPropertyPath().toString()).append(":").append(violation.getMessage()).append("\n");
        }
        return Response.status(Response.Status.BAD_REQUEST).entity(message.toString()).build();
    }
}
