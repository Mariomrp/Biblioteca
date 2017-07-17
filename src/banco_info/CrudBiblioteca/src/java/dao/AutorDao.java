package dao;

import model.Autor;
import java.util.List;

public interface AutorDao {

    public void salvar(Autor autor);

    public void deletar(Autor autor);

    public void deletar(int id_autor);

    public void atualizar(Autor autor);

    public List<Autor> listar();

    public Autor procurarPorId_Autor(int id_autor);

}
