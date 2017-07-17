/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.LivroDao;
import dao.LivroDaoBd;
import model.Livro;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Mario
 */
@Stateless
public class LivroNegocio {

    LivroDao livroDao;

    public LivroNegocio() {
        livroDao = new LivroDaoBd();
    }

    public void inserir(Livro liv) throws Exception {
        if (liv.getNome() == null || liv.getNome().isEmpty()) {
            throw new Exception("Livro invalido!");
        }
        livroDao.salvar(liv);
    }

    public List<Livro> listar() {
        return livroDao.listar();
    }

    public Livro buscarPorId_Livro(int id_livro) {
        return livroDao.procurarPorId_Livro(id_livro);
    }

    public void atualizar(Livro liv, int id_livro) throws Exception {
        Livro livro = livroDao.procurarPorId_Livro(id_livro);
        if (livro == null) {
            throw new Exception("Livro nao encontrado");
        }
        //    if (liv.getIsbn() != null && !liv.getIsbn().isEmpty()) {
        //        livro.setIsbn(liv.getIsbn());
        //    }
        if (liv.getNome() != null && !liv.getNome().isEmpty()) {
            livro.setNome(liv.getNome());
        }
        //    if (liv.getId_autor() != null && !liv.getId_autor().isEmpty()) {
        //        livro.setId_autor(liv.getId_autor());
        //    }
        if (liv.getEditora() != null && !liv.getEditora().isEmpty()) {
            livro.setEditora(liv.getEditora());
        }
        //    if (liv.getAnopublicacao()!= null && !liv.getAnopublicacao().isEmpty()) {
        //        livro.setAnopublicacao(liv.getAnopublicacao());
        //    }
        livroDao.atualizar(livro);
    }

    public Livro deletar(int id_livro) throws Exception {
        Livro livro = livroDao.procurarPorId_Livro(id_livro);
        if (livro == null) {
            throw new Exception("Livro nao encontrado");
        }
        livroDao.deletar(id_livro);
        return livro;
    }

}
