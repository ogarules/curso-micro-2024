import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Inject, Injectable } from "@angular/core";
import { OKTA_AUTH } from "@okta/okta-angular";
import OktaAuth from "@okta/okta-auth-js";
import { from, Observable } from "rxjs";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(@Inject(OKTA_AUTH) private oktaAuth: OktaAuth){

    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return from(this.handleAccess(req, next));
    }

    private async handleAccess(req: HttpRequest<any>, next: HttpHandler) : Promise<HttpEvent<any>>{

        const allowedOrigins = ['http://localhost'];

        if(allowedOrigins.some(url => req.urlWithParams.includes(url))){

            const accessToken = this.oktaAuth.getAccessToken();

            req = req.clone({
                setHeaders:{
                    Authorization: 'Bearer ' + accessToken
                }
            })
        }

        return next.handle(req).toPromise();
    }
}