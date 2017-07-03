import { Component, OnInit } from '@angular/core';
import { LivroService } from "app/livro.service";
import { Livro } from "app/livro";
import { Router, ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-formulario-livro',
  templateUrl: './formulario-livro.component.html',
  styleUrls: ['../css/formulario.component.css']
})

export class FormularioLivroComponent implements OnInit {
  titulo: string;
  livro: Livro;
  indice: number;

  constructor(
    private servico: LivroService,
    private rota: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.indice = +this.rota.snapshot.params['ind'];

    if (isNaN(this.indice)) {
      this.titulo = "Cadastro de Livro";
      this.livro = new Livro();
    }
    else {
      this.titulo = "Edicao de Livro";
      //Clonando o objeto da lista
      this.livro = Object.assign({},
        this.servico.getLivro(this.indice));
   }
 }

  salvar() {
    if (isNaN(this.indice)) {
      this.servico.addLivro(this.livro);
      this.livro = new Livro();
    }
    else {
      this.servico.atualizaLivro(this.indice, this.livro);
    }
    this.router.navigate(['/livros']);
  }

  cancelar() {
    this.router.navigate(['/livros']);
  }

}
