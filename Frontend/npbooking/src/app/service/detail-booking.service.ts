import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { CustomResponse } from '../model/custom.response';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root',
})
export class DetailBookingService {
  private readonly apiUrl = 'http://localhost:8080/api/DetailBooking';

  constructor(private http: HttpClient) {}

  insertDetailBooking$ = (payload: object) =>
    <Observable<CustomResponse>>(
      this.http
        .post<CustomResponse>(`${this.apiUrl}`, payload)
        .pipe(tap(console.log), catchError(this.handleError))
    );

  private handleError(error: HttpErrorResponse): Observable<unknown> {
    console.log(error);
    return throwError(
      () => new Error(`An error ocurred - Error code: ${error.status}`)
    );
  }
  getBookingDTO(bookingId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/booking/${bookingId}`);
  }

}
