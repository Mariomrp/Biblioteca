// criação das rotas do projeto
import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

import { TabelaUsuariosComponent } from './tabela-usuarios/tabela-usuarios.component';
import { FormularioUsuariosComponent } from './formulario-usuarios/formulario-usuarios.component';
import { LivrosComponent } from './livros/livros.component';
import { AutorComponent } from './autor/autor.component';

const APP_ROUTES: Routes = [
    {path: 'usuarios', component: TabelaUsuariosComponent },
    {path: 'livros', component: LivrosComponent },
    {path: 'autor', component: AutorComponent },
    {path: 'formulario', component: FormularioUsuariosComponent },
];

export const rotas: ModuleWithProviders = RouterModule.forRoot(APP_ROUTES);