package hcmute.edu.booking.service.impl;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import hcmute.edu.booking.config.PaypalPaymentIntent;
import hcmute.edu.booking.config.PaypalPaymentMethod;
import hcmute.edu.booking.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalServiceImpl implements PaypalService {
    @Autowired
    private APIContext apiContext;


    @Override
    public Payment createPayment(Double total, String currency, PaypalPaymentMethod method, PaypalPaymentIntent intent, String description, String cancelUrl, String successUrl) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format("%.2f", total));
        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);
//        ItemList itemList = new ItemList();
//        List<Item> items = new ArrayList<>();
//        List<DetailBookingDTO> cart = detailBookingService.getAllDetailBookingByBookingId(bookingId);
//        cart.forEach(p -> {
//            Item item = new Item();
//            item.setCurrency(currency);
//            item.setName(p.getRoom().getType());
//            item.setPrice(String.format("%.2f", (p.getRoom().getPrice() - p.getRoom().getDiscount()) * p.getQuantity()));
//            item.setQuantity(p.getQuantity() + "");
//            items.add(item);
//            itemList.setItems(items);
//        });


//        Transaction transaction = new Transaction();
//        transaction.setAmount(amount);
//        transaction.setItemList(itemList);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(method.toString());
//        payer.setPaymentMethod("paypal");
//        PayerInfo payerInfo = new PayerInfo();
//        payerInfo.setFirstName("NPBOOKING")
//                .setLastName("NPBOOKING")
//                .setEmail("sb-cq6cp24008300@business.example.com");
//        payer.setPayerInfo(payerInfo);

        Payment payment = new Payment();
        payment.setIntent(intent.toString());
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);
        apiContext.setMaskRequestId(true);
        return payment.create(apiContext);
    }

    @Override
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }
}
