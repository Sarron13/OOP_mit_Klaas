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
    public void create(@Context UriInfo uriInfo, Character character);

    @GET
    @Path("/{charid}")
    public Character read(@PathParam("charid") UUID id);

    @GET
    public Collection<Character> getAll();

    @PUT
    @Path("/{charid}")
    public void update(Character c);

    @DELETE
    @Path("/{charid}")
    public void delete(@PathParam("charid") UUID id);

    @GET
    @Path("/search")
    public Collection<Character> search(@QueryParam("query") String query);
}
