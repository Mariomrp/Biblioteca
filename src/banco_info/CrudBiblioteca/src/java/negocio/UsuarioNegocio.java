/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.UsuarioDao;
import dao.UsuarioDaoBd;
import model.Usuario;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Mario
 */
@Stateless
public class UsuarioNegocio {

    UsuarioDao usuarioDao;

    public UsuarioNegocio() {
        usuarioDao = new UsuarioDaoBd();
    }

    public void inserir(Usuario usu) throws Exception {
        if (usu.getNome() == null || usu.getNome().isEmpty()) {
            throw new Exception("Usuario invalido!");
        }
        usuarioDao.salvar(usu);
    }

    public List<Usuario> listar() {
        return usuarioDao.listar();
    }

    public Usuario buscarPorId_Usuario(int id_usuario) {
        return usuarioDao.procurarPorId_Usuario(id_usuario);
    }

    public Usuario deletar(int id_usuario) throws Exception {
        Usuario usuario = usuarioDao.procurarPorId_Usuario(id_usuario);
        if (usuario == null) {
            throw new Exception("Usuario nao encontrado");
        }
        usuarioDao.deletar(id_usuario);
        return usuario;
    }

    public void atualizar(Usuario usu, int id_usuario) throws Exception {
        Usuario usuario = usuarioDao.procurarPorId_Usuario(id_usuario);
        if (usuario == null) {
            throw new Exception("Usuario nao encontrado");
        }
//        if (usu.getMatricula()!= null && !usu.getMatricula().isEmpty()) {
//            usuario.setMatricula(usu.getMatricula());
//        }
        if (usu.getNome() != null && !usu.getNome().isEmpty()) {
            usuario.setNome(usu.getNome());
        }
        if (usu.getEmail() != null && !usu.getEmail().isEmpty()) {
            usuario.setEmail(usu.getEmail());
        }
        if (usu.getTelefone() != null && !usu.getTelefone().isEmpty()) {
            usuario.setTelefone(usu.getTelefone());
        }
        usuarioDao.atualizar(usuario);
    }

}
