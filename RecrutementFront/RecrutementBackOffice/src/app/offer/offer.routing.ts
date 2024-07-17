import { Routes } from "@angular/router";
import { AddOfferComponent } from "./add-offer/add-offer.component";
import { OfferComponent } from "./offer.component";
import { UpdateOfferComponent } from "./update-offer/update-offer.component";
import { OfferDetailsComponent } from "./offer-details/offer-details.component";

export const OfferRoutes: Routes = [
	{
		path: '',
		children: [
			{
				path: 'display',
				component: OfferComponent
			},
			{
				path: 'add',
				component: AddOfferComponent
			},
			{
				path: 'update/:id',
				component: UpdateOfferComponent
			},
			{
				path: 'details/:id',
				component:OfferDetailsComponent
			}
			
		]
	}
];