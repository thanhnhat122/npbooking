import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, map, Observable, of, startWith } from 'rxjs';
import { DataState } from '../enum/data.state.enum';
import { AppState } from '../model/app-state';
import { CustomResponse } from '../model/custom.response';
import { UserAuthService } from '../service/user-auth.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {
  readonly DataState = DataState;
  userState$!: Observable<AppState<CustomResponse>>;
  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private route: Router
  ) {}
  ngOnInit(): void {
    this.userState$ = this.userService
      .getByEmail$(this.userAuthService.getItem('email') as string)
      .pipe(
        map((response) => {
          if (!response.data) {
            // this.isLogged = false;
            this.userAuthService.clearAllItems();
            this.route.navigate(['/login']);
          }
          // this.isLogged = true;
          return { dataState: DataState.LOADED_STATE, appData: response };
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error });
        })
      );
  }
  logOut(): void {
    // this.isLogged = false;
    this.userAuthService.clearAllItems();
    this.route.navigate(['/']);
  }
}
