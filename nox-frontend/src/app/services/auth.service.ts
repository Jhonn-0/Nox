import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/pacientes';  // URL da API

  constructor(private http: HttpClient) { }

  login(email: string, senha: string): Observable<any> {
  return this.http.post(`${this.apiUrl}/login`, { email, senha });
  }

  cadastrar(cliente: { nome: string; email: string; senha: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/cadastro`, cliente);
  }

}
