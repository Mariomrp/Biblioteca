import { Injectable } from '@angular/core';
import { Livro } from "app/livro";

@Injectable()
export class LivroService {
  livros: Livro[] = [
    {isbn:"85-359-0277-5", nome:"Nome do livro", autor:"autor(es)", editora:"Companhia das Letras", anopublicacao:"2016"},
    {isbn:"85-123-3783-2", nome:"Nome do livro", autor:"autor(es)", editora:"Saraiva", anopublicacao:"2013"},
    {isbn:"85-432-4234-8", nome:"Nome do livro", autor:"autor(es)", editora:"Novatec", anopublicacao:"2015"}
  ];

  constructor() { }

getListaLivros() {
  return this.livros;
}

addLivro(livro:Livro) {
  this.livros.push(livro);
}

removeLivro(livro:Livro) {

}

alteraLivro(livro:Livro) {
  
}
}
