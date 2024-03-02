package org.n11_bootcamp.entity;

import java.time.LocalDate;

public class Invoice {
    private Double amount;
    private LocalDate date;

    public Invoice(Double amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
