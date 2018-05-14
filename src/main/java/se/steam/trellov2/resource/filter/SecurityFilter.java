package se.steam.trellov2.resource.filter;

import se.steam.trellov2.resource.mapper.Secured;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

import static java.util.Collections.singletonMap;
import static javax.ws.rs.Priorities.*;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static javax.ws.rs.core.Response.status;

@Secured
@Provider
@Priority(AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        if (!"pepparkaka".equalsIgnoreCase(request.getHeaderString("key")))
            request.abortWith(status(UNAUTHORIZED)
                    .entity(singletonMap("error", "Missing/Invalid api key"))
                    .build());
    }
}
