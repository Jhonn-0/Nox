import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConsultaComponent } from '../consulta/consulta.component'; // ajuste o caminho

@Component({
  selector: 'app-portal',
  standalone: true,
  imports: [CommonModule, ConsultaComponent], // ✅ importar aqui
  templateUrl: './portal.component.html',
  styleUrls: ['./portal.component.css']
})
export class PortalComponent {
  menuAberto = false;
  paginaAtual = 'consultas';

  toggleMenu() {
    this.menuAberto = !this.menuAberto;
  }

  abrirPagina(pagina: string) {
    this.paginaAtual = pagina;
  }
}