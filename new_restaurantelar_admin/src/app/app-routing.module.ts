import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { UserComponent } from './pages/user/user.component';
import { UsuariosComponent } from './pages/usuarios/usuarios.component';

const routes: Routes = [

  { path: '', pathMatch: 'full', redirectTo: '/login' },
  { path: 'login', component: LoginComponent },
  
  {path: 'home', component: HomeComponent,
  children: [
    { path: 'home', redirectTo: 'home/user' },
    { path: 'user', component: UserComponent},
    { path: 'usuarios', component: UsuariosComponent},
    { path: 'register', component: RegisterComponent}

  ]}
];

  

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
