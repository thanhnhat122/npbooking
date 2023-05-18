import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PayService } from '../service/pay.service';
@Component({
  selector: 'app-payment-status',
  templateUrl: './payment-status.component.html',
  styleUrls: ['./payment-status.component.css'],
})
export class PaymentStatusComponent implements OnInit {
  status: string = 'cancel';
  text: string = '';
  constructor(
    private readonly route: ActivatedRoute,
    private payService: PayService
  ) {}
  ngOnInit(): void {
    this.status = this.route.snapshot.paramMap.get('status') as string;
    if (this.status) {
      if (this.status === 'success') {
        this.text = 'Thanh toán thành công';
        const bookingId = this.route.snapshot.queryParamMap.get(
          'bookingId'
        ) as string;
        const paymentId = this.route.snapshot.queryParamMap.get(
          'paymentId'
        ) as string;
        const payerId = this.route.snapshot.queryParamMap.get('PayerID') as string;
        this.payService
          .getSuccess$(+bookingId, paymentId, payerId)
          .subscribe((data) => console.log(data));
      } else {
        this.text = 'Thanh toán thất bại';
      }
    }
  }
}
