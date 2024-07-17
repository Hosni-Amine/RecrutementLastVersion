import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  users!: User[]; 
  search: string = ''; 

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.loadUsers(); 
  }

  
  loadUsers() {
    this.userService.getAllUsers().subscribe(data => {
      this.users = data; 
    });
  }

  deleteUser(id: number | null) {
    if (id != null) {
      this.userService.deleteUser(id).subscribe(() => {
        this.loadUsers(); 
      });
    }
  }

  
  updateUser(id: number | null) {
    
  }

  
  gotoAddUser() {
    this.router.navigate(['/user/add']);
  }

 
  filterUsers() {

  }
}
