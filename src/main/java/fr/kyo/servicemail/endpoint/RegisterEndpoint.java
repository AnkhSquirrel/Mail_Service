package fr.kyo.servicemail.endpoint;

import fr.kyo.servicemail.dao.DAOFactory;
import fr.kyo.servicemail.entity.Email;
import fr.kyo.servicemail.security.MyApiKey;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static fr.kyo.servicemail.security.ApiKeyed.APIKEY;

@Path("/register")
@Tag(name = "register")
public class RegisterEndpoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register() {
        String apiKey = "";
        do {
            apiKey = MyApiKey.generateApiKey();
        } while (DAOFactory.getApiKeyDAO().exists(apiKey));
        DAOFactory.getApiKeyDAO().insert(apiKey);
        return Response.ok().header(APIKEY, apiKey).build();
    }

}
