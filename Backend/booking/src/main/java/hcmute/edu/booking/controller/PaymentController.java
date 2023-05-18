package hcmute.edu.booking.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import hcmute.edu.booking.config.PaypalPaymentIntent;
import hcmute.edu.booking.config.PaypalPaymentMethod;
import hcmute.edu.booking.model.Booking;
import hcmute.edu.booking.model.DataResponse;
import hcmute.edu.booking.repository.BookingRepository;
import hcmute.edu.booking.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/api/Pay")
public class PaymentController {
    public static final String URL_PAYPAL_SUCCESS = "/success";
    public static final String URL_PAYPAL_CANCEL = "/cancel";
    @Autowired
    private PaypalService paypalService;
    @Autowired
    private BookingRepository bookingRepository;

    @PostMapping("")
    DataResponse pay(HttpServletRequest request, @RequestParam("price") double price, @RequestParam("bookingId") int bookingId) {
        String cancelUrl = "http://localhost:4200/pay" + URL_PAYPAL_CANCEL;
        String successUrl = "http://localhost:4200/pay" + URL_PAYPAL_SUCCESS + "?bookingId=" + bookingId;
        try {
            Payment payment = paypalService.createPayment(
                    price,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "NPBOOKING",
                    cancelUrl,
                    successUrl);
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return new DataResponse(links.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            throw new RuntimeException("Error when pay: " + e);
        }
        return new DataResponse(null);
    }

    @GetMapping(URL_PAYPAL_CANCEL)
    DataResponse cancelPay() {
        return new DataResponse("Cancel");
    }

    @GetMapping(URL_PAYPAL_SUCCESS)
    DataResponse successPay(@RequestParam("bookingId") int bookingId, @RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                Booking a = bookingRepository.findBookingById(bookingId);
                a.setStatus("Đã thanh toán");
                bookingRepository.save(a);
                return new DataResponse("Success");
            }
        } catch (PayPalRESTException e) {
            throw new RuntimeException("Success pay error:" + e);
        }
        return new DataResponse(null);
    }
}
