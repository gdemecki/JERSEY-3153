# JERSEY-3153 bug showcase

## Description

This is a sample project which uses: Jersey, Weld, Jetty and jersey bean validation module.

The problem is, when I've upgraded Jersey from **2.14** to **2.23.1** my custom exception mapper:

```java
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
```

is no longer called - Jersey uses the built-in exception mapper for this exception: `org.glassfish.jersey.server.validation.internal.ValidationExceptionMapper` and unfortunately I cannot override it.

The problem is strictly related to the Weld integration. Because without dependency to Weld, above custom exception mapper has a higher priority than the one provided by **jersey-bean-validation** module.

## Known workarounds

* Downgrade to Jersey 2.14
* Remove Weld
* Remove jersey-bean-validation module

