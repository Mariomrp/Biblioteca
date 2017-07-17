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
import model.Livro;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import negocio.LivroNegocio;

/**
 * REST Web Service
 *
 * @author Mario
 */
@Path("livros")
public class LivroWS {

    @EJB
    private LivroNegocio livroService;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LivroWS
     */
    public LivroWS() {
    }

    /**
     * Retrieves representation of an instance of ws.LivroWS
     *
     * @param response
     * @return an instance of model.Livro
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Livro> getListaLivros(@Context final HttpServletResponse response) {
        response.setHeader("Acess-Control-Allow-Origin", "*");
        return livroService.listar();
    }
    
    @GET
    @Path("{id_livro}")
    @Produces(MediaType.APPLICATION_JSON)
    public Livro getLivroPorId_Livro(@PathParam("id_livro") int id_livro) {
        return (livroService.buscarPorId_Livro(id_livro));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionarLivro(Livro liv,
            @Context final HttpServletResponse response) throws IOException {
        try {
            livroService.inserir(liv);
            //Alterar o codigo para 201 (Created)
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.flushBuffer();
        } catch (Exception ex) {
            // Erro 500
            throw new IOException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{id_livro}")
    public void deletarLivro(@PathParam("id_livro") int id_livro) {
        try {
            livroService.deletar(id_livro);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PUT
    @Path("{id_livro}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alterarLivro(Livro liv, @PathParam("id_livro") int id_livro) {
        try {
            livroService.atualizar(liv, id_livro);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
