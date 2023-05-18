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
export class BookingService {
  private readonly apiUrl = 'http://localhost:8080/api/Booking';

  constructor(private http: HttpClient) {}

  insertBooking$ = (payload: object) =>
    <Observable<CustomResponse>>(
      this.http
        .post<CustomResponse>(`${this.apiUrl}`, payload)
        .pipe(tap(console.log), catchError(this.handleError))
    );
  getByHotelId$ = (id: string) =>
    <Observable<CustomResponse>>(
      this.http
        .get<CustomResponse>(`${this.apiUrl}/hotelId/${id}`)
        .pipe(tap(console.log), catchError(this.handleError))
    );
  update$ = (id: string, payload: object) =>
    <Observable<CustomResponse>>(
      this.http
        .put<CustomResponse>(`${this.apiUrl}/${id}`, payload)
        .pipe(tap(console.log), catchError(this.handleError))
    );
  getById$ = (id: string) =>
    <Observable<CustomResponse>>(
      this.http
        .get<CustomResponse>(`${this.apiUrl}/${id}`)
        .pipe(tap(console.log), catchError(this.handleError))
    );
  private handleError(error: HttpErrorResponse): Observable<unknown> {
    console.log(error);
    return throwError(
      () => new Error(`An error ocurred - Error code: ${error.status}`)
    );
  }
  getBookingDTOByEmail(email: string) {
    const formData = new FormData();
    formData.append('userEmail', email);
    return this.http.post<any>(this.apiUrl + "/email", formData);
  }

}
