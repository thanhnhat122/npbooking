import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { catchError, map, Observable, of, pluck, startWith } from 'rxjs';
import { FilterDTO } from '../dto/filter.dto';
import { DataState } from '../enum/data.state.enum';
import { AppState } from '../model/app-state';
import { CustomResponse } from '../model/custom.response';
import { Province } from '../model/province';
import { ProvinceService } from '../service/province.service';
import { UserAuthService } from '../service/user-auth.service';
import { UserService } from '../service/user.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  provinceState$!: Observable<AppState<CustomResponse>>;
  readonly DataState = DataState;
  banners = [
    {
      img: 'https://res.cloudinary.com/cuadong/image/upload/v1671897180/tlcn/promotion-1_o1xu5f.jpg',
    },
    {
      img: 'https://res.cloudinary.com/cuadong/image/upload/v1671897180/tlcn/promotion-2_wjnpmg.jpg',
    },
    {
      img: 'https://res.cloudinary.com/cuadong/image/upload/v1671897323/tlcn/promotion-3_fhqwvl.jpg',
    },
  ];
  promotions = [
    {
      title: 'Tiết kiệm 15% với Ưu Đãi Cuối Năm',
      desc: 'Thực hiện thêm một chuyến đi trong danh sách điểm đến của bạn',
      img: 'https://res.cloudinary.com/cuadong/image/upload/v1671898134/tlcn/pormotion-6_ltjebs.jpg',
    },
    {
      title: 'Đổi gió một thời gian',
      desc: 'Tận hưởng sự tự do với kỳ nghỉ theo tháng trên NPBOOKING',
      img: 'https://res.cloudinary.com/cuadong/image/upload/v1671898134/tlcn/promotion-5_jqamla.jpg',
    },
    {
      title: 'Năm mới, hành trình mới',
      desc: 'Tiết kiệm từ 15% khi đặt và lưu trú trước 31/3/2023',
      img: 'https://res.cloudinary.com/cuadong/image/upload/v1671898134/tlcn/promotion-4_huoqpm.jpg',
    },
  ];
  rangeDates: Date[];
  responsiveOptions;
  items: MenuItem[] = [];
  activeItem!: MenuItem;
  provinces: any;
  provinceId = 2;
  numPeople = 1;
  constructor(
    private provinceService: ProvinceService,
    private readonly activatedRoute: ActivatedRoute,
    private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router
  ) {
    this.provinceService.getAll$.subscribe((data) => {
      this.provinces = data.data;
    });
    let tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    this.rangeDates = [new Date(), tomorrow];
    this.responsiveOptions = [
      {
        breakpoint: '1024px',
        numVisible: 3,
        numScroll: 3,
      },
      {
        breakpoint: '768px',
        numVisible: 2,
        numScroll: 2,
      },
      {
        breakpoint: '560px',
        numVisible: 1,
        numScroll: 1,
      },
    ];
  }
  ngOnInit(): void {
    this.items = [
      { label: 'Home', icon: 'pi pi-fw pi-home' },
      { label: 'Calendar', icon: 'pi pi-fw pi-calendar' },
      { label: 'Edit', icon: 'pi pi-fw pi-pencil' },
      { label: 'Documentation', icon: 'pi pi-fw pi-file' },
      { label: 'Settings', icon: 'pi pi-fw pi-cog' },
    ];
    this.activeItem = this.items[0];

    this.provinceState$ = this.provinceService.getAll$.pipe(
      map((response) => {
        return { dataState: DataState.LOADED_STATE, appData: response };
      }),
      startWith({ dataState: DataState.LOADING_STATE }),
      catchError((error: string) => {
        return of({ dataState: DataState.ERROR_STATE, error });
      })
    );
  }
  search(): void {
    const date1 = this.rangeDates[0];
    const date2 = this.rangeDates[1];
    const datein = [
      date1.getFullYear(),
      (date1.getMonth() + 1).toString().padStart(2, '0'),
      date1.getDate().toString().padStart(2, '0'),
    ].join('-');
    const dateout = [
      date2.getFullYear(),
      (date2.getMonth() + 1).toString().padStart(2, '0'),
      date2.getDate().toString().padStart(2, '0'),
    ].join('-');
    console.log(this.provinceId, datein, dateout, this.numPeople);
    this.router.navigateByUrl(`/hotel/${this.provinceId}`, {
      state: { dateIn: datein, dateOut: dateout, numPeo: this.numPeople },
    });
  }
  private getDayDefault() {
    const date = new Date();
    const datein = [
      date.getFullYear(),
      (date.getMonth() + 1).toString().padStart(2, '0'),
      date.getDate().toString().padStart(2, '0'),
    ].join('-');
    const dateout = [
      date.getFullYear(),
      (date.getMonth() + 1).toString().padStart(2, '0'),
      (date.getDate() + 1).toString().padStart(2, '0'),
    ].join('-');
    return { datein, dateout };
  }
}
