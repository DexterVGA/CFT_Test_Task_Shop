package cft.task.shop.controller;

import cft.task.shop.error.ValidationResult;
import cft.task.shop.model.Laptop;
import cft.task.shop.service.LaptopService;
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
public class LaptopControllerTest {

    @Autowired
    private LaptopController laptopController;

    @MockBean
    private LaptopService laptopService;

    private final int serialNumber = 33562;
    private final String producer = "Asus";
    private final int price = 40;
    private final int numberProductUnitsInStock = 120;
    private final int id = 1;
    private final int sizeInInches = 15;

    @Test
    void createSuccessful() {
        Laptop passedLaptop = new Laptop(serialNumber, producer, price, numberProductUnitsInStock, null, sizeInInches);
        Laptop expectedLaptop = new Laptop(serialNumber, producer, price, numberProductUnitsInStock, id, sizeInInches);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(expectedLaptop);
        Mockito.when(laptopService.create(passedLaptop)).thenReturn(expectedLaptop);

        ResponseEntity<?> actualResponse = laptopController.create(passedLaptop);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedAllFieldsAreEmpty() {
        Laptop passedLaptop = new Laptop(0, null, 0, 0, null, 0);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.ALL_FIELDS_ARE_EMPTY);

        ResponseEntity<?> actualResponse = laptopController.create(passedLaptop);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedSomeFieldsAreEmpty() {
        Laptop passedLaptop = new Laptop(0, null, 0, 0, null, sizeInInches);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.SOME_FIELDS_ARE_EMPTY);

        ResponseEntity<?> actualResponse = laptopController.create(passedLaptop);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedSerialNumberIsEmpty() {
        Laptop passedLaptop = new Laptop(0, producer, price, numberProductUnitsInStock, null, sizeInInches);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.SERIAL_NUMBER_IS_EMPTY);

        ResponseEntity<?> actualResponse = laptopController.create(passedLaptop);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedProducerIsEmpty() {
        Laptop passedLaptop = new Laptop(serialNumber, null, price, numberProductUnitsInStock, null, sizeInInches);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.PRODUCER_IS_EMPTY);

        ResponseEntity<?> actualResponse = laptopController.create(passedLaptop);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedPriceIsEmpty() {
        Laptop passedLaptop = new Laptop(serialNumber, producer, 0, numberProductUnitsInStock, null, sizeInInches);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.PRICE_IS_EMPTY);

        ResponseEntity<?> actualResponse = laptopController.create(passedLaptop);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedPriceLessThanZero() {
        Laptop passedLaptop = new Laptop(serialNumber, producer, -1, numberProductUnitsInStock, null, sizeInInches);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.PRICE_LESS_THAN_ZERO);

        ResponseEntity<?> actualResponse = laptopController.create(passedLaptop);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedNumberProductUnitsInStockIsEmpty() {
        Laptop passedLaptop = new Laptop(serialNumber, producer, price, 0, null, sizeInInches);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.NUMBER_PRODUCT_UNITS_IN_STOCK_IS_EMPTY);

        ResponseEntity<?> actualResponse = laptopController.create(passedLaptop);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedNumberProductUnitsInStockLessThanZero() {
        Laptop passedLaptop = new Laptop(serialNumber, producer, price, -1, null, sizeInInches);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.NUMBER_PRODUCT_UNITS_IN_STOCK_LESS_THAN_ZERO);

        ResponseEntity<?> actualResponse = laptopController.create(passedLaptop);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedMemorySizeIsEmpty() {
        Laptop passedLaptop = new Laptop(serialNumber, producer, price, numberProductUnitsInStock, null, 0);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.LAPTOP_SIZE_IS_EMPTY);

        ResponseEntity<?> actualResponse = laptopController.create(passedLaptop);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedMemorySizeLessThanZero() {
        Laptop passedLaptop = new Laptop(serialNumber, producer, price, numberProductUnitsInStock, null, -1);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.LAPTOP_SIZE_IS_WRONG);

        ResponseEntity<?> actualResponse = laptopController.create(passedLaptop);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void updateSuccessful() {
        Laptop updatedLaptop = new Laptop(serialNumber, producer, price, numberProductUnitsInStock, null, sizeInInches);
        Laptop expectedLaptop = new Laptop(serialNumber, producer, price, numberProductUnitsInStock, id, sizeInInches);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(expectedLaptop);
        try {
            Mockito.when(laptopService.update(updatedLaptop, id)).thenReturn(expectedLaptop);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseEntity<?> actualResponse = laptopController.update(id, updatedLaptop);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void updateFailedHardDiskDriveNotFound() {
        Laptop updatedLaptop = new Laptop(serialNumber, producer, price, numberProductUnitsInStock, null, sizeInInches);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.LAPTOP_NOT_FOUND);
        try {
            Mockito.when(laptopService.update(updatedLaptop, id)).thenThrow(Exception.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseEntity<?> actualResponse = laptopController.update(id, updatedLaptop);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void readSuccessful() {
        List<Laptop> expectedList = new ArrayList<>();
        expectedList.add(new Laptop(serialNumber, producer, price, numberProductUnitsInStock, 1, sizeInInches));
        expectedList.add(new Laptop(serialNumber + 1, producer, price + 1, numberProductUnitsInStock, 2, sizeInInches + 2));
        Mockito.when(laptopService.getAllLaptops()).thenReturn(expectedList);

        List<Laptop> actualList = laptopController.read();

        assertEquals(expectedList, actualList);
    }

    @Test
    void getSuccessful() {
        Laptop expectedLaptop = new Laptop(serialNumber, producer, price, numberProductUnitsInStock, id, sizeInInches);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(expectedLaptop);
        Mockito.when(laptopService.getLaptop(id)).thenReturn(expectedLaptop);

        ResponseEntity<?> actualResponse = laptopController.get(id);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getFailed() {
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.LAPTOP_NOT_FOUND);
        Mockito.when(laptopService.getLaptop(id)).thenThrow(NoSuchElementException.class);

        ResponseEntity<?> actualResponse = laptopController.get(id);

        assertEquals(expectedResponse, actualResponse);
    }
}
