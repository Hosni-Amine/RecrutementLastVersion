import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


import { UserComponent } from './user.component';
import { AddUserComponent } from './add-user/add-user.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { UserRoutes } from './user.routing';
import { RouterModule } from '@angular/router';

import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    UserComponent,
    AddUserComponent,
    UpdateUserComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(UserRoutes),
    FormsModule

  ]
})
export class UserModule { }
