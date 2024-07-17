import { Component, OnInit } from '@angular/core';
import { Profile } from 'src/app/models/profile';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { ProfileService } from 'src/app/services/profile.service';

@Component({
  selector: 'app-cd-profile',
  templateUrl: './cd-profile.component.html',
  styleUrls: ['./cd-profile.component.scss']
})
export class CdProfileComponent implements OnInit{
  currentUser!:User;
  currentUserProfile!:Profile;
  constructor(private authService:AuthService,
    private profileService:ProfileService){}
  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe({
            next: (data)=>{
                this.currentUser=data;
                const userId=localStorage.getItem('userId');
                this.profileService.afficherProfileParUserId(Number(userId)).subscribe((data)=>{
                  this.currentUserProfile=data;
                  console.log(this.currentUserProfile);
                })
                
            },
            error: (error)=>console.error('Error fetching user data:', error)
        })
        
  }
  updateUser(){
    console.log(this.currentUser);
    const userId=localStorage.getItem('userId');
    this.currentUser.id=Number(userId);
    this.authService.updateUserProfile(this.currentUser).subscribe({
      next: ()=>{
        console.log("User information updated successfully");
      },
      error: (error)=> console.error("Error updating user information:",error)
    });
  }
  updateProfile(){
    console.log(this.currentUserProfile);
    this.profileService.modifierProfile(this.currentUserProfile,this.currentUserProfile.id).subscribe({
      next: ()=>{
        console.log("Profile updated successfullty");
      },
      error: (error)=> console.error("Error updating profile:",error)
    })
  }

}
