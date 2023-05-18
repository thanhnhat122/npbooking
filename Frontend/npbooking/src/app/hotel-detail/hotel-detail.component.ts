import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { catchError, map, Observable, of, startWith } from 'rxjs';
import { DataState } from '../enum/data.state.enum';
import { AppState } from '../model/app-state';
import { CustomResponse } from '../model/custom.response';
import { BookingService } from '../service/booking.service';
import { DetailBookingService } from '../service/detail-booking.service';
import { HotelService } from '../service/hotel.service';
import { ImageService } from '../service/image.service';
import { PayService } from '../service/pay.service';
import { ReviewService } from '../service/review.service';
import { RoomService } from '../service/room.service';
import { UserAuthService } from '../service/user-auth.service';

@Component({
  selector: 'app-hotel-detail',
  templateUrl: './hotel-detail.component.html',
  styleUrls: ['./hotel-detail.component.css'],
  providers: [MessageService],
})
export class HotelDetailComponent implements OnInit {
  imgHotelState$!: Observable<AppState<CustomResponse>>;
  imgRoomState$!: Observable<AppState<CustomResponse>>;
  hotelState$!: Observable<AppState<CustomResponse>>;
  roomHotelState$!: Observable<AppState<CustomResponse>>;
  roomState$!: Observable<AppState<CustomResponse>>;
  reviewState$!: Observable<AppState<CustomResponse>>;
  bookingState$!: Observable<AppState<CustomResponse>>;
  detailBookingState$!: Observable<AppState<CustomResponse>>;

