import { Component, OnInit } from '@angular/core';
import { Autor } from "app/autor";
import { AutorService } from "app/autor.service";

@Component({
  selector: 'app-autor',
  templateUrl: './autor.component.html',
  styleUrls: ['./autor.component.css']
})
export class AutorComponent implements OnInit {
  autores: Autor[];

constructor(private servico: AutorService) { }

ngOnInit() {
  this.autores = this.servico.getListaAutores()
}

removerAutor(indice: number){
    this.servico.removerAutor(indice);
  }
}
