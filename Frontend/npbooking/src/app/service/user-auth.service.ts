import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
} from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { ResolveStart } from '@angular/router';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { CustomResponse } from '../model/custom.response';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root',
})
export class UserAuthService {
  private readonly apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}
  public setItem(key: string, value: string) {
    localStorage.setItem(key, value);
  }
  public getItem(key: string) {
    return localStorage.getItem(key);
  }
  public isLogged(): boolean {
    return !!(this.getItem('access_token') && this.getItem('refresh_token'));
  }
  public clearAllItems() {
    localStorage.clear();
  }

  public roleMatch(role: string): boolean {
    return this.getItem('roles') === role;
  }

  login$ = (email: string, password: string) =>
    <Observable<CustomResponse>>(
      this.http
        .post<CustomResponse>(
          `${this.apiUrl}/login`,
          new HttpParams().set('email', email).set('password', password)
        )
        .pipe(tap(console.log), catchError(this.handleError))
    );
  register$ = (payload: object) =>
    <Observable<CustomResponse>>(
      this.http
        .post<CustomResponse>(`${this.apiUrl}/User/signup`, payload)
        .pipe(tap(console.log), catchError(this.handleError))
    );

  activation$ = (token: string) =>
    <Observable<CustomResponse>>(
      this.http
        .get<CustomResponse>(`${this.apiUrl}/User/activation?token=${token}`)
        .pipe(tap(console.log), catchError(this.handleError))
    );
  private handleError(error: HttpErrorResponse): Observable<unknown> {
    console.log(error);
    return throwError(
      () => new Error(`An error ocurred - Error code: ${error.status}`)
    );
  }
}
