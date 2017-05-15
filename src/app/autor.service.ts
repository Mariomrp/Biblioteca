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

removeAutor(autor:Autor) {

}

alteraAutor(autor:Autor) {
  
}
}
