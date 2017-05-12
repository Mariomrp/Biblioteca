import { Component, OnInit } from '@angular/core';
import { Usuario } from "app/usuario";
import { UsuarioService } from "app/usuario.service";

@Component({
  selector: 'app-tabela-usuarios',
  templateUrl: './tabela-usuarios.component.html',
  styleUrls: ['./tabela-usuarios.component.css']
})
export class TabelaUsuariosComponent implements OnInit {
  usuarios: Usuario[];

  constructor(private servico: UsuarioService) { }

  ngOnInit() {
    this.usuarios = this.servico.getListaUsuarios()
  }

}
