package org.n11_bootcamp;

import org.n11_bootcamp.entity.Customer;
import org.n11_bootcamp.entity.Invoice;
import org.n11_bootcamp.service.CustomerService;
import org.n11_bootcamp.service.impl.CustomerServiceImpl;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // Creation of the service class
        CustomerService service = new CustomerServiceImpl();

        // Creation of customers and invoices
        Customer customer1 = new Customer("Sam", "Transport", LocalDate.of(2023, 6, 1));
        customer1.addInvoice(new Invoice(1200.0, LocalDate.of(2024, 6, 10)));
        customer1.addInvoice(new Invoice(2000.0, LocalDate.of(2024, 6, 15)));

        Customer customer2 = new Customer("Porter", "Retail", LocalDate.of(2023, 6, 1));
        customer2.addInvoice(new Invoice(1500.0, LocalDate.of(2024, 6, 5)));
        customer2.addInvoice(new Invoice(1800.0, LocalDate.of(2024, 6, 20)));

        Customer customer3 = new Customer("Bridges", "Finance", LocalDate.of(2023, 5, 1));
        customer3.addInvoice(new Invoice(1000.0, LocalDate.of(2024, 5, 20)));
        customer3.addInvoice(new Invoice(900.0, LocalDate.of(2024, 6, 25)));

        Customer customer4 = new Customer("Charlie", "IT", LocalDate.of(2023, 6, 1));
        customer4.addInvoice(new Invoice(500.0, LocalDate.of(2024, 6, 21)));
        customer4.addInvoice(new Invoice(450.0, LocalDate.of(2024, 7, 23)));

        // Addition of the customer to the service
        service.addCustomer(customer1);
        service.addCustomer(customer2);
        service.addCustomer(customer3);
        service.addCustomer(customer4);

        // Listing of all customers
        System.out.println("All Customers:");
        service.getAllCustomers().forEach(customer -> System.out.println(customer.getName()));

        // Addition of new customer
        Customer newCustomer = new Customer("Mads", "Healthcare", LocalDate.of(2023, 6, 1));
        service.addCustomer(newCustomer);

        // Listing of the customers with the letter 'C' in them
        System.out.println("\nCustomers Containing Letter 'C':");
        service.getCustomersWithNameContainingC().forEach(customer -> System.out.println(customer.getName()));

        // Listing the total amount of invoices of customers who registered in June
        System.out.println("\nTotal Invoice Amounts of Customers Registered in June:");
        service.getCustomersRegisteredInMonth(6).forEach(customer -> {
            Double totalAmount = customer.getInvoices().stream()
                    .mapToDouble(Invoice::getAmount)
                    .sum();
            System.out.println(customer.getName() + ": " + totalAmount);
        });

        // Listing all invoices in the system
        System.out.println("\nAll Invoices in the System:");
        service.getAllInvoices().forEach(invoice -> System.out.println(invoice.getAmount()));

        // Listing invoices over $1500 in the system
        System.out.println("\nInvoices over $1500:");
        service.getInvoicesAbove(1500.0).forEach(invoice -> System.out.println(invoice.getAmount()));

        // Calculating the average of invoices over $1500 in the system
        System.out.println("\nAverage of Invoices Above $1500:");
        Double averageAbove1500 = service.getAverageInvoiceAmountAbove(1500.0);
        System.out.println(averageAbove1500);

        // Listing the names of customers with invoices under $500 in the system
        System.out.println("\nNames of Customers with Invoices Under $500:");
        service.getCustomersWithInvoicesBelow(500.0).forEach(System.out::println);

        // Listing the sector in which the companies whose average invoices for June are below 750 are located.
        System.out.println("\nSectors of Companies with June Average Invoices Below 750:");
        service.getCustomerSectorsWithInvoiceAverageBelow(6, 750.0).forEach(System.out::println);
    }
}