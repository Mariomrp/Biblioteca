import { Injectable } from '@angular/core';
import { Livro } from "app/livro";

@Injectable()
export class LivroService {
  livros: Livro[] = [
    {isbn:"85-359-0277-5", nome:"Livro Um", autor:"Lya Luft", editora:"Companhia das Letras", anopublicacao:"2016"},
    {isbn:"85-123-3783-2", nome:"Livro Dois", autor:"Moacyr Scliar", editora:"Saraiva", anopublicacao:"2013"},
    {isbn:"85-432-4234-8", nome:"Livro Três", autor:"Mário Quintana", editora:"Novatec", anopublicacao:"2015"}
  ];

  constructor() { }

getListaLivros() {
  return this.livros;
}

addLivro(livro:Livro) {
  this.livros.push(livro);
}

removerLivro(indice:number){
    this.livros.splice(indice,1);    
  }

getLivro(indice:number){
    return this.livros[indice];
  }

atualizaLivro(indice:number, livro:Livro){
    this.livros[indice] = livro;
  }
}
