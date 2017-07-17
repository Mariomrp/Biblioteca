package dao;

import model.Livro;
import java.util.List;

public interface LivroDao {

    public void salvar(Livro livro);

    public void deletar(Livro livro);

    public void deletar(int id_livro);

    public void atualizar(Livro livro);

    public List<Livro> listar();

    public Livro procurarPorId_Livro(int id_livro);

}
