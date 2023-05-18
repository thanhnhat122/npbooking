import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Role } from '../enum/role.enum';
import { User } from '../model/user';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-admin-admin',
  templateUrl: './admin-admin.component.html',
  styleUrls: ['./admin-admin.component.css']
})
export class AdminAdminComponent {
  users!: User[];
  user1: User = <User>{};
  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getAdmins();
  }
  private getAdmins(){
    this.userService.getAdminList().subscribe(
      (data: any) => {
        console.log(data)
        this.users= data['data']
      }
    );
  }
  userDetail(email: string){

    this.router.navigate(['user-details',email]);
  }
  insertUser(){
    this.userService.setRole('ROLE_AD');
    this.router.navigate(['/create-user']);
  }

  updateUser(email: string) {
    this.userService.setRole('ROLE_AD');
    this.router.navigate(['/update-user', email]);
  }

  deleteUser(email: string) {
    this.userService.deleteUser(email).subscribe((data: any) => {
      console.log(data);
      this.getAdmins();
    });
  }
  saveAdmin(){
    this.user1.role= Role.ROLE_AD;
    this.userService.createUser(this.user1).subscribe(
      (data: any) => {
        //this.toastr.success('Thêm thành công!');
      },
      error => console.log(error)
    );
  }
  updateAdmin(email: string, user: User){
    this.userService.updateUser(email, user).subscribe(
      (data: any) => {
        //this.toastr.success('Cập nhật thành công!');
      },
      error => console.log(error)
    );
  }

}
