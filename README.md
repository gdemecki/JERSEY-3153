# JERSEY-3153 (#3425) bug showcase

After Jersey's migration from https://java.net, this bug is available as [Issue #3425](https://github.com/jersey/jersey/issues/3425).

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
* Use HK2 binding to override the problematic mapper:

        config.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(com.xyz.rest.errors.ValidationExceptionMapper.class).to(ExceptionMapper.class)
                        .in(Singleton.class);
            }
        });

