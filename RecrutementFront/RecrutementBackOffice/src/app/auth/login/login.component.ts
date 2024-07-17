import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{
  loginForm!:FormGroup;
  errorMessage!:string;
  constructor(private fb:FormBuilder,private authService:AuthService,private router:Router){}
  ngOnInit(): void {
    this.loginForm=this.fb.group({
      email:['',[Validators.required,Validators.email]],
      password:['',[Validators.required,Validators.minLength(6)]]
    });
  }
  onSubmit():void{
    if(this.loginForm.valid){
      const {email,password}=this.loginForm.value;
      this.authService.login(email,password).subscribe({
        next:(success)=>{
          if(success){
            this.router.navigate(['/dashboard']);
          }
        },
        error:(err)=>{
          this.errorMessage='Invalid email or password'
        }
      })
    }
  }

}
