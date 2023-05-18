import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, map, Observable, of, startWith } from 'rxjs';
import { DataState } from '../enum/data.state.enum';
import { AppState } from '../model/app-state';
import { CustomResponse } from '../model/custom.response';
import { UserAuthService } from '../service/user-auth.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-user-setting',
  templateUrl: './user-setting.component.html',
  styleUrls: ['./user-setting.component.css'],
})
export class UserSettingComponent implements OnInit {
  readonly DataState = DataState;
  userState$!: Observable<AppState<CustomResponse>>;
  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private route: Router
  ) {}
  ngOnInit(): void {
    const email = this.userAuthService.getItem('email');
    if (email) {
      this.userState$ = this.userService.getByEmail$(email).pipe(
        map((response) => {
          if (!response.data) {
            this.route.navigate(['/login']);
          }
          return { dataState: DataState.LOADED_STATE, appData: response };
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error });
        })
      );
    } else {
      this.route.navigate(['/login']);
    }
  }
}
