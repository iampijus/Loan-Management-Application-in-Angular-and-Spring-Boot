import {
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class JwtInterceptorService implements HttpInterceptor {
  constructor() {}

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    let userItem = sessionStorage.getItem('user');

    if (userItem) {
      let user = JSON.parse(userItem);
      if (user && user.accessToken) {
        request = request.clone({
          setHeaders: {
            Authorization: `Bearer ${user.accessToken}`,
          },
        });
      }
    }

    return next.handle(request);
  }
}
