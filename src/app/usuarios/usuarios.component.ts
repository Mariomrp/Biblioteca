import { Component, OnInit } from '@angular/core';
import { Usuario } from "app/usuario";
import { UsuarioService } from "app/usuario.service";

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['../css/html.component.css']
})
export class UsuariosComponent implements OnInit {
  usuarios: Usuario[];

  constructor(private servico: UsuarioService) { }

  ngOnInit() {
    this.usuarios = this.servico.getListaUsuarios()
  }

  removerUsuario(indice: number){
    this.servico.removerUsuario(indice);
  }
}