  readonly DataState = DataState;
  readMore: boolean;
  modalRoomDetail: boolean = false;
  hotelId: string;
  rangeDates: Date[];
  displayImgHotel: boolean;
  priceTotal = [
    {
      id: 0,
      type: '',
      num: 0,
      price: 0,
      discounted: 0,
    },
  ];
  finalPrice: number;
  finalDiscounted: number;
  numRoom = [{}];
  isLogged: boolean;
  showReviews: boolean;
  score: number = 0;
  numReview: number = 0;
  textReview: string = 'Chưa xếp hạng';
  provinceId: string;
  constructor(
    private readonly route: ActivatedRoute,
    private router: Router,
    private payService: PayService,
    private imageService: ImageService,
    private hotelService: HotelService,
    private roomService: RoomService,
    private userAuthService: UserAuthService,
    private reviewService: ReviewService,
    private bookingService: BookingService,
    private detailBookingService: DetailBookingService,
    private messageService: MessageService
  ) {
    this.showReviews = false;
    this.isLogged = this.userAuthService.isLogged();
    this.readMore = false;
    this.finalPrice = 0;
    this.finalDiscounted = 0;
    let tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    this.rangeDates = [new Date(), tomorrow];
    this.hotelId = this.route.snapshot.paramMap.get('hotelId') as string;
    this.provinceId = this.route.snapshot.paramMap.get('provinceId') as string;
    this.displayImgHotel = false;
  }
  ngOnInit(): void {
    this.imgHotelState$ = this.imageService
      .getAllImgByHotelId$(this.hotelId)
      .pipe(
        map((response) => {
          return { dataState: DataState.LOADED_STATE, appData: response };
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error });
        })
      );
    this.hotelState$ = this.hotelService.getById$(this.hotelId).pipe(
      map((response) => {
        return { dataState: DataState.LOADED_STATE, appData: response };
      }),
      startWith({ dataState: DataState.LOADING_STATE }),
      catchError((error: string) => {
        return of({ dataState: DataState.ERROR_STATE, error });
      })
    );
    this.reviewState$ = this.reviewService
      .getAllReviewByHotelId$(this.hotelId)
      .pipe(
        map((response) => {
          for (let i of response.data) {
            this.score += i.rate;
          }
          this.score /= response.data.length;
          this.numReview = response.data.length;
          if (this.score > 9) {
            this.textReview = 'Tuyệt hảo';
          } else if (this.score > 8) {
            this.textReview = 'Rất tốt';
          } else if (this.score > 7) {
            this.textReview = 'Tốt';
          } else if (this.score > 6) {
            this.textReview = 'Dễ chịu';
          }
          return { dataState: DataState.LOADED_STATE, appData: response };
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error });
        })
      );
    this.search();
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
    this.roomHotelState$ = this.roomService
      .getAllRoomByHotelId$(this.hotelId, datein, dateout)
      .pipe(
        map((response) => {
          return { dataState: DataState.LOADED_STATE, appData: response };
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error });
        })
      );
  }
  totalPrice(
    price: number,
    discount: number,
    id: number,
    type: string,
    event: any
  ) {
    const i = this.priceTotal.findIndex((e) => e.id === id);
    const element = {
      id: id,
      type: type,
      num: +event.target.value,
      price: price,
      discounted: discount,
    };
    if (i > -1) {
      this.priceTotal[i] = element;
    } else {
      this.priceTotal.push(element);
    }
    console.log(this.priceTotal);
    this.finalPrice = this.priceTotal
      .filter((i) => i.num > 0)
      .reduce((sum, num) => sum + num.price * num.num, 0);
    this.finalDiscounted = this.priceTotal
      .filter((i) => i.num > 0)
      .reduce((sum, num) => sum + num.discounted * num.num, 0);
  }
  showReview(): void {
    this.showReviews = true;
    this.reviewState$ = this.reviewService
      .getAllReviewByHotelId$(this.hotelId)
      .pipe(
        map((response) => {
          return { dataState: DataState.LOADED_STATE, appData: response };
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error });
        })
      );
  }
  async booking() {
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
    const payload = {
      hotelId: this.hotelId,
      dateIn: datein,
      dateOut: dateout,
      userEmail: this.userAuthService.getItem('email'),
      sumPrice: this.finalDiscounted,
      status: 'Chờ xác nhận',
    };
    const booking = await this.bookingService
      .insertBooking$(payload)
      .toPromise();

    const rooms = this.priceTotal
      .filter((i) => i.num > 0)
      .map((opt) => ({
        roomId: opt.id,
        quantity: opt.num,
      }));
    for (let room of rooms) {
      const p = {
        bookingId: booking?.data.id,
        roomId: room.roomId,
        quantity: room.quantity,
      };
      console.log(p);
      const detailBooking = await this.detailBookingService
        .insertDetailBooking$(p)
        .toPromise();
    }
    this.messageService.add({
      severity: 'success',
      summary: 'Thành công',
      detail: 'Chờ xác nhận từ khách sạn bạn nhé',
    });
  }
  showRoomDetailModal(roomId: string) {
    this.modalRoomDetail = true;
    this.imgRoomState$ = this.imageService
      .getAllImgRoom$(this.hotelId, roomId)
      .pipe(
        map((response) => {
          return { dataState: DataState.LOADED_STATE, appData: response };
        }),
        startWith({ dataState: DataState.LOADING_STATE }),
        catchError((error: string) => {
          return of({ dataState: DataState.ERROR_STATE, error });
        })
      );
    this.roomState$ = this.roomService.getById$(roomId).pipe(
      map((response) => {
        return { dataState: DataState.LOADED_STATE, appData: response };
      }),
      startWith({ dataState: DataState.LOADING_STATE }),
      catchError((error: string) => {
        return of({ dataState: DataState.ERROR_STATE, error });
      })
    );
  }
  redirectToLogin(): void {
    this.router.navigate(['/login'], {
      queryParams: { returnUrl: `/hotel/${this.provinceId}/${this.hotelId}` },
    });
  }
  async paypal() {
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
    const payload = {
      hotelId: this.hotelId,
      dateIn: datein,
      dateOut: dateout,
      userEmail: this.userAuthService.getItem('email'),
      sumPrice: this.finalDiscounted,
      status: 'Chờ xác nhận',
    };
    const booking = await this.bookingService
      .insertBooking$(payload)
      .toPromise();

    const rooms = this.priceTotal
      .filter((i) => i.num > 0)
      .map((opt) => ({
        roomId: opt.id,
        quantity: opt.num,
      }));
    for (let room of rooms) {
      const p = {
        bookingId: booking?.data.id,
        roomId: room.roomId,
        quantity: room.quantity,
      };
      console.log(p);
      const detailBooking = await this.detailBookingService
        .insertDetailBooking$(p)
        .toPromise();
    }
    const exchangeRate = 23675;
    this.payService
      .paypal$(this.finalDiscounted / exchangeRate, booking?.data.id)
      .subscribe((data) => (window.location.href = `${data.data}`));
  }
}
