import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { MaterializeModule } from 'angular2-materialize';
import { TabelaUsuariosComponent } from './tabela-usuarios/tabela-usuarios.component';
import { FormularioUsuariosComponent } from './formulario-usuarios/formulario-usuarios.component';
import { UsuarioService } from './usuario.service';
import { rotas } from "app/app.rotas";
import { LivrosComponent } from './livros/livros.component';
import { AutorComponent } from './autor/autor.component';
import { AutorService } from "app/autor.service";
import { LivroService } from "app/livro.service";

@NgModule({
  declarations: [
    AppComponent,
    TabelaUsuariosComponent,
    FormularioUsuariosComponent,
    LivrosComponent,
    AutorComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    rotas
  ],
  providers: [
    UsuarioService,
    AutorService,
    LivroService
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
