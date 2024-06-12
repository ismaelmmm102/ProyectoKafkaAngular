import { Component, OnInit } from '@angular/core';
import { ConsumidorService } from '../servicios/consumidor.service';
import { Usuario } from '../servicios/usuario.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})

export class HomeComponent implements OnInit{
  users: Usuario[] = [];
  lasts:Usuario[] = [];
  user: Usuario = { id: 1, nombre: '',edad: 0 };
  selectedUser: Usuario = { id: 0, nombre: '', edad: 0 };
  showModify: boolean = false;

  constructor(private consumidorService: ConsumidorService) { }

  ngOnInit(): void {
    this.fillData();
    this.fillKafka();
  }

  fillData(){
    this.consumidorService.getData().subscribe( data => {
      this.users = data;
    })
  }

  fillKafka(){
    this.consumidorService.lastUsers().subscribe( data => {
      this.lasts = data;
    })
  }

  deleteAllUsers(): void{
    this.consumidorService.deleteAll().subscribe(() => {
      this.users = [];
    });
    window.location.reload();
  }

  deleteUser(id: number): void{
    this.consumidorService.delete(id).subscribe(() => {
    this.users = this.users.filter(usuario => usuario.id !== id);});
    window.location.reload();
  }

  get isUserEmpty(): boolean {
  return this.users.length === 0;
  }

  get isUserNotEmpty(): boolean {
  return this.users.length !== 0;
  }

  get isLastEmpty(): boolean {
  return this.lasts.length === 0;
  }

  addUser(): void {
    this.consumidorService.add(this.user).subscribe(() => {
      this.user = { id:0, nombre: '' , edad:0};
    });
    window.location.reload();
  }

  selectUser(user: Usuario): void {
    this.selectedUser = user;
    this.showModify = true;
  }

  modifyUser(): void {
    this.consumidorService.modify(this.selectedUser.id, this.selectedUser).subscribe(() => {
      this.selectedUser = { id:0, nombre: '' , edad:0};
    });
    window.location.reload();
  }

  gotoModifyForm(): void {
    window.location.href = '#modifyForm';
  }
}