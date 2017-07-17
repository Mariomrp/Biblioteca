package dao;

import model.Usuario;
import java.util.List;

public interface UsuarioDao {

    public void salvar(Usuario usuario);

    public void deletar(Usuario usuario);

    public void deletar(int id_usuario);

    public void atualizar(Usuario usuario);

    public List<Usuario> listar();

    public Usuario procurarPorId_Usuario(int id_usuario);

}
