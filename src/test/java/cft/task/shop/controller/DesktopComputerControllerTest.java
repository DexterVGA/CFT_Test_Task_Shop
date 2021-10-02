package cft.task.shop.controller;

import cft.task.shop.error.ValidationResult;
import cft.task.shop.model.DesktopComputer;
import cft.task.shop.service.DesktopComputerService;
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
public class DesktopComputerControllerTest {

    @Autowired
    private DesktopComputerController desktopComputerController;

    @MockBean
    private DesktopComputerService desktopComputerService;

    private final int serialNumber = 290921;
    private final String producer = "Apple";
    private final int price = 999;
    private final int numberProductUnitsInStock = 521;
    private final int id = 1;
    private final String formFactor = "моноблок";

    @Test
    void createSuccessful() {
        DesktopComputer passedComputer = new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, null, formFactor);
        DesktopComputer expectedComputer = new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, id, formFactor);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(expectedComputer);
        Mockito.when(desktopComputerService.create(passedComputer)).thenReturn(expectedComputer);

        ResponseEntity<?> actualResponse = desktopComputerController.create(passedComputer);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedAllFieldsAreEmpty() {
        DesktopComputer passedComputer = new DesktopComputer(0, null, 0, 0, null, null);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.ALL_FIELDS_ARE_EMPTY);

        ResponseEntity<?> actualResponse = desktopComputerController.create(passedComputer);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedSomeFieldsAreEmpty() {
        DesktopComputer passedComputer = new DesktopComputer(0, null, 0, 0, null, formFactor);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.SOME_FIELDS_ARE_EMPTY);

        ResponseEntity<?> actualResponse = desktopComputerController.create(passedComputer);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedSerialNumberIsEmpty() {
        DesktopComputer passedComputer = new DesktopComputer(0, producer, price, numberProductUnitsInStock, null, formFactor);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.SERIAL_NUMBER_IS_EMPTY);

        ResponseEntity<?> actualResponse = desktopComputerController.create(passedComputer);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedProducerIsEmpty() {
        DesktopComputer passedComputer = new DesktopComputer(serialNumber, null, price, numberProductUnitsInStock, null, formFactor);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.PRODUCER_IS_EMPTY);

        ResponseEntity<?> actualResponse = desktopComputerController.create(passedComputer);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedPriceIsEmpty() {
        DesktopComputer passedComputer = new DesktopComputer(serialNumber, producer, 0, numberProductUnitsInStock, null, formFactor);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.PRICE_IS_EMPTY);

        ResponseEntity<?> actualResponse = desktopComputerController.create(passedComputer);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedPriceLessThanZero() {
        DesktopComputer passedComputer = new DesktopComputer(serialNumber, producer, -1, numberProductUnitsInStock, null, formFactor);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.PRICE_LESS_THAN_ZERO);

        ResponseEntity<?> actualResponse = desktopComputerController.create(passedComputer);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedNumberProductUnitsInStockIsEmpty() {
        DesktopComputer passedComputer = new DesktopComputer(serialNumber, producer, price, 0, null, formFactor);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.NUMBER_PRODUCT_UNITS_IN_STOCK_IS_EMPTY);

        ResponseEntity<?> actualResponse = desktopComputerController.create(passedComputer);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedNumberProductUnitsInStockLessThanZero() {
        DesktopComputer passedComputer = new DesktopComputer(serialNumber, producer, price, -1, null, formFactor);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.NUMBER_PRODUCT_UNITS_IN_STOCK_LESS_THAN_ZERO);

        ResponseEntity<?> actualResponse = desktopComputerController.create(passedComputer);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedFormFactorIsEmpty() {
        DesktopComputer passedComputer = new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, null, null);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.FORM_FACTOR_IS_EMPTY);

        ResponseEntity<?> actualResponse = desktopComputerController.create(passedComputer);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedFormFactorIsWrong() {
        DesktopComputer passedComputer = new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, null, "someFactor");
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.FORM_FACTOR_IS_WRONG);

        ResponseEntity<?> actualResponse = desktopComputerController.create(passedComputer);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void updateSuccessful() {
        DesktopComputer updatedComputer = new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, null, formFactor);
        DesktopComputer expectedComputer = new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, id, formFactor);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(expectedComputer);
        try {
            Mockito.when(desktopComputerService.update(updatedComputer, id)).thenReturn(expectedComputer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseEntity<?> actualResponse = desktopComputerController.update(id, updatedComputer);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void updateFailedDesktopComputerNotFound() {
        DesktopComputer updatedComputer = new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, null, formFactor);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.DESKTOP_COMPUTER_NOT_FOUND);
        try {
            Mockito.when(desktopComputerService.update(updatedComputer, id)).thenThrow(Exception.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseEntity<?> actualResponse = desktopComputerController.update(id, updatedComputer);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void readSuccessful() {
        List<DesktopComputer> expectedList = new ArrayList<>();
        expectedList.add(new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, 1, formFactor));
        expectedList.add(new DesktopComputer(serialNumber + 1, producer, price + 1, numberProductUnitsInStock, 2, formFactor));
        Mockito.when(desktopComputerService.getAllDesktopComputers()).thenReturn(expectedList);

        List<DesktopComputer> actualList = desktopComputerController.read();

        assertEquals(expectedList, actualList);
    }

    @Test
    void getSuccessful() {
        DesktopComputer expectedComputer = new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, id, formFactor);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(expectedComputer);
        Mockito.when(desktopComputerService.getDesktopComputer(id)).thenReturn(expectedComputer);

        ResponseEntity<?> actualResponse = desktopComputerController.get(id);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getFailed() {
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.DESKTOP_COMPUTER_NOT_FOUND);
        Mockito.when(desktopComputerService.getDesktopComputer(id)).thenThrow(NoSuchElementException.class);

        ResponseEntity<?> actualResponse = desktopComputerController.get(id);

        assertEquals(expectedResponse, actualResponse);
    }
}
