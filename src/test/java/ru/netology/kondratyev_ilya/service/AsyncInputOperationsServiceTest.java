package ru.netology.kondratyev_ilya.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.netology.kondratyev_ilya.OperationHistoryApiApplicationTest;
import ru.netology.kondratyev_ilya.domain.Customer;
import ru.netology.kondratyev_ilya.domain.operation.Operation;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

public class AsyncInputOperationsServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private AsyncInputOperationsService asyncInputOperationService;
    @MockBean
    private StatementService statementService;

    @Value("${operation.processing.timeout}")
    private int timeout;

    @Test
    public void addOperationTest(){
        Operation operation = new Operation(1, 1000, "USD", "Shoko", new Customer(2,"ILYA"));
        int i = asyncInputOperationService.getOperations().size();
        asyncInputOperationService.addOperation(operation);
        int y = asyncInputOperationService.getOperations().size();
        assertEquals(i+1, y);
    }

    @Test
    public void getOperationsTest(){
        Operation operation = new Operation(1, 1000, "USD", "Shoko", new Customer(2,"ILYA"));
        asyncInputOperationService.addOperation(operation);
        Queue<Operation> operations = asyncInputOperationService.getOperations();
        assertEquals("[Operation{№:1, sum=6760, currency='RUB', merchant='Yn'}]", operations.toString());
    }

    @Test
    public void testStartProcessing_ShouldStartThreadForProcessingQueue() {
        asyncInputOperationService.startProcessing();
        assertTrue(asyncInputOperationService.getOperations().isEmpty());
    }

    @Test
    public void testProcessQueue_WithOperations_ShouldProcessOperation() {
        Operation operation = new Operation(1, 1000, "USD", "Shoko", new Customer(2,"ILYA"));
        asyncInputOperationService.addOperation(operation);

        asyncInputOperationService.processQueue();

        assertTrue(asyncInputOperationService.getOperations().isEmpty());
        verify(statementService).getOperations();
        verify(statementService).saveOperation(operation);
    }
}
