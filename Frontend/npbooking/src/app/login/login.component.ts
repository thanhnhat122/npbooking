import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserAuthService } from '../service/user-auth.service';
import { User } from '../model/user';
import { catchError, map, Observable, of, startWith, tap } from 'rxjs';
import { AppState } from '../model/app-state';
import { CustomResponse } from '../model/custom.response';
import { DataState } from '../enum/data.state.enum';
import { Role } from '../enum/role.enum';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [MessageService],
})
export class LoginComponent implements OnInit {
  loginState$!: Observable<AppState<CustomResponse>>;
  loginForm = new FormGroup({
    email: new FormControl('', [
      Validators.required,
      Validators.pattern(
        /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/g
      ),
    ]),
    password: new FormControl('', [Validators.required]),
  });
  readonly DataState = DataState;
  readonly Role = Role;
  returnUrl: string = '';
  constructor(
    private route: ActivatedRoute,
    private userAuthService: UserAuthService,
    private router: Router,
    private messageService: MessageService
  ) {}
  ngOnInit(): void {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    console.log(this.returnUrl);
  }

  login() {
    const { email, password } = this.loginForm.getRawValue();
    this.loginState$ = this.userAuthService
      .login$(email as string, password as string)
      .pipe(
        map((response) => {
          const { access_token, refresh_token, roles, email } = response.data;
          this.userAuthService.setItem('access_token', access_token);
          this.userAuthService.setItem('refresh_token', refresh_token);
          this.userAuthService.setItem('email', email);
          this.userAuthService.setItem('roles', roles);
          switch (roles) {
            case this.Role.ROLE_NV:
              this.router.navigate(['/host']);
              break;
            case this.Role.ROLE_AD:
              this.router.navigate(['/admin']);
              break;
            default:
              this.router.navigateByUrl(this.returnUrl);
              break;
          }
          return { dataState: DataState.LOADED_STATE, appData: response };
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          this.messageService.add({
            severity: 'error',
            summary: 'Lỗi đăng nhập',
            detail: 'Email hoặc mật khẩu không đúng, xin vui lòng thử lại!',
          });
          return of({ dataState: DataState.ERROR_STATE, error });
        })
      );
  }

  // login(loginForm: NgForm) {
  //   this.userService.login(loginForm).subscribe(
  //     (response: any) => {
  //       console.log(response);
  //       this.userAuthService.setEmail(response.data.email);
  //       this.userAuthService.setAccessToken(response.data.access_token);
  //       this.userAuthService.setRefreshToken(response.data.refresh_token);
  //       this.userAuthService.setRole(response.data.roles);

  //       const role = response.data.roles;
  //       if (role == 'ROLE_AD') {
  //         this.router.navigate(['/admin']);
  //       } else {
  //         if (role == 'ROLE_NV') {
  //           this.router.navigate(['/staff']);
  //         } else {
  //           console.log('OKE nha');
  //           this.router.navigate(['/']).then(() => {
  //             window.location.reload();
  //           });
  //         }
  //       }
  //     },
  //     (error) => {
  //       //this.toastr.error('Tài khoản hoặc mật khẩu không chính xác');
  //       // console.log(error);
  //     }
  //   );
  // }
}
