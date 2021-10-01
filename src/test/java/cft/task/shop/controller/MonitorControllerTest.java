package cft.task.shop.controller;

import cft.task.shop.error.ValidationResult;
import cft.task.shop.model.Monitor;
import cft.task.shop.service.MonitorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MonitorControllerTest {

    @Autowired
    MonitorController monitorController;

    @MockBean
    MonitorService monitorService;

    private final int serialNumber = 5567;
    private final String producer = "Dell";
    private final int price = 51;
    private final int numberProductUnitsInStock = 470;
    private final int id = 1;
    private final int diagonal = 27;

    @Test
    void createSuccessful() {
        Monitor passedMonitor = new Monitor(serialNumber, producer, price, numberProductUnitsInStock, null, diagonal);
        Monitor expectedMonitor = new Monitor(serialNumber, producer, price, numberProductUnitsInStock, id, diagonal);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(expectedMonitor);
        Mockito.when(monitorService.create(passedMonitor)).thenReturn(expectedMonitor);

        ResponseEntity<?> actualResponse = monitorController.create(passedMonitor);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedAllFieldsAreEmpty() {
        Monitor passedMonitor = new Monitor(0, null, 0, 0, null, 0);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.ALL_FIELDS_ARE_EMPTY);

        ResponseEntity<?> actualResponse = monitorController.create(passedMonitor);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedSomeFieldsAreEmpty() {
        Monitor passedMonitor = new Monitor(0, null, 0, 0, null, diagonal);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.SOME_FIELDS_ARE_EMPTY);

        ResponseEntity<?> actualResponse = monitorController.create(passedMonitor);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedSerialNumberIsEmpty() {
        Monitor passedMonitor = new Monitor(0, producer, price, numberProductUnitsInStock, null, diagonal);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.SERIAL_NUMBER_IS_EMPTY);

        ResponseEntity<?> actualResponse = monitorController.create(passedMonitor);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedProducerIsEmpty() {
        Monitor passedMonitor = new Monitor(serialNumber, null, price, numberProductUnitsInStock, null, diagonal);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.PRODUCER_IS_EMPTY);

        ResponseEntity<?> actualResponse = monitorController.create(passedMonitor);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedPriceIsEmpty() {
        Monitor passedMonitor = new Monitor(serialNumber, producer, 0, numberProductUnitsInStock, null, diagonal);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.PRICE_IS_EMPTY);

        ResponseEntity<?> actualResponse = monitorController.create(passedMonitor);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedPriceLessThanZero() {
        Monitor passedMonitor = new Monitor(serialNumber, producer, -1, numberProductUnitsInStock, null, diagonal);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.PRICE_LESS_THAN_ZERO);

        ResponseEntity<?> actualResponse = monitorController.create(passedMonitor);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedNumberProductUnitsInStockIsEmpty() {
        Monitor passedMonitor = new Monitor(serialNumber, producer, price, 0, null, diagonal);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.NUMBER_PRODUCT_UNITS_IN_STOCK_IS_EMPTY);

        ResponseEntity<?> actualResponse = monitorController.create(passedMonitor);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedNumberProductUnitsInStockLessThanZero() {
        Monitor passedMonitor = new Monitor(serialNumber, producer, price, -1, null, diagonal);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.NUMBER_PRODUCT_UNITS_IN_STOCK_LESS_THAN_ZERO);

        ResponseEntity<?> actualResponse = monitorController.create(passedMonitor);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedMemorySizeIsEmpty() {
        Monitor passedMonitor = new Monitor(serialNumber, producer, price, numberProductUnitsInStock, null, 0);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.DIAGONAL_IS_EMPTY);

        ResponseEntity<?> actualResponse = monitorController.create(passedMonitor);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedMemorySizeLessThanZero() {
        Monitor passedMonitor = new Monitor(serialNumber, producer, price, numberProductUnitsInStock, null, -1);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.DIAGONAL_LESS_THAN_ZERO);

        ResponseEntity<?> actualResponse = monitorController.create(passedMonitor);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void updateSuccessful() {
        Monitor updatedMonitor = new Monitor(serialNumber, producer, price, numberProductUnitsInStock, null, diagonal);
        Monitor expectedMonitor = new Monitor(serialNumber, producer, price, numberProductUnitsInStock, id, diagonal);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(expectedMonitor);
        try {
            Mockito.when(monitorService.update(updatedMonitor, id)).thenReturn(expectedMonitor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseEntity<?> actualResponse = monitorController.update(id, updatedMonitor);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void updateFailedHardDiskDriveNotFound() {
        Monitor updatedMonitor = new Monitor(serialNumber, producer, price, numberProductUnitsInStock, null, diagonal);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.MONITOR_NOT_FOUND);
        try {
            Mockito.when(monitorService.update(updatedMonitor, id)).thenThrow(Exception.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseEntity<?> actualResponse = monitorController.update(id, updatedMonitor);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void readSuccessful() {
        List<Monitor> expectedList = new ArrayList<>();
        expectedList.add(new Monitor(serialNumber, producer, price, numberProductUnitsInStock, 1, diagonal));
        expectedList.add(new Monitor(serialNumber + 1, producer, price + 1, numberProductUnitsInStock, 2, diagonal + 2));
        Mockito.when(monitorService.getAllMonitors()).thenReturn(expectedList);

        List<Monitor> actualList = monitorController.read();

        assertEquals(expectedList, actualList);
    }

    @Test
    void getSuccessful() {
        Monitor expectedMonitor = new Monitor(serialNumber, producer, price, numberProductUnitsInStock, id, diagonal);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(expectedMonitor);
        Mockito.when(monitorService.getMonitor(id)).thenReturn(expectedMonitor);

        ResponseEntity<?> actualResponse = monitorController.get(id);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getFailed() {
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.MONITOR_NOT_FOUND);
        Mockito.when(monitorService.getMonitor(id)).thenThrow(NoSuchElementException.class);

        ResponseEntity<?> actualResponse = monitorController.get(id);

        assertEquals(expectedResponse, actualResponse);
    }
}
