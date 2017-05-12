import { Component, OnInit } from '@angular/core';
import { UsuarioService } from "app/usuario.service";
import { Usuario } from "app/usuario";

@Component({
  selector: 'app-formulario-usuarios',
  templateUrl: './formulario-usuarios.component.html',
  styleUrls: ['./formulario-usuarios.component.css']
})
export class FormularioUsuariosComponent implements OnInit {
  usuario: Usuario;
  constructor(private servico: UsuarioService) { }

  ngOnInit() {
    this.usuario = new Usuario();
  }

  adicionarUsuario() {
    this.servico.addUsuario(this.usuario);
    this.usuario = new Usuario();
  }
}
