import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";

@Component({
    selector: "manage-rooms",
    templateUrl: "manage-rooms.component.html"
})

export class ManageRoomsComponent {
    availableRooms = '';

    constructor( private router: Router) {

    }

    private requestNewRoom(){

    }

}
