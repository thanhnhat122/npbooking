import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
  HttpParams,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { DataState } from '../enum/data.state.enum';
import { CustomResponse } from '../model/custom.response';
import { User } from '../model/user';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly apiUrl = 'http://localhost:8080/api';
  private baseURL = "http://localhost:8080/api/User"

  constructor(private http: HttpClient) {}

  getAll$ = <Observable<CustomResponse>>(
    this.http
      .get<CustomResponse>(`${this.apiUrl}/User`)
      .pipe(tap(console.log), catchError(this.handleError))
  );

  save$ = (user: User) =>
    <Observable<CustomResponse>>(
      this.http
        .post<CustomResponse>(`${this.apiUrl}/User`, user)
        .pipe(tap(console.log), catchError(this.handleError))
    );

  getByEmail$ = (email: string) =>
    <Observable<CustomResponse>>(
      this.http
        .post<CustomResponse>(
          `${this.apiUrl}/User/getUser`,
          new HttpParams().set('email', email)
        )
        .pipe(tap(console.log), catchError(this.handleError))
    );

  delete$ = (id: string) =>
    <Observable<CustomResponse>>(
      this.http
        .delete<CustomResponse>(`${this.apiUrl}/User/${id}`)
        .pipe(tap(console.log), catchError(this.handleError))
    );

  private handleError(error: HttpErrorResponse): Observable<unknown> {
    console.log(error);
    return throwError(
      () => new Error(`An error ocurred - Error code: ${error.status}`)
    );
  }
  public setRole(role: string) {
    localStorage.setItem('roleInsert', role);
  }
  public getRole() {
    return localStorage.getItem('roleInsert');
  }
  getAdminList(): Observable<any> {
    return this.http.get(`${this.baseURL}/admin`);
  }
  getStaffList(): Observable<any> {
    return this.http.get(`${this.baseURL}/staff`);
  }
  getCustomerList(): Observable<any> {
    return this.http.get(`${this.baseURL}/customer`);
  }
  getUserByEmail(email: string) {
    const formData = new FormData();
    formData.append('email', email);
    return this.http.post<any>(this.baseURL + "/getUser", formData);
  }

  createUser(user: User): Observable<Object> {
    return this.http.post(`${this.baseURL}`, user);
  }

  updateUser(email: string, user: User): Observable<Object> {
    const params = { 'email': email };
    return this.http.put(`${this.baseURL}`, user, { params });
  }

  deleteUser(email: string): Observable<Object> {
    const params = { 'email': email };
    return this.http.delete(`${this.baseURL}`, { params });
  }
  changePass(email: string, oldPassword: string, newPassword: string): Observable<Object>{
    return this.http.post<any>(`${this.baseURL}/changePass`, new HttpParams()
    .set('email',email)
    .set('oldPassword',oldPassword)
    .set('newPassword',newPassword))
  }
}
