import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
// import { MaterializeModule } from 'angular2-materialize';
import { FormularioUsuariosComponent } from './formulario-usuarios/formulario-usuarios.component';
import { UsuarioService } from './usuario.service';
import { rotas } from "app/app.rotas";
import { LivrosComponent } from './livros/livros.component';
import { AutorComponent } from './autor/autor.component';
import { AutorService } from "app/autor.service";
import { LivroService } from "app/livro.service";
import { FormularioAutorComponent } from './formulario-autor/formulario-autor.component';
import { FormularioLivroComponent } from './formulario-livro/formulario-livro.component';
import { FormularioRetiradaComponent } from './formulario-retirada/formulario-retirada.component';
import { RetiradaService } from "app/retirada.service";
import { UsuariosComponent } from "app/usuarios/usuarios.component";

@NgModule({
  declarations: [
    AppComponent,
    UsuariosComponent,
    FormularioUsuariosComponent,
    LivrosComponent,
    AutorComponent,
    FormularioAutorComponent,
    FormularioLivroComponent,
    FormularioRetiradaComponent
  ],
  imports: [
    BrowserModule,
    BsDropdownModule.forRoot(),
    FormsModule,
    HttpModule,
    rotas,
    // MaterializeModule.forRoot()
  ],
  providers: [
    UsuarioService,
    AutorService,
    LivroService,
    RetiradaService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
