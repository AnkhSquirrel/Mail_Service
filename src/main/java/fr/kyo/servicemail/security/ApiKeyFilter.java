package fr.kyo.servicemail.security;


import fr.kyo.servicemail.dao.DAOFactory;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;


@Provider
@ApiKeyed
@Priority(Priorities.AUTHENTICATION)
public class ApiKeyFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String apiKeyHeader = requestContext.getHeaderString(ApiKeyed.APIKEY);
        if (MyApiKey.isValide(apiKeyHeader)) {
            DAOFactory.getApiKeyDAO().addUse(apiKeyHeader);
        } else {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}