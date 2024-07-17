import { Routes } from "@angular/router";
import { TestoffersComponent } from "./testoffers.component";
import { AddtestoffersComponent } from "./addtestoffers/addtestoffers.component";
import { UpdatetestoffersComponent } from "./updatetestoffers/updatetestoffers.component";

export const TestOffersRoutes: Routes = [
	{
		path: '',
		children: [
			{
				path: 'display',
				component: TestoffersComponent
			},
			{
				path: 'add',
				component: AddtestoffersComponent
			},
			{
				path: 'update/:id',
				component: UpdatetestoffersComponent
			},
			/*{
				path: 'details/:id',
				component:OfferDetailsComponent
			}*/
			
		]
	}
];