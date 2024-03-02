package org.n11_bootcamp.service;

import org.n11_bootcamp.entity.Customer;
import org.n11_bootcamp.entity.Invoice;

import java.util.List;
import java.util.Set;

public interface CustomerService {
    void addCustomer(Customer customer);

    List<Customer> getAllCustomers();

    List<Customer> getCustomersWithNameContainingC();

    List<Customer> getCustomersRegisteredInMonth(int month);

    List<Invoice> getAllInvoices();

    List<Invoice> getInvoicesAbove(Double amount);

    Double getAverageInvoiceAmountAbove(Double amount);

    List<String> getCustomersWithInvoicesBelow(Double amount);

    Set<String> getCustomerSectorsWithInvoiceAverageBelow(int month, Double average);

}
