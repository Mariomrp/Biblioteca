import { Injectable } from '@angular/core';
import { Usuario } from "app/usuario";

@Injectable()
export class UsuarioService {
  usuarios: Usuario[] = [
    {matricula:"01",nome:"Mário",email:"mario@email.com",telefone:"987654321"},
    {matricula:"02",nome:"Mateus",email:"mateus@email.com",telefone:"123456789"},
    {matricula:"03",nome:"Fulano",email:"fulano@email.com",telefone:"246813579"}
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
