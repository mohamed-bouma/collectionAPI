package collectionapi.endpoint;

import collectionapi.dao.DAOfactory;
import collectionapi.metier.Produit;
import collectionapi.service.ProduitSearch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "produits")
@Path("/produits")
public class ProduitRessource {
    @GET
    @Operation(summary = "Liste des produits")
    public Response getAll() {
        ArrayList<Produit> produits = DAOfactory.getProduitDAO().getAll();
        return Response.ok(produits).build();
    }

    @GET
    @Consumes("application/json")
    @Path("/getLike")
    public Response getLike(ProduitSearch produitSearch) {
        if (produitSearch == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOfactory.getProduitDAO().getLike(produitSearch) != null)
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @POST
    @Consumes("application/json")
    @Path("/insert")
    public Response insert(Produit produit) {
        if (produit == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (DAOfactory.getProduitDAO().insert(produit))
            return Response.ok(produit).status(Response.Status.CREATED).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response Update(@PathParam("id") Integer id, Produit produit) {
        if (id == null || produit == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (id != produit.getId_produit()) {
            return Response.status(Response.Status.CONFLICT).entity(produit).build();
        }
        if (DAOfactory.getProduitDAO().update(produit))
            return Response.ok(produit).build();
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
        if (DAOfactory.getProduitDAO().delete(new Produit(id, "")))
            return Response.status(204).build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
