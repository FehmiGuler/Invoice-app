package org.n11_bootcamp.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String sector;
    private LocalDate registrationDate;
    private List<Invoice> invoices;

    public Customer(String name, String sector, LocalDate localDate) {
        this.name = name;
        this.sector = sector;
        this.registrationDate = localDate;
        this.invoices = new ArrayList<>();
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public String getName() {
        return name;
    }

    public String getSector() {
        return sector;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
}
