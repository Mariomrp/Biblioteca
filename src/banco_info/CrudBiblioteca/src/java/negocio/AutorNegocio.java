/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.AutorDao;
import dao.AutorDaoBd;
import model.Autor;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Mario
 */
@Stateless
public class AutorNegocio {

    AutorDao autorDao;

    public AutorNegocio() {
        autorDao = new AutorDaoBd();
    }

    public void inserir(Autor aut) throws Exception {
        if (aut.getNome() == null || aut.getNome().isEmpty()) {
            throw new Exception("Autor invalido!");
        }
        autorDao.salvar(aut);
    }

    public List<Autor> listar() {
        return autorDao.listar();
    }

    public Autor buscarPorId_Autor(int id_autor) {
        return autorDao.procurarPorId_Autor(id_autor);
    }

    public Autor deletar(int id_autor) throws Exception {
        Autor autor = autorDao.procurarPorId_Autor(id_autor);
        if (autor == null) {
            throw new Exception("Autor nao encontrado");
        }
        autorDao.deletar(id_autor);
        return autor;
    }

    public void atualizar(Autor aut, int id_autor) throws Exception {
        Autor autor = autorDao.procurarPorId_Autor(id_autor);
        if (autor == null) {
            throw new Exception("Autor nao encontrado");
        }
        if (aut.getNome() != null && !aut.getNome().isEmpty()) {
            autor.setNome(aut.getNome());
        }
        if (aut.getOrigem() != null && !aut.getOrigem().isEmpty()) {
            autor.setOrigem(aut.getOrigem());
        }
        autorDao.atualizar(autor);
    }

}
