import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { catchError, map, Observable, of, startWith } from 'rxjs';
import { DataState } from '../enum/data.state.enum';
import { AppState } from '../model/app-state';
import { CustomResponse } from '../model/custom.response';
import { UserAuthService } from '../service/user-auth.service';

@Component({
  selector: 'app-activation',
  templateUrl: './activation.component.html',
  styleUrls: ['./activation.component.css'],
})
export class ActivationComponent implements OnInit {
  activationState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  text: string = '';
  showLogin: boolean = false;
  constructor(
    private userAuthService: UserAuthService,
    private param: ActivatedRoute
  ) {}
  ngOnInit(): void {
    this.param.queryParams.subscribe((params) => {
      this.activationState$ = this.userAuthService
        .activation$(params['token'])
        .pipe(
          map((response) => {
            if (!response.data && response.errCode === '400') {
              switch (response.errMsg) {
                case 'Your verification token has expired':
                  this.text = 'Link xác thực đã hết hạn, vui lòng thử lại';
                  break;
                case 'Your verification token is invalid':
                  this.text = 'Link xác thực của bạn không hợp lệ';
                  break;
                default:
                  break;
              }
              return { dataState: DataState.LOADED_STATE };
            }
            this.showLogin = true;
            this.text = 'Tài khoản của bạn đã được tạo thành công';
            return { dataState: DataState.LOADED_STATE, appData: response };
          }),
          startWith({ dataState: DataState.LOADING_STATE }),
          catchError((error: string) => {
            this.text = 'Có lỗi xảy ra, vui lòng thử lại';
            return of({ dataState: DataState.ERROR_STATE, error });
          })
        );
    });
  }
}
