import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { CadastroClienteComponent } from '../cadastro-cliente/cadastro-cliente.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, HttpClientModule, CommonModule, CadastroClienteComponent],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent { 
  email = '';
  senha = '';
    
  constructor(private authService: AuthService, private router: Router) {}
  modalAberto: boolean = false;

  entrar() {
    this.authService.login(this.email, this.senha).subscribe(
      response => {
        console.log('Login bem-sucedido:', response);
        this.router.navigate(['/portal']);
      },
      error => {
        console.error('Erro no login:', error);
      }
    );
  }
   cadastrarCliente(cliente: {nome:string, email:string, senha:string, telefone:string}) {
    this.authService.cadastrar(cliente).subscribe(
      response => {
        console.log('Cadastro bem-sucedido:', response);
        alert('Cadastro bem-sucedido! Agora você pode fazer login.');
      },
      error => {
        console.error('Erro no cadastro:', error);
        alert('Erro ao cadastrar. Tente novamente.');
      }
    );
  } 
}