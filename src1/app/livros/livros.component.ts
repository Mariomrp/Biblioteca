import { Component, OnInit } from '@angular/core';
import { Livro } from 'app/livro';
import { LivroService } from 'app/livro.service';

@Component({
  selector: 'app-livros',
  templateUrl: './livros.component.html',
  styleUrls: ['./livros.component.css']
})
export class LivrosComponent implements OnInit {
  livros: Livro[];

  constructor(private servico: LivroService) { }

  ngOnInit() {
    this.livros = this.servico.getListaLivros()
  }

  removerLivro(indice: number){
    this.servico.removerLivro(indice);
  }
}
