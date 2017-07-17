/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletResponse;
import model.Autor;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import negocio.AutorNegocio;

/**
 * REST Web Service
 *
 * @author Mario
 */
@Path("autor")
public class autorWS {

    @EJB
    private AutorNegocio autorService;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AutorWS
     */
    public autorWS() {
    }

    /**
     * Retrieves representation of an instance of ws.AutorWS
     *
     * @return an instance of model.Autor
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Autor> getListaAutores(@Context final HttpServletResponse response) {
        response.setHeader("Acess-Control-Allow-Origin", "*");
        return autorService.listar();
    }

    @GET
    @Path("{id_autor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Autor getAutorPorId_Autor(@PathParam("id_autor") int id_autor) {
        return autorService.buscarPorId_Autor(id_autor);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionarAutor(Autor aut,
            @Context final HttpServletResponse response) throws IOException {
        try {
            autorService.inserir(aut);
            //Alterar o codigo para 201 (Created)
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.flushBuffer();
        } catch (Exception ex) {
            // Erro 500
            throw new IOException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{id_autor}")
    public void deletarAutor(@PathParam("id_autor") int id_autor) {
        try {
            autorService.deletar(id_autor);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PUT
    @Path("{id_autor}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alterarAutor(Autor aut, @PathParam("id_autor") int id_autor) {
        try {
            autorService.atualizar(aut, id_autor);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
