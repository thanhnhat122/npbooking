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
export class HotelService {
  private readonly apiUrl = 'http://localhost:8080/api/Hotel';

  constructor(private http: HttpClient) {}

  getById$ = (id: string) =>
    <Observable<CustomResponse>>(
      this.http
        .get<CustomResponse>(`${this.apiUrl}/${id}`)
        .pipe(tap(console.log), catchError(this.handleError))
    );
  getAllHotelByOwner$ = (email: string) =>
    <Observable<CustomResponse>>(
      this.http
        .post<CustomResponse>(
          `${this.apiUrl}/owner`,
          new HttpParams().set('owner', email)
        )
        .pipe(tap(console.log), catchError(this.handleError))
    );

  update$ = (id: string, payload: object) =>
    <Observable<CustomResponse>>(
      this.http
        .put<CustomResponse>(`${this.apiUrl}/${id}`, payload)
        .pipe(tap(console.log), catchError(this.handleError))
    );

  insert$ = (payload: object) =>
    <Observable<CustomResponse>>(
      this.http
        .post<CustomResponse>(this.apiUrl, payload)
        .pipe(tap(console.log), catchError(this.handleError))
    );
  delete$ = (id: string) =>
    <Observable<CustomResponse>>(
      this.http
        .delete<CustomResponse>(`${this.apiUrl}/${id}`)
        .pipe(tap(console.log), catchError(this.handleError))
    );
  private handleError(error: HttpErrorResponse): Observable<unknown> {
    console.log(error);
    return throwError(
      () => new Error(`An error ocurred - Error code: ${error.status}`)
    );
  }
}
