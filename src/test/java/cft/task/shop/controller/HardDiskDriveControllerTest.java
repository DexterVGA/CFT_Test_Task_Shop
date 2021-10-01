package cft.task.shop.controller;

import cft.task.shop.error.ValidationResult;
import cft.task.shop.model.HardDiskDrive;
import cft.task.shop.service.HardDiskDriveService;
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
public class HardDiskDriveControllerTest {

    @Autowired
    HardDiskDriveController hardDiskDriveController;

    @MockBean
    HardDiskDriveService hardDiskDriveService;

    private final int serialNumber = 241254;
    private final String producer = "Western Digital";
    private final int price = 12;
    private final int numberProductUnitsInStock = 2564;
    private final int id = 1;
    private final int memorySizeGB = 1000;

    @Test
    void createSuccessful() {
        HardDiskDrive passedHardDisk = new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, null, memorySizeGB);
        HardDiskDrive expectedHardDisk = new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, id, memorySizeGB);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(expectedHardDisk);
        Mockito.when(hardDiskDriveService.create(passedHardDisk)).thenReturn(expectedHardDisk);

        ResponseEntity<?> actualResponse = hardDiskDriveController.create(passedHardDisk);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedAllFieldsAreEmpty() {
        HardDiskDrive passedHardDisk = new HardDiskDrive(0, null, 0, 0, null, 0);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.ALL_FIELDS_ARE_EMPTY);

        ResponseEntity<?> actualResponse = hardDiskDriveController.create(passedHardDisk);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedSomeFieldsAreEmpty() {
        HardDiskDrive passedHardDisk = new HardDiskDrive(0, null, 0, 0, null, memorySizeGB);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.SOME_FIELDS_ARE_EMPTY);

        ResponseEntity<?> actualResponse = hardDiskDriveController.create(passedHardDisk);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedSerialNumberIsEmpty() {
        HardDiskDrive passedHardDisk = new HardDiskDrive(0, producer, price, numberProductUnitsInStock, null, memorySizeGB);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.SERIAL_NUMBER_IS_EMPTY);

        ResponseEntity<?> actualResponse = hardDiskDriveController.create(passedHardDisk);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedProducerIsEmpty() {
        HardDiskDrive passedHardDisk = new HardDiskDrive(serialNumber, null, price, numberProductUnitsInStock, null, memorySizeGB);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.PRODUCER_IS_EMPTY);

        ResponseEntity<?> actualResponse = hardDiskDriveController.create(passedHardDisk);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedPriceIsEmpty() {
        HardDiskDrive passedHardDisk = new HardDiskDrive(serialNumber, producer, 0, numberProductUnitsInStock, null, memorySizeGB);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.PRICE_IS_EMPTY);

        ResponseEntity<?> actualResponse = hardDiskDriveController.create(passedHardDisk);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedPriceLessThanZero() {
        HardDiskDrive passedHardDisk = new HardDiskDrive(serialNumber, producer, -1, numberProductUnitsInStock, null, memorySizeGB);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.PRICE_LESS_THAN_ZERO);

        ResponseEntity<?> actualResponse = hardDiskDriveController.create(passedHardDisk);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedNumberProductUnitsInStockIsEmpty() {
        HardDiskDrive passedHardDisk = new HardDiskDrive(serialNumber, producer, price, 0, null, memorySizeGB);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.NUMBER_PRODUCT_UNITS_IN_STOCK_IS_EMPTY);

        ResponseEntity<?> actualResponse = hardDiskDriveController.create(passedHardDisk);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedNumberProductUnitsInStockLessThanZero() {
        HardDiskDrive passedHardDisk = new HardDiskDrive(serialNumber, producer, price, -1, null, memorySizeGB);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.NUMBER_PRODUCT_UNITS_IN_STOCK_LESS_THAN_ZERO);

        ResponseEntity<?> actualResponse = hardDiskDriveController.create(passedHardDisk);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedMemorySizeIsEmpty() {
        HardDiskDrive passedHardDisk = new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, null, 0);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.MEMORY_SIZE_IS_EMPTY);

        ResponseEntity<?> actualResponse = hardDiskDriveController.create(passedHardDisk);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void createFailedMemorySizeLessThanZero() {
        HardDiskDrive passedHardDisk = new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, null, -1);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.MEMORY_SIZE_LESS_THAN_ZERO);

        ResponseEntity<?> actualResponse = hardDiskDriveController.create(passedHardDisk);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void updateSuccessful() {
        HardDiskDrive updatedHardDisk = new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, null, memorySizeGB);
        HardDiskDrive expectedHardDisk = new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, id, memorySizeGB);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(expectedHardDisk);
        try {
            Mockito.when(hardDiskDriveService.update(updatedHardDisk, id)).thenReturn(expectedHardDisk);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseEntity<?> actualResponse = hardDiskDriveController.update(id, updatedHardDisk);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void updateFailedHardDiskDriveNotFound() {
        HardDiskDrive updatedHardDisk = new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, null, memorySizeGB);
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.HARD_DISK_DRIVE_NOT_FOUND);
        try {
            Mockito.when(hardDiskDriveService.update(updatedHardDisk, id)).thenThrow(Exception.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ResponseEntity<?> actualResponse = hardDiskDriveController.update(id, updatedHardDisk);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void readSuccessful() {
        List<HardDiskDrive> expectedList = new ArrayList<>();
        expectedList.add(new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, 1, memorySizeGB));
        expectedList.add(new HardDiskDrive(serialNumber + 1, producer, price + 1, numberProductUnitsInStock, 2, memorySizeGB + 10));
        Mockito.when(hardDiskDriveService.getAllHardDiskDrives()).thenReturn(expectedList);

        List<HardDiskDrive> actualList = hardDiskDriveController.read();

        assertEquals(expectedList, actualList);
    }

    @Test
    void getSuccessful() {
        HardDiskDrive expectedHardDisk = new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, id, memorySizeGB);
        ResponseEntity<?> expectedResponse = ResponseEntity.ok(expectedHardDisk);
        Mockito.when(hardDiskDriveService.getHardDiskDrive(id)).thenReturn(expectedHardDisk);

        ResponseEntity<?> actualResponse = hardDiskDriveController.get(id);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void getFailed() {
        ResponseEntity<?> expectedResponse = ResponseEntity.internalServerError().body(ValidationResult.HARD_DISK_DRIVE_NOT_FOUND);
        Mockito.when(hardDiskDriveService.getHardDiskDrive(id)).thenThrow(NoSuchElementException.class);

        ResponseEntity<?> actualResponse = hardDiskDriveController.get(id);

        assertEquals(expectedResponse, actualResponse);
    }
}
