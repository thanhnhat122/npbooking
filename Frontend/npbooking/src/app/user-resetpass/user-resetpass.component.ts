import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { UserAuthService } from '../service/user-auth.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-user-resetpass',
  templateUrl: './user-resetpass.component.html',
  styleUrls: ['./user-resetpass.component.css'],
  providers: [MessageService],
})
export class UserResetpassComponent {
  oldPassword = ""
  newPassword = ""
  reEnterNewPassword = ""
  email : any = "";


  constructor(
    private router: Router,
    private userService: UserService,
    private messageService: MessageService,
    private userAuthService: UserAuthService
    ) { }


    setOldPassword(event: any) {
      this.oldPassword = event.target.value;
    }

    setNewPassword(event: any) {
      this.newPassword = event.target.value;
    }

    setReEnterNewPassword(event: any) {
      this.reEnterNewPassword = event.target.value;
    }
    onSubmit() {
      if(this.reEnterNewPassword != this.newPassword){
        this.messageService.add({
          severity: 'error',
          summary: 'Lỗi đổi mật khẩu',
          detail: 'Mật khẩu nhập lại không khớp, xin vui lòng thử lại!',
        });
      }
      else{
        this.email = this.userAuthService.getItem('email');
        console.log(this.email)
        console.log(this.oldPassword)
        console.log(this.newPassword)
        this.userService.changePass(this.email,this.oldPassword,this.newPassword).subscribe(
          (data: any) => {
            if(data['data'] == null){
              this.messageService.add({
                severity: 'error',
                summary: 'Đổi mật khẩu',
                detail: 'Mật khẩu cũ không đúng, xin vui lòng thử lại!',
              });
            }
            else {
              this.messageService.add({
                severity: 'success',
                summary: 'Đổi mật khẩu',
                detail: 'Đổi mật khẩu thành công',
              });
            }

          },
          error => console.log(error)
        )

      }


    }

}
