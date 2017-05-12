import { Injectable } from '@angular/core';
import { Usuario } from "app/usuario";

@Injectable()
export class UsuarioService {
  usuarios: Usuario[] = [
    {matricula:"001",nome:"Mário",email:"mario@email.com",telefone:"987654321"}
  ];

  constructor() { }

getListaUsuarios() {
  return this.usuarios;
}

addUsuario(usuario:Usuario) {
  this.usuarios.push(usuario);
}

removeUsuario(usuario:Usuario) {

}

alteraUsuario(usuario:Usuario) {
  
}
}

