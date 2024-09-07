import { Component, Inject, OnInit } from '@angular/core';
import { OKTA_AUTH } from '@okta/okta-angular';
import OktaAuth, { AuthState } from '@okta/okta-auth-js';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'ssookta';

  isAuthenticated: boolean = false;

  constructor(@Inject(OKTA_AUTH) private oktaAuth: OktaAuth){

  }

  async ngOnInit(){
    this.isAuthenticated = await this.oktaAuth.isAuthenticated();
    this.oktaAuth.authStateManager.subscribe(
      (authState: AuthState) => this.isAuthenticated = authState.isAuthenticated
    );
  }
}
