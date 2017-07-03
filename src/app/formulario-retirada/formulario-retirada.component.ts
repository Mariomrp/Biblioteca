import { Component, OnInit } from '@angular/core';
import { Usuario } from 'app/usuario';
import { UsuarioService } from 'app/usuario.service';
import { Livro } from 'app/livro';
import { LivroService } from 'app/livro.service';

@Component({
  selector: 'app-formulario-retirada',
  templateUrl: './formulario-retirada.component.html',
  styleUrls: ['../css/formulario.component.css']
})
export class FormularioRetiradaComponent implements OnInit {
  usuarios: Usuario[];
  livros: Livro[];

  constructor(private servico_Usu: UsuarioService,
              private servico_Liv: LivroService
              ) { }

  ngOnInit() {
    this.usuarios = this.servico_Usu.getListaUsuarios(),
    this.livros = this.servico_Liv.getListaLivros()
  }

}
