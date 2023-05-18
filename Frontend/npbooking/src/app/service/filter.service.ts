import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap, catchError, throwError } from 'rxjs';
import { CustomResponse } from '../model/custom.response';

@Injectable({
  providedIn: 'root',
})
export class FilterService {
  private readonly apiUrl = 'http://localhost:8080/api/Hotel';

  constructor(private http: HttpClient) {}

  filter$ = (payload: object) =>
    <Observable<CustomResponse>>(
      this.http
        .post<CustomResponse>(`${this.apiUrl}/filter`, payload)
        .pipe(tap(console.log), catchError(this.handleError))
    );
  private handleError(error: HttpErrorResponse): Observable<unknown> {
    console.log(error);
    return throwError(
      () => new Error(`An error ocurred - Error code: ${error.status}`)
    );
  }
}
