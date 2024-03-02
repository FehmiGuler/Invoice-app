package org.n11_bootcamp.service.impl;

import org.n11_bootcamp.entity.Customer;
import org.n11_bootcamp.entity.Invoice;
import org.n11_bootcamp.service.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {
    private final List<Customer> customers;

    public CustomerServiceImpl() {
        customers = new ArrayList<>();
    }

    @Override
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @Override
    public List<Customer> getCustomersWithNameContainingC() {
        return customers.stream()
                .filter(customer -> customer.getName().contains("C"))
                .toList();
    }

    @Override
    public List<Customer> getCustomersRegisteredInMonth(int month) {
        return customers.stream()
                .filter(customer -> customer.getRegistrationDate().getMonthValue() == month)
                .toList();
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return customers.stream()
                .flatMap(customer -> customer.getInvoices().stream())
                .toList();
    }

    @Override
    public List<Invoice> getInvoicesAbove(Double amount) {
        return getAllInvoices().stream()
                .filter(invoice -> invoice.getAmount() > amount)
                .toList();
    }

    @Override
    public Double getAverageInvoiceAmountAbove(Double amount) {
        return getAllInvoices().stream()
                .filter(invoice -> invoice.getAmount() > amount)
                .mapToDouble(Invoice::getAmount)
                .average()
                .orElse(0);
    }

    @Override
    public List<String> getCustomersWithInvoicesBelow(Double amount) {
        return customers.stream()
                .filter(customer -> customer.getInvoices().stream()
                        .anyMatch(invoice -> invoice.getAmount() < amount))
                .map(Customer::getName)
                .toList();
    }

    @Override
    public Set<String> getCustomerSectorsWithInvoiceAverageBelow(int month, Double average) {
        return customers.stream()
                .filter(customer -> {
                    List<Invoice> invoices = customer.getInvoices().stream()
                            .filter(invoice -> invoice.getDate().getMonthValue() == month)
                            .toList();
                    if (!invoices.isEmpty()) {
                        Double invoiceAverage = invoices.stream()
                                .mapToDouble(Invoice::getAmount)
                                .average()
                                .orElse(0);
                        return invoiceAverage < average;
                    }
                    return false;
                })
                .map(Customer::getSector)
                .collect(Collectors.toSet());
    }
}
