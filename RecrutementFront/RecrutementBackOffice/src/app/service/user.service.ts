import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiURL='http://localhost:8082/api/users';

  constructor(private http: HttpClient) { }
  addUSer(user:User):Observable<User>{
    return this.http.post<User>(this.apiURL,user);
  }
  getUserById(id:number):Observable<User>{
    return this.http.get<User>(`${this.apiURL}/${id}`);
  }
  deleteUser(id:number):Observable<any>{
    return this.http.delete(`${this.apiURL}/${id}`);
  }
  getAllUsers():Observable<User[]>{
    return this.http.get<User[]>(this.apiURL);
  }
  getAllRH():Observable<User[]>{
    return this.http.get<User[]>(this.apiURL+"/rh");
  }
}

  
  