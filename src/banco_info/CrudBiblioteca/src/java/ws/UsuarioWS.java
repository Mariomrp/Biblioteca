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
import model.Usuario;
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
import negocio.UsuarioNegocio;

/**
 * REST Web Service
 *
 * @author Mario
 */
@Path("usuarios")
public class UsuarioWS {

    @EJB
    private UsuarioNegocio usuarioService;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuarioWS
     */
    public UsuarioWS() {
    }

    /**
     * Retrieves representation of an instance of ws.UsuarioWS
     *
     * @param response
     * @return an instance of model.Usuario
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getListaUsuarios(@Context final HttpServletResponse response) {
        response.setHeader("Acess-Control-Allow-Origin", "*");
        return usuarioService.listar();
    }

    @GET
    @Path("{id_usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuarioPorId_Usuario(@PathParam("id_usuario") int id_usuario) {
        return (usuarioService.buscarPorId_Usuario(id_usuario));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionarUsuario(Usuario usu,
            @Context final HttpServletResponse response) throws IOException {
        try {
            usuarioService.inserir(usu);
            //Alterar o codigo para 201 (Created)
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.flushBuffer();
        } catch (Exception ex) {
            // Erro 500
            throw new IOException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{id_usuario}")
    public void deletarUsuario(@PathParam("id_usuario") int id_usuario) {
        try {
            usuarioService.deletar(id_usuario);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @PUT
    @Path("{id_usuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void alterarUsuario(Usuario usu, @PathParam("id_usuario") int id_usuario) {
        try {
            usuarioService.atualizar(usu, id_usuario);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
