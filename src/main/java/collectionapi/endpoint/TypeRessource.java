package collectionapi.endpoint;

import collectionapi.dao.DAOfactory;
import collectionapi.dao.TypeDAO;
import collectionapi.metier.Caracteristique;
import collectionapi.metier.Type;
import collectionapi.service.ProduitSearch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "types")
@Path("/types")
public class TypeRessource {
    @GET
    @Operation(summary = "Liste des types")
    public Response getAll() {
        ArrayList<Type> type = DAOfactory.getTypeDAO().getAll();
        return Response.ok(type).build();
    }

    @GET
    @Path("{id}/caracteristiques")
    public Response getCaracteristiquesTypes (Type type){
        ArrayList<Caracteristique> caracteristiques= TypeDAO.getCaracteristiquesTypes(type);
        return Response.ok(caracteristiques).build();
    }

    @POST
    @Consumes("application/json")
    @Path("/insert")
    public Response insert(Type type) {
        if (type == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOfactory.getTypeDAO().insert(type))
            return Response.ok(type).status(Response.Status.CREATED).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response Update(@PathParam("id") Integer id, Type type) {
        if (id == null || type == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (id != type.getId()) {
            return Response.status(Response.Status.CONFLICT).entity(type).build();
        }
        if (DAOfactory.getTypeDAO().update(type))
            return Response.ok(type).build();
        else
            return Response.status(Response.Status.CONFLICT).build();
    }

    @DELETE
    @Consumes("application/json")
    @Path("{id}")
    @ApiResponse(responseCode = "204", description = "supprimé !!!")
    @ApiResponse(responseCode = "400", description = "non trouvé !!!")
    public Response delete(@PathParam("id") Integer id) {
        if (id == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOfactory.getTypeDAO().delete(new Type(id, "")))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
