/* Uma locadora brasileira de carros cobra um valor por hora para locações de até 12
 horas. Porém, se a duração da locação ultrapassar 12 horas, a locação será cobrada
 com base em um valor diário. Além do valor da locação, é acrescido no preço o valor do
 imposto conforme regras do país que, no caso do Brasil, é 20%para valores até 100,00 ou
 15% para valores acima de 100,00. Fazer um programa que lê os dados da locação
 (modelo do carro, instante inicial e final da locação), bem como o valor por hora
 e o valor diário de locação. O programa deve então gerar a nota de pagamento
 (contendo valor da locação, valor do imposto e valor total do pagamento) e
 informar os dados na tela. */
package com.Car_Rental.application;

import com.Car_Rental.model.entities.CarRental;
import com.Car_Rental.model.entities.Vehicle;
import com.Car_Rental.model.services.BrazilTaxService;
import com.Car_Rental.model.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args)  {

        Scanner sc = new Scanner(System.in);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("----- ENTER THE RENTAL DETAILS -----");
        System.out.print("Car model: ");
        String carModel = sc.nextLine();
        System.out.print("Car pickup (dd/MM/yyyy hh:mm): ");
        LocalDateTime carPickup = LocalDateTime.parse(sc.nextLine(), dateFormatter);
        System.out.print("Car delivery (dd/MM/yyyy hh:mm): ");
        LocalDateTime carDelivery = LocalDateTime.parse(sc.nextLine(), dateFormatter);
        CarRental carRental = new CarRental(carPickup, carDelivery, new Vehicle(carModel));

        System.out.print("Enter price per hour: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Enter price per day: ");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerHour, pricePerDay,
                new BrazilTaxService());

        rentalService.processInvoice(carRental);

        System.out.println("----- INVOICE -----");
        System.out.println("Basic payment: " + carRental.getInvoice().getBasicPayment());
        System.out.println("Tax: " + carRental.getInvoice().getTax());
        System.out.println("Total payment: " + carRental.getInvoice().getTotalPayment());
    }
}
