import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { CustomResponse } from '../model/custom.response';
import { Province } from '../model/province';

@Injectable({
  providedIn: 'root',
})
export class PayService {
  private readonly apiUrl = 'http://localhost:8080/api/Pay';

  constructor(private http: HttpClient) {}

  paypal$ = (price:number, bookingId: number) =>
    <Observable<CustomResponse>>(
      this.http
        .post<CustomResponse>(
          `${this.apiUrl}`,
          new HttpParams().set('price', price).set('bookingId', bookingId)
        )
        .pipe(tap(console.log), catchError(this.handleError))
    );
    getSuccess$ = (bookingId: number, paymentId: string, payerId: string) =>
    <Observable<CustomResponse>>(
      this.http
        .get<CustomResponse>(`${this.apiUrl}/success/?bookingId=${bookingId}&paymentId=${paymentId}&PayerID=${payerId}`)
        .pipe(tap(console.log), catchError(this.handleError))
    );
    

  private handleError(error: HttpErrorResponse): Observable<unknown> {
    console.log(error);
    return throwError(
      () => new Error(`An error ocurred - Error code: ${error.status}`)
    );
  }
}
