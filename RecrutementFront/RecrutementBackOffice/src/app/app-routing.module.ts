import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FullComponent } from './layouts/full/full.component';
import { LoginComponent } from './auth/login/login.component';
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';

export const Approutes: Routes = [
  
  {
    path: '',
    component: FullComponent,
    children: [
      { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
      
      {
        path: 'dashboard',
        loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule)
      },
      {
        path: 'about',
        loadChildren: () => import('./about/about.module').then(m => m.AboutModule)
      },
      {
        path: 'component',
        loadChildren: () => import('./component/component.module').then(m => m.ComponentsModule)
      },
      {
        path:'offer',
        loadChildren: ()=>import('./offer/offer.module').then(m=>m.OfferModule)
      },
      {
        path:'testoffers',
        loadChildren: ()=>import('./testoffers/testoffers.module').then(m=>m.TestoffersModule)
      },
      {
        path:'candidature',
        loadChildren: ()=>import('./candidature/candidature.module').then(m=>m.CandidatureModule)
      },
      {
        path:'user',
        loadChildren: ()=>import('./user/user.module').then(m=>m.UserModule)
      },
    ]
    
  },
  {
    path:'auth',
    component:AuthLayoutComponent,
    children:[
      { path: '', redirectTo: '/login', pathMatch: 'full' },
      {path:'login',component:LoginComponent}
    ]
  },
  {
    path: '**',
    redirectTo: '/starter'
  },
  
];
