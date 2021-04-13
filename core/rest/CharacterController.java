package core.rest;

import core.model.Character;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.UUID;

@Path("/characters")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface CharacterController {

    @POST
    public Response create(@Context UriInfo uriInfo, Character character);

    @GET
    @Path("/{charid}")
    public String read(@PathParam("charid") UUID id);

    @GET
    public String getAll(@QueryParam("maxnumber") @DefaultValue("50") int maxNumber);

    @PUT
    @Path("/{charid}")
    public void update(@PathParam("charid") UUID id, java.lang.Character c);

    @DELETE
    @Path("/{charid}")
    public void delete(@PathParam("charid") UUID id);

    @DELETE
    @Path("/search")
    public Collection<Character> search(@QueryParam("query") String query);
}
