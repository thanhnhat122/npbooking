import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Role } from '../enum/role.enum';
import { User } from '../model/user';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-admin-host',
  templateUrl: './admin-host.component.html',
  styleUrls: ['./admin-host.component.css']
})
export class AdminHostComponent {
  users!: User[];
  user1: User = <User>{};
  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getStaffs();
  }
  private getStaffs(){
    this.userService.getStaffList().subscribe(
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
      this.getStaffs();
    });
  }
  saveHost(){
    this.user1.role= Role.ROLE_NV;
    this.userService.createUser(this.user1).subscribe(
      (data: any) => {
        this.getStaffs();
        //this.toastr.success('Thêm thành công!');
      },
      error => console.log(error)
    );
  }
  updateHost(email: string, user: User){
    this.userService.updateUser(email, user).subscribe(
      (data: any) => {
        this.getStaffs();
        //this.toastr.success('Cập nhật thành công!');
      },
      error => console.log(error)
    );
  }


}
