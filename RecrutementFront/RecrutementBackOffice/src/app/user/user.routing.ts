import { Routes } from "@angular/router";
import { AddUserComponent } from "./add-user/add-user.component";
import { UserComponent } from "./user.component";
import { UpdateUserComponent } from "./update-user/update-user.component";

export const UserRoutes: Routes = [
	{
		path: '',
		children: [
			{
				path: 'display',
				component: UserComponent
			},
			{
				path: 'add',
				component: AddUserComponent
			},
			{
				path: 'update/:id',
				component: UpdateUserComponent
			}
			
		]
	}
];
