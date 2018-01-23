import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {OpenCloseRoomComponent} from "./components/open-close-room/open-close-room.component";
import {ManageRoomsComponent} from "./components/manage-rooms/manage-rooms.component";
import {LoginPageComponent} from "./components/login-page/login-page.component";
import {PageNotFoundComponent} from "./components/page-not-found/page-not-found.component";


const routes: Routes = [
    {path: '', redirectTo: '/loginPage', pathMatch: 'full'},
    {path: 'dashboard', component: DashboardComponent},
    {path: 'openCloseRoom', component: OpenCloseRoomComponent},
    {path: 'manageRooms', component: ManageRoomsComponent},
    {path: 'loginPage', component: LoginPageComponent},
    {path: '**', component: PageNotFoundComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
