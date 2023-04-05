import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { OpenAccountComponent } from './components/open-account/open-account.component';
import { AppRoatingModule } from './app-roating.module';
import { WrapperComponent } from './components/wrapper/wrapper.component';
import { HeaderComponent } from './components/layout/header/header.component';
import { FormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';
import { CloseAccountComponent } from './components/close-account/close-account.component';
import { PutMoneyComponent } from './components/put-money/put-money.component';
import { GetMoneyComponent } from './components/get-money/get-money.component';
import { InfoComponent } from './components/info/info.component';

@NgModule({
  declarations: [
    AppComponent,
    OpenAccountComponent,
    WrapperComponent,
    HeaderComponent,
    CloseAccountComponent,
    PutMoneyComponent,
    GetMoneyComponent,
    InfoComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoatingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
