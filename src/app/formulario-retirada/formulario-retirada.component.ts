import { Component, OnInit } from '@angular/core';
import { Usuario } from "app/usuario";
import { UsuarioService } from "app/usuario.service";
import { Livro } from 'app/livro';
import { LivroService } from 'app/livro.service';

@Component({
  selector: 'app-formulario-retirada',
  templateUrl: './formulario-retirada.component.html',
  styleUrls: ['./formulario-retirada.component.css']
})
export class FormularioRetiradaComponent implements OnInit {
  usuarios: Usuario[];
  livros: Livro[];

  constructor(private servico: UsuarioService,
              private servicol: LivroService) { }

  ngOnInit() {
    this.usuarios = this.servico.getListaUsuarios(),
    this.livros = this.servicol.getListaLivros()
  }

}
