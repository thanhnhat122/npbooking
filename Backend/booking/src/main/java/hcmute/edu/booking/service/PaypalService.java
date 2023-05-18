package hcmute.edu.booking.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import hcmute.edu.booking.config.PaypalPaymentIntent;
import hcmute.edu.booking.config.PaypalPaymentMethod;

public interface PaypalService {
    Payment createPayment(Double total, String currency, PaypalPaymentMethod method, PaypalPaymentIntent intent, String description, String cancelUrl, String successUrl) throws PayPalRESTException;

    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
}
