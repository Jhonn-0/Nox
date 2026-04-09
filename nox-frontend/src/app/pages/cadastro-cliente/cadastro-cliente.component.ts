import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-cadastro-cliente',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './cadastro-cliente.component.html',
  styleUrls: ['./cadastro-cliente.component.css']
})
export class CadastroClienteComponent {
  @Output() fechar = new EventEmitter<void>();
  @Output() cadastrar = new EventEmitter<{nome:string, email:string, senha:string, telefone:string}>();

  novoCliente = {
    nome: '',
    email: '',
    senha: '',
    telefone: '',
  };

  enviarCadastro() {
    const clienteParaEnviar = { ...this.novoCliente };
    this.cadastrar.emit(clienteParaEnviar);
    this.novoCliente = { nome: '', email: '', senha: '', telefone: '' };
    this.fechar.emit();
  }
}