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
public class Autor implements Serializable {

    private int id_autor;
    private String nome;
    private String origem;

    public Autor(int id_autor, String nome, String origem) {
        this.id_autor = id_autor;
        this.nome = nome;
        this.origem = origem;
    }

    public Autor() {
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

}
