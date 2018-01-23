import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import * as $ from 'jquery';

import {AppRoutingModule} from "./appRouting.module";
import { AppComponent } from './app.component';
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {OpenCloseRoomComponent} from "./components/open-close-room/open-close-room.component";
import {ManageRoomsComponent} from "./components/manage-rooms/manage-rooms.component";
import {LoginPageComponent} from "./components/login-page/login-page.component";
import {PageNotFoundComponent} from "./components/page-not-found/page-not-found.component";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    OpenCloseRoomComponent,
    ManageRoomsComponent,
    LoginPageComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
