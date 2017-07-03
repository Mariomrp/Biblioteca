import { Injectable } from '@angular/core';
import { Usuario } from "app/usuario";

@Injectable()
export class UsuarioService {
  usuarios: Usuario[] = [
    {matricula:"1",nome:"Mário",email:"mario@email.com",telefone:"987654321"},
    {matricula:"2",nome:"Mateus",email:"mateus@email.com",telefone:"123456789"},
    {matricula:"3",nome:"Fulano",email:"fulano@email.com",telefone:"246813579"}
  ];

  constructor() { }

getListaUsuarios() {
  return this.usuarios;
}

addUsuario(usuario:Usuario) {
  this.usuarios.push(usuario);
}

removerUsuario(indice:number){
    this.usuarios.splice(indice,1);    
  }

getUsuario(indice:number){
    return this.usuarios[indice];
  }

atualizaUsuario(indice:number, usuario:Usuario){
    this.usuarios[indice] = usuario;
  }
}
