import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from './usuario.model';

@Injectable({
  providedIn: 'root'
})

export class ConsumidorService {
  private CRUDApi = 'http://localhost:8093/app'
  private ConsumidorApi = 'http://localhost:8081/usuarios'

  constructor(private http: HttpClient) { }

  public getData(): Observable<any> {
    return this.http.get<any>(`${this.CRUDApi}/all`);
  }

  public lastUsers(): Observable<any> {
    return this.http.get<any>(this.ConsumidorApi);
  }

  public get(id: number): Observable<any> {
    return this.http.get<any>(`${this.CRUDApi}/${id}`);
  }

  public delete(id: number): Observable<any> {
    return this.http.delete(`${this.CRUDApi}/delete/${id}`);
  }

  public deleteAll(): Observable<any> {
    return this.http.delete(`${this.CRUDApi}/delete/all`);
  }

  public add(usuario:Usuario): Observable<any> {
    return this.http.post(`${this.CRUDApi}/add`, usuario);
  }

  public modify(id: number, usuario:Usuario): Observable<any> {
    return this.http.put(`${this.CRUDApi}/update/${id}`, usuario);
  }
  
}
