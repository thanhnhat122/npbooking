import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, map, startWith, catchError, of } from 'rxjs';
import { DataState } from '../enum/data.state.enum';
import { AppState } from '../model/app-state';
import { CustomResponse } from '../model/custom.response';
import { UserAuthService } from '../service/user-auth.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  visibleBanner: string = '';
  readonly DataState = DataState;
  userState$!: Observable<AppState<CustomResponse>>;
  isLogged: boolean;

  closeBanner() {
    this.visibleBanner = 'hidden';
  }

  constructor(
    protected userAuthService: UserAuthService,
    private userService: UserService,
    private route: Router
  ) {
    this.isLogged = this.userAuthService.isLogged();
  }
  ngOnInit(): void {
    const email = this.userAuthService.getItem('email');
    if (email) {
      this.userState$ = this.userService.getByEmail$(email).pipe(
        map((response) => {
          if (!response.data) {
            this.logOut();
          }
          this.isLogged = true;
          return { dataState: DataState.LOADED_STATE, appData: response };
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          this.isLogged = false;
          return of({ dataState: DataState.ERROR_STATE, error });
        })
      );
    }
  }
  logOut(): void {
    this.isLogged = false;
    this.userAuthService.clearAllItems();
    this.route.navigate(['/']);
  }
}
