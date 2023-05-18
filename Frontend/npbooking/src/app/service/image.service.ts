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
export class ImageService {
  private readonly apiUrl = 'http://localhost:8080/api/Image';

  constructor(private http: HttpClient) {}

  getAllImgByHotelId$ = (hotelId: string) =>
    <Observable<CustomResponse>>(
      this.http
        .post<CustomResponse>(
          `${this.apiUrl}/getAllImageHotel`,
          new HttpParams().set('hotelId', hotelId)
        )
        .pipe(tap(console.log), catchError(this.handleError))
    );
  getAllImgRoom$ = (hotelId: string, roomId: string) =>
    <Observable<CustomResponse>>(
      this.http
        .post<CustomResponse>(
          `${this.apiUrl}/getAllImageHotelRoom`,
          new HttpParams().set('hotelId', hotelId).set('roomId', roomId)
        )
        .pipe(tap(console.log), catchError(this.handleError))
    );

  delete$ = (id: string) =>
    <Observable<CustomResponse>>(
      this.http
        .delete<CustomResponse>(`${this.apiUrl}/${id}`)
        .pipe(tap(console.log), catchError(this.handleError))
    );

  upload(
    hotelId: string,
    roomId: string,
    file: File
  ): Observable<CustomResponse> {
    const formData = new FormData();
    formData.append('hotelId', hotelId);
    formData.append('roomId', roomId);
    formData.append('file', file);
    return this.http.post<CustomResponse>(
      `${this.apiUrl}/uploadImage`,
      formData
    );
  }
  private handleError(error: HttpErrorResponse): Observable<unknown> {
    console.log(error);
    return throwError(
      () => new Error(`An error ocurred - Error code: ${error.status}`)
    );
  }
}
