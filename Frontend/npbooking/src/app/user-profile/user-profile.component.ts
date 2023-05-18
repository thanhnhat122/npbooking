import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { catchError, map, Observable, of, startWith } from 'rxjs';
import { DataState } from '../enum/data.state.enum';
import { AppState } from '../model/app-state';
import { CustomResponse } from '../model/custom.response';
import { User } from '../model/user';
import { UserAuthService } from '../service/user-auth.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
  providers: [MessageService],
})
export class UserProfileComponent implements OnInit {
  readonly DataState = DataState;
  userProfileState$!: Observable<AppState<CustomResponse>>;
  user : User = <User>{};
  email: any;
  constructor(
    private userService: UserService,
    private userAuthService: UserAuthService,
    private messageService: MessageService,
    private route: Router
  ) {}
  ngOnInit(): void {
    this.email = this.userAuthService.getItem('email');
    if (this.email) {
      this.userProfileState$ = this.userService.getByEmail$(this.email).pipe(
        map((response) => {
          if (!response.data) {
            this.route.navigate(['/login']);
          }
          // this.userForm.get('email')?.setValue(response.data.email);
          // this.userForm.get('firstName')?.setValue(response.data.firstName);
          // this.userForm.get('lastName')?.setValue(response.data.lastName);
          // this.userForm.get('phone')?.setValue(response.data.phone);
          this.user= response.data;
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
  onSubmit(){
    console.log(this.user);
    this.userService.updateUser(this.email, this.user).subscribe(
      (data: any) => {
        console.log(data)
        this.messageService.add({
          severity: 'success',
          summary: 'Cập nhật',
          detail: 'Cập nhật thông tin thành công',
        });
      },
      error => console.log(error)
    );
  }
}
