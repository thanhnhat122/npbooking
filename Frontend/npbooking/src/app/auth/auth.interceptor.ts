import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import { UserAuthService } from '../service/user-auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(
    private userAuthService: UserAuthService,
    private router: Router
  ) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const accessToken = this.userAuthService.getItem('access_token');
    if (accessToken == null) {
      return next.handle(req.clone());
    }
    return next
      .handle(
        req.clone({
          setHeaders: {
            Authorization: `Bearer ${accessToken}`,
          },
        })
      )
      .pipe(
        catchError((err: HttpErrorResponse) => {
          console.log(err.status);
          if (err.status === 401) {
            this.router.navigate(['/login']);
          } else if (err.status === 403) {
            this.router.navigate(['/forbidden']);
          }
          return throwError('Something is wrong!');
        })
      );
  }
}
