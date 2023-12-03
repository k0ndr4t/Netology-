package ru.netology.kondratyev_ilya.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.kondratyev_ilya.OperationHistoryApiApplicationTest;
import ru.netology.kondratyev_ilya.domain.Customer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    CustomerService customerService;

    @Test
    public void getCustomersTest(){
        List<Customer> customers = customerService.getCustomers();
        Customer customer1 = customers.get(0);
        Customer customer2 = customers.get(1);
        assertEquals(1, customer1.getId());
        assertEquals("Spring", customer1.getName());
        assertEquals(2, customer2.getId());
        assertEquals("Boot", customer2.getName());
        assertEquals(2,customers.size());
    }

    @Test
    public void addCustomerTest(){
        customerService.addUser(3, "Ilya");
        List<Customer> customers = customerService.getCustomers();
        Customer customer = customers.get(customers.size()-1);
        assertEquals("Ilya", customer.getName());
    }
}
