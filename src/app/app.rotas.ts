// criação das rotas do projeto
import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

import { FormularioUsuariosComponent } from './formulario-usuarios/formulario-usuarios.component';

import { LivrosComponent } from './livros/livros.component';
import { FormularioLivroComponent } from './formulario-livro/formulario-livro.component';

import { AutorComponent } from './autor/autor.component';
import { FormularioAutorComponent } from './formulario-autor/formulario-autor.component';

import { FormularioRetiradaComponent } from "app/formulario-retirada/formulario-retirada.component";
import { UsuariosComponent } from "app/usuarios/usuarios.component";

const APP_ROUTES: Routes = [

    //path usuarios
    { path: 'usuarios', component: UsuariosComponent },
    { path: 'novousuario', component: FormularioUsuariosComponent },
    { path: 'edicaousuario/:ind', component: FormularioUsuariosComponent },
    //path livros
    { path: 'livros', component: LivrosComponent },
    { path: 'novolivro', component: FormularioLivroComponent },
    { path: 'edicaolivro/:ind', component: FormularioLivroComponent },
    //path autor
    { path: 'autor', component: AutorComponent },
    { path: 'novoautor', component: FormularioAutorComponent },
    { path: 'edicaoautor/:ind', component: FormularioAutorComponent },
    //path retirada
    { path: 'retirada', component: FormularioRetiradaComponent },

];

export const rotas: ModuleWithProviders = RouterModule.forRoot(APP_ROUTES);
