import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";

@Component({
    selector: "manage-rooms",
    templateUrl: "manage-rooms.component.html"
})

export class ManageRoomsComponent {
    availableRooms = [
      "AR - Audytorium im. prof. St. Ryżko (105)",
      "115",
      "573",
      "123"
    ];

    constructor( private router: Router) {

    }

    private requestNewRoom(){
      $('#newAccessModal').modal('show');
    }

    private addNewUserAccess(){
      $('#newAccessModal').modal('hide');

    }
}
