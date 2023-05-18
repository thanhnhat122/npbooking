import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { CustomResponse } from '../model/custom.response';
import { Review } from '../model/review';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root',
})
export class ReviewService {
  private readonly apiUrl = 'http://localhost:8080/api/Review';

  constructor(private http: HttpClient) {}

  getAllReviewByHotelId$ = (id: string) =>
    <Observable<CustomResponse>>(
      this.http
        .post<CustomResponse>(
          `${this.apiUrl}/hotel`,
          new HttpParams().set('hotelId', id)
        )
        .pipe(tap(console.log), catchError(this.handleError))
    );

  save$ = (user: User) =>
    <Observable<CustomResponse>>(
      this.http
        .post<CustomResponse>(`${this.apiUrl}/User`, user)
        .pipe(tap(console.log), catchError(this.handleError))
    );

  getById$ = (id: string) =>
    <Observable<CustomResponse>>(
      this.http
        .get<CustomResponse>(`${this.apiUrl}/User/${id}`)
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
  createReview(review: Review): Observable<Object> {
    return this.http.post(`${this.apiUrl}`, review);
  }
}
