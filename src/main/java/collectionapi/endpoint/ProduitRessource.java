package collectionapi.endpoint;

import collectionapi.dao.DAOfactory;
import collectionapi.metier.Produit;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "produits")
@Path("/produits")

public class ProduitRessource {
    @GET
    @Operation(summary = "Liste des produits")
    public Response getAll(){
        ArrayList<Produit>produits= DAOfactory.getProduitDAO().getAll();
        return Response.ok(produits).build();
    }


}
