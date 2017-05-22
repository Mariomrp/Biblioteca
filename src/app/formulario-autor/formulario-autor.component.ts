import { Component, OnInit } from '@angular/core';
import { AutorService } from "app/autor.service";
import { Autor } from "app/autor";
import { Router, ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-formulario-autor',
  templateUrl: './formulario-autor.component.html',
  styleUrls: ['./formulario-autor.component.css']
})

export class FormularioAutorComponent implements OnInit {
  titulo: string;
  autor: Autor;
  indice: number;

  constructor(private servico: AutorService, private rota: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.indice = +this.rota.snapshot.params['ind'];

    if (isNaN(this.indice)) {
      this.titulo = "Cadastro de Autor";
      this.autor = new Autor();
    }
    else {
      this.titulo = "Edicao de Autor";
      //Clonando o objeto da lista
      this.autor = Object.assign({},
        this.servico.getAutor(this.indice));
    }
  }

  salvar() {
    if (isNaN(this.indice)) {
      this.servico.addAutor(this.autor);
      this.autor = new Autor();
    }
    else {
      this.servico.atualizaAutor(this.indice, this.autor);
    }
    this.router.navigate(['/autor']);
  }

  cancelar() {
    this.router.navigate(['/autor']);
  }
}
