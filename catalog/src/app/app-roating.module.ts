import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { ElemInfoComponent } from './components/elem-info/elem-info.component';
import { WrapperComponent } from './components/wrapper/wrapper.component';
import { ErrorComponent } from './components/error/error.component';

const routes: Routes = [
  { path: '', component: WrapperComponent },
  { path: 'about', component: AboutComponent },
  { path: 'elem-info', component: ElemInfoComponent },
  { path: 'page404', component: ErrorComponent },
  { path: '**', redirectTo: 'page404' }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoatingModule { }
