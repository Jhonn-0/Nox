import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

interface Medico {
  nome: string;
  especialidade: string;
  foto: string;
}

@Component({
  selector: 'app-consulta',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.css']
})
export class ConsultaComponent {
  especialidades = ['Cardiologia', 'Dermatologia', 'Pediatria', 'Ortopedia'];
  especialidadeSelecionada = '';
  dataSelecionada: string = '';

  medicos: Medico[] = [
    { nome: 'Dr. João', especialidade: 'Cardiologia', foto: 'assets/Img/teste.jpg' },
    { nome: 'Dra. Maria', especialidade: 'Dermatologia', foto: 'assets/Img/teste.jpg' },
    { nome: 'Dr. Pedro', especialidade: 'Pediatria', foto: 'assets/Img/teste.jpg' },
    { nome: 'Dra. Ana', especialidade: 'Ortopedia', foto: 'assets/Img/teste.jpg' },
    { nome: 'Dr. Carlos', especialidade: 'Cardiologia', foto: 'assets/Img/teste.jpg' },
    
  ];

  cardCentral = 0;

  get medicosFiltrados() {
    return this.medicos.filter(m =>
      !this.especialidadeSelecionada || m.especialidade === this.especialidadeSelecionada
    );
  }

  
moverDireita() {
  if(this.medicosFiltrados.length === 0) return;
  this.cardCentral = (this.cardCentral + 1) % this.medicosFiltrados.length;
}

moverEsquerda() {
  if(this.medicosFiltrados.length === 0) return;
  this.cardCentral =
    (this.cardCentral - 1 + this.medicosFiltrados.length) % this.medicosFiltrados.length;
}

getCardTransform(index: number): string {
  const offset = index - this.cardCentral;
  const zIndex = this.medicosFiltrados.length - Math.abs(offset);
  const scale = index === this.cardCentral ? 1 : 0.8;
  const translateX = offset * 50; // distância horizontal entre os cards

  return `translateX(${translateX}px) scale(${scale})`;
}
}