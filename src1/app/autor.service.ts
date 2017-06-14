import { Injectable } from '@angular/core';
import { Autor } from "app/autor";

@Injectable()
export class AutorService {
  autores: Autor[] = [
    {nome:"Moacyr Scliar",origem:"Brasil"},
    {nome:"Mário Quintana",origem:"Brasil"},
    {nome:"Lya Luft",origem:"Brasil"}
  ];

  constructor() { }

getListaAutores() {
  return this.autores;
}

addAutor(autor:Autor) {
  this.autores.push(autor);
}

removerAutor(indice:number) {
  this.autores.splice(indice,1);
}

getAutor(indice:number){
    return this.autores[indice];
  }

atualizaAutor(indice:number, autor:Autor){
    this.autores[indice] = autor;
  }
}
