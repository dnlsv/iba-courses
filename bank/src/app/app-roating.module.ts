import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { OpenAccountComponent } from './components/open-account/open-account.component';
import { AppComponent } from './app.component';
import { WrapperComponent } from './components/wrapper/wrapper.component';
import { CloseAccountComponent } from './components/close-account/close-account.component';
import { PutMoneyComponent } from './components/put-money/put-money.component';
import { GetMoneyComponent } from './components/get-money/get-money.component';
import { InfoComponent } from './components/info/info.component';

const routes: Routes = [
  { path: '', component: WrapperComponent},
  { path: 'open-account', component: OpenAccountComponent},
  { path: 'close-account', component: CloseAccountComponent},
  { path: 'put-money', component: PutMoneyComponent},
  { path: 'get-money', component: GetMoneyComponent},
  { path: 'info', component: InfoComponent}
]

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
