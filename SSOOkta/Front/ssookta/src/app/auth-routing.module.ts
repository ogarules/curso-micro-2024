import { NgModule } from '@angular/core';
import { NoteListComponent } from './note-list/note-list.component';
import { NoteEditComponent } from './note-edit/note-edit.component';
import OktaAuth from '@okta/okta-auth-js';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { OKTA_CONFIG, OktaAuthGuard, OktaAuthModule, OktaCallbackComponent } from '@okta/okta-angular';
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http'
import { AuthInterceptor } from './auth.interceptor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

const oktaConfig = {
    issuer: 'https://dev-909112.okta.com/oauth2/default',
    redirectUri: window.location.origin+'/callback',
    clientId: '0oatfnww9ehf5eu73357',
    scopes: ['openid', 'profile']
};

const oktaAuth = new OktaAuth(oktaConfig);

const routes: Routes =[
    { path: '', redirectTo: '/home', pathMatch:'full' },
    { path: 'home', component: HomeComponent},
    { path: 'callback', component: OktaCallbackComponent },
    { path: 'notes', component: NoteListComponent, canActivate: [OktaAuthGuard]},
    { path: 'notes/:id', component: NoteEditComponent, canActivate: [OktaAuthGuard]},
]

@NgModule({
    declarations: [
        HomeComponent,
        NoteListComponent,
        NoteEditComponent
    ],
    imports: [
        CommonModule,
        HttpClientModule,
        OktaAuthModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule.forRoot(routes)
    ],
    providers: [
        {
            provide: OKTA_CONFIG,
            useValue: { oktaAuth }
        },
        {
            provide: HTTP_INTERCEPTORS, 
            useClass: AuthInterceptor, 
            multi: true
        }
    ],
    exports: [RouterModule]
})
export class AuthRoutingModule{

}