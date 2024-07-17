import { Routes } from "@angular/router";
import { AddCandidatureComponent } from "./add-candidature/add-candidature.component";
import { CandidatureComponent } from "./candidature.component";
import { UpdateCandidatureComponent } from "./update-candidature/update-candidature.component";
import { InterviewComponent } from "./interview/interview.component";

export const CandidatureRoutes: Routes = [
	{
		path: '',
		children: [
			{
				path: 'display',
				component: CandidatureComponent
			},
			{
				path: 'add',
				component: AddCandidatureComponent
			},
			{
				path: 'update/:id',
				component: UpdateCandidatureComponent
			},
			{
				path: 'interview',
				component: InterviewComponent
			}
			
		]
	}
];
