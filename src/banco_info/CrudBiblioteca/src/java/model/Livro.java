/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mario
 */
@XmlRootElement
public class Livro implements Serializable {

    private int id_livro;
    private int isbn;
    private String anopublicacao;
    private String nome;
    private String editora;
    private int id_autor;

    public Livro(int id_livro, int isbn, String nome, String editora, String anopublicacao, int id_autor) {
        this.id_livro = id_livro;                                                   
        this.isbn = isbn;
        this.nome = nome;
        this.anopublicacao = anopublicacao;
        this.editora = editora;
        this.id_autor = id_autor;
    }

    public Livro() {
    }

    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getAnopublicacao() {
        return anopublicacao;
    }

    public void setAnopublicacao(String anopublicacao) {
        this.anopublicacao = anopublicacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

}
