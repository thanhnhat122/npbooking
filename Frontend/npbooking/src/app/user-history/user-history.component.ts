import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { BookingDTO } from '../dto/booking.dto';
import { DetailBoookingDTO } from '../dto/detailbooking.dto';
import { Review } from '../model/review';
import { BookingService } from '../service/booking.service';
import { DetailBookingService } from '../service/detail-booking.service';
import { ReviewService } from '../service/review.service';
import { UserAuthService } from '../service/user-auth.service';

@Component({
  selector: 'app-user-history',
  templateUrl: './user-history.component.html',
  styleUrls: ['./user-history.component.css'],
  providers: [MessageService],
})
export class UserHistoryComponent {
  email: any;
  bookingDTOs!: BookingDTO[];
  detailBookingDTOs!: DetailBoookingDTO[];
  review : Review = <Review>{};
  constructor(
    private bookingService: BookingService,
    private route: Router,
    private userAuthService: UserAuthService,
    private detailBookingService: DetailBookingService,
    private messageService: MessageService,
    private reviewService: ReviewService
  ) {}
  ngOnInit(): void {
    this.email = this.userAuthService.getItem('email');
    this.bookingService.getBookingDTOByEmail(this.email).subscribe(
      (data:any) => {
        this.bookingDTOs = data['data']

      }
    )
  }
  getDetailBooking(bookingId: number){
    this.detailBookingService.getBookingDTO(bookingId).subscribe(
      (data:any) => {
        this.detailBookingDTOs = data['data']
      }
    )
  }
  insertReview(hotelId:number){
    console.log(hotelId)
    this.review.hotelId= hotelId
    this.review.userEmail = this.email;
    console.log(this.review)
    this.reviewService.createReview(this.review).subscribe(
      (data:any) =>{
        console.log(data['data']);
        this.messageService.add({
          severity: 'success',
          summary: 'Đánh giá',
          detail: 'Đánh giá thành công',
        });

      }
    )
  }


}
