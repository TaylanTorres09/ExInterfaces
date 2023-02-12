package model.services;

public class PayPalService implements OnlinePaymentService {

	public Double paymentFee(Double amount) {
		return amount*0.02;
	}
	
	public Double interest(Double amount, Integer months) {
		return amount*months*0.01;
	}

}
