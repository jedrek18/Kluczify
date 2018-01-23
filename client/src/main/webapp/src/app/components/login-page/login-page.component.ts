import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";


@Component({
    selector: 'login-page',
    templateUrl: 'login-page.template.html',
    styleUrls: ['login-page.style.css']
})

export class LoginPageComponent implements OnInit {

    constructor( private router: Router)
     { }

    ngOnInit() {

    }

    login() {
      this.router.navigate(['/dashboard']);
    }
}
