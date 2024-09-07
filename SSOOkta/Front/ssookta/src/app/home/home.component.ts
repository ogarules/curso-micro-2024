import { Component, Inject, OnInit } from '@angular/core';
import { OKTA_AUTH } from '@okta/okta-angular';
import OktaAuth, { AuthState } from '@okta/okta-auth-js';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  isAuthenticated: boolean = false;

  constructor(@Inject(OKTA_AUTH) public oktaAuth: OktaAuth){

  }

  async ngOnInit(){
    this.isAuthenticated = await this.oktaAuth.isAuthenticated();
    this.oktaAuth.authStateManager.subscribe(
      (authState: AuthState) => this.isAuthenticated = authState.isAuthenticated
    );
  }
}
