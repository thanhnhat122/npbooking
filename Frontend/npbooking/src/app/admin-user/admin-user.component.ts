import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Role } from '../enum/role.enum';
import { User } from '../model/user';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-admin-user',
  templateUrl: './admin-user.component.html',
  styleUrls: ['./admin-user.component.css']
})
export class AdminUserComponent {
  users!: User[];
  user1: User = <User>{};
  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getUsers();
  }
  private getUsers(){
    this.userService.getCustomerList().subscribe(
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


  deleteUser(email: string) {
    this.userService.deleteUser(email).subscribe((data: any) => {
      console.log(data);
      this.getUsers();
    });
  }
  saveUser(){
    this.user1.role= Role.ROLE_KH;
    this.userService.createUser(this.user1).subscribe(
      (data: any) => {
        //this.toastr.success('Thêm thành công!');
      },
      error => console.log(error)
    );
  }
  updateUser(email: string, user: User){
    this.userService.updateUser(email, user).subscribe(
      (data: any) => {
        //this.toastr.success('Cập nhật thành công!');
      },
      error => console.log(error)
    );
  }

}
