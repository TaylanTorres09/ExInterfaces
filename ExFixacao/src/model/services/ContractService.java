package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	public void processContract(Contract contract, Integer months) {
		double avgPerMonth = contract.getTotalValue() / months;
		for(int i = 1; i <= months; i++) {
			LocalDate date = contract.getDate().plusMonths(i);
			Double valuePerMonth = avgPerMonth + onlinePaymentService.interest(avgPerMonth, i);
			valuePerMonth += onlinePaymentService.paymentFee(valuePerMonth);
			
			contract.getInstallments().add(new Installment(date, valuePerMonth));
		}
	}

}
