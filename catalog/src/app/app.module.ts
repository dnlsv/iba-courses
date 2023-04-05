import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ListComponent } from './components/list/list.component';
import { ElemItemComponent } from './components/elem-item/elem-item.component';

import { DataService } from './data.service';
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';

import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './components/layout/header/header.component';
import { AppRoatingModule } from './app-roating.module';
import { AboutComponent } from './components/about/about.component';
import { ElemInfoComponent } from './components/elem-info/elem-info.component';
import { ChangeComponent } from './components/change/change.component';
import { WrapperComponent } from './components/wrapper/wrapper.component';
import { FormsModule } from '@angular/forms';
import { ErrorComponent } from './components/error/error.component';

@NgModule({
  declarations: [
    AppComponent,
    ListComponent,
    ElemItemComponent,
    HeaderComponent,
    AboutComponent,
    ElemInfoComponent,
    ChangeComponent,
    WrapperComponent,
    ErrorComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientInMemoryWebApiModule.forRoot(DataService, {
      post204: false,
      put204: false,
      delay: 1000,
    }),
    HttpClientModule,
    AppRoatingModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
