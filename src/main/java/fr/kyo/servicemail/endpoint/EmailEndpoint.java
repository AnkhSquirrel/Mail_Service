package fr.kyo.servicemail.endpoint;

import fr.kyo.servicemail.dao.DAOFactory;
import fr.kyo.servicemail.entity.Email;
import fr.kyo.servicemail.security.ApiKeyed;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static fr.kyo.servicemail.security.ApiKeyed.APIKEY;

@Path("/mail")
@Tag(name = "mail")
public class EmailEndpoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiKeyed
    public Response authorizeEmail(Email email) {
        try {
            //EmailResource.sendEmail(email);
            System.out.println(email.getBody());

            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(417).build();
        }
    }

}
