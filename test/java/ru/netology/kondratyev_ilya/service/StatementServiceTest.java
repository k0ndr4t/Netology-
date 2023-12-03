package ru.netology.kondratyev_ilya.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.kondratyev_ilya.OperationHistoryApiApplicationTest;
import ru.netology.kondratyev_ilya.domain.Customer;
import ru.netology.kondratyev_ilya.domain.operation.Operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatementServiceTest extends OperationHistoryApiApplicationTest{
    @Autowired
    StatementService statementService;

    @Test
    public void saveOperationsTest(){
        Operation operation = new Operation(1, 6760, "RUB", "Yn", new Customer(2,"Kostya"));
        statementService.saveOperation(operation);
        String operations = statementService.getOperations();
        assertEquals("{2=[Operation{№:1, sum=6760, currency='RUB', merchant='Yn'}]}", operations);
    }

    @Test
    public void removeOperationTest(){
        Operation operation = new Operation(1, 2400, "USD", "Yn", new Customer(2,"Kostya"));
        statementService.saveOperation(operation);
        statementService.removeOperationsOnCustomerId(2);
        assertEquals("{}", statementService.getOperations());
    }

    @Test
    public void getOperations(){
        Operation operation = new Operation(1, 1000, "RUB", "Yn", new Customer(2,"Kostya"));
        statementService.saveOperation(operation);
        String operations = statementService.getOperations();
        assertEquals("{2=[Operation{№:1, sum=1000, currency='RUB', merchant='Yn'}]}", operations);
    }
}
