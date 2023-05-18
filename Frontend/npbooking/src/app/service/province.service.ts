import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { CustomResponse } from '../model/custom.response';
import { Province } from '../model/province';

@Injectable({
  providedIn: 'root',
})
export class ProvinceService {
  private readonly apiUrl = 'http://localhost:8080/api/Province';

  constructor(private http: HttpClient) {}

  getAll$ = <Observable<CustomResponse>>(
    this.http
      .get<CustomResponse>(`${this.apiUrl}`)
      .pipe(tap(console.log), catchError(this.handleError))
  );
  findById$ = (id: string) =>
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
}
