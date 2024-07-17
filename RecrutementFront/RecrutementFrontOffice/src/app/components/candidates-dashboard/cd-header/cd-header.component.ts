import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
    selector: 'app-cd-header',
    templateUrl: './cd-header.component.html',
    styleUrls: ['./cd-header.component.scss']
})
export class CdHeaderComponent implements OnInit{
    currentUser!:User;

    constructor(private authService:AuthService) { }

    classApplied = false;
    toggleClass() {
        this.classApplied = !this.classApplied;
    }
    ngOnInit(): void {
        this.authService.getCurrentUser().subscribe({
            next: (data)=>{
                this.currentUser=data;
            },
            error: (error)=>console.error('Error fetching user data:', error)
        })
    }

    classApplied2 = false;
    toggleClass2() {
        this.classApplied2 = !this.classApplied2;
    }
    getImageUrl(filename:string):string{
        return `http://localhost:8082/api/files/get-image/${filename}`;
    }

}