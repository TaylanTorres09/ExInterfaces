package Application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PayPalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Entre os dados do contrato:");
		System.out.print("Numero: ");
		Integer number = sc.nextInt();
		
		System.out.print("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.next(), fmt);
		
		System.out.print("Valor do contrato: ");
		Double totalValue = sc.nextDouble();
		
		Contract contract = new Contract(number, date, totalValue);
		
		System.out.print("Entre com o numero de parcelas: ");
		Integer numbersOfMonths = sc.nextInt();
		
		ContractService contractService = new ContractService(new PayPalService());
		contractService.processContract(contract, numbersOfMonths);
		
		System.out.println("Parcelas:");
		for(Installment installment: contract.getInstallments()) {
			System.out.println(installment.toString());
		}
		
		sc.close();

	}

}
