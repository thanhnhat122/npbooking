import { Component } from '@angular/core';
import { MessageService } from 'primeng/api';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { catchError, map, Observable, of, startWith } from 'rxjs';
import { AppState } from '../model/app-state';
import { CustomResponse } from '../model/custom.response';
import { UserAuthService } from '../service/user-auth.service';
import { DataState } from '../enum/data.state.enum';
import { Router } from '@angular/router';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  providers: [MessageService],
})
export class RegisterComponent {
  registerState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  loadingReg: boolean = false;
  readonly registerForm = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName: new FormControl('', [Validators.required]),
    email: new FormControl('', [
      Validators.required,
      Validators.pattern(/\b[\w\.-]+@[\w\.-]+\.\w{2,4}\b/),
    ]),
    phone: new FormControl('', [
      Validators.required,
      Validators.pattern(/(84|0[3|5|7|8|9])+([0-9]{8})\b/),
    ]),
    password: new FormControl('', [
      Validators.required,
      Validators.pattern(
        /(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})./
      ),
    ]),
    confirm_password: new FormControl('', [Validators.required]),
  });
  constructor(
    private messageService: MessageService,
    private userAuthService: UserAuthService,
    private router: Router
  ) {}

  register() {
    this.loadingReg = true;
    const { confirm_password, ...payload } = this.registerForm.value;
    console.log(this.registerForm.value)
    if (confirm_password !== payload.password) {
      this.messageService.add({
        severity: 'error',
        summary: 'Lỗi mật khẩu',
        detail: 'Mật khẩu không khớp, vui lòng nhập lại!',
      });
      this.loadingReg = false;
      return;
    }
    this.userAuthService.register$(payload).subscribe(
      (response: any) => {
        if (!response.data && response.errCode === '400') {
          if (response.errMsg === 'Phone exist!') {
            this.messageService.add({
              severity: 'error',
              summary: 'Lỗi đăng ký',
              detail: 'Số điện thoại đã được sử dụng',
            });
          } else {
            this.messageService.add({
              severity: 'error',
              summary: 'Lỗi đăng ký',
              detail: 'Email đã được sử dụng',
            });
          }
          this.loadingReg = false;
          return;
        }
        this.messageService.add({
          severity: 'success',
          summary: 'Đăng ký thành công',
          detail: 'Hãy xác nhận email bạn vừa nhập',
          life: 10000,
        });
        this.loadingReg = false;
      },
      error => {
        this.messageService.add({
          severity: 'error',
          summary: 'Lỗi đăng ký',
          detail: 'Email hoặc số điện thoại đã được sử dụng',
        });
        this.loadingReg = false;
      }
    );
  }
}
