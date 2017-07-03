import { Component, OnInit } from '@angular/core';
import { UsuarioService } from "app/usuario.service";
import { Usuario } from "app/usuario";
import { Router, ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-formulario-usuarios',
  templateUrl: './formulario-usuarios.component.html',
  styleUrls: ['../css/formulario.component.css']
})

export class FormularioUsuariosComponent implements OnInit {
  titulo: string;
  usuario: Usuario;
  indice: number;

  constructor(
    private servico: UsuarioService,
    private rota: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.indice = +this.rota.snapshot.params['ind'];

    if (isNaN(this.indice)) {
      this.titulo = "Cadastro de Usuario";
      this.usuario = new Usuario();
    }
    else {
      this.titulo = "Edicao de Usuario";
      //Clonando o objeto da lista
      this.usuario = Object.assign({},
        this.servico.getUsuario(this.indice));
    }
  }

  salvar() {
    if (isNaN(this.indice)) {
      this.servico.addUsuario(this.usuario);
      this.usuario = new Usuario();
    }
    else {
      this.servico.atualizaUsuario(this.indice, this.usuario);
    }
    this.router.navigate(['/usuarios']);
  }

  cancelar() {
    this.router.navigate(['/usuarios']);
  }
}
