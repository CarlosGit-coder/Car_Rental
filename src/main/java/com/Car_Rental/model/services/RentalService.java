package com.Car_Rental.model.services;

import com.Car_Rental.model.entities.CarRental;
import com.Car_Rental.model.entities.Invoice;

import java.time.Duration;

public class RentalService {

    private Double pricePerhour;
    private Double pricePerDay;

    private TaxService taxService;

    public RentalService() {}
    public RentalService(Double pricePerhour, Double pricePerDay,
                         TaxService taxService) {
        this.pricePerhour = pricePerhour;
        this.pricePerDay = pricePerDay;
        this.taxService = taxService;
    }


    public Double getPricePerhour() {
        return pricePerhour;
    }

    public void setPricePerhour(Double pricePerhour) {
        this.pricePerhour = pricePerhour;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public TaxService getTaxService() {
        return taxService;
    }

    public void setTaxService(TaxService taxService) {
        this.taxService = taxService;
    }

    public void processInvoice(CarRental carRental) {

        double minutes = Duration.between(carRental.getStart(),
                carRental.getFinish()).toMinutes();
        double hours = minutes / 60.0;

        double basicPayment;
        if (hours <= 12.0) {
            basicPayment = pricePerhour * Math.ceil(hours);
        } else {
            basicPayment = pricePerDay * Math.ceil(hours / 24);
        }

        double tax = taxService.tax(basicPayment);

        carRental.setInvoice(new Invoice(basicPayment, tax));
    }

}
