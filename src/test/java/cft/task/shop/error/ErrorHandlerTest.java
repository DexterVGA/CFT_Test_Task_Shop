package cft.task.shop.error;

import cft.task.shop.model.DesktopComputer;
import cft.task.shop.model.HardDiskDrive;
import cft.task.shop.model.Laptop;
import cft.task.shop.model.Monitor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ErrorHandlerTest {

    private final int serialNumber = 290921;
    private final String producer = "Apple";
    private final int price = 999;
    private final int numberProductUnitsInStock = 521;
    private final int id = 1;
    private final String formFactor = "моноблок";
    private final int memorySizeGB = 1000;
    private final int diagonal = 27;
    private final int sizeInInches = 15;

    @Test
    void validateProductSuccessful() {
        DesktopComputer desktopComputer = new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, null, formFactor);
        ValidationResult expectedResult = ValidationResult.NO_ERROR;

        ValidationResult actualResult = ErrorHandler.validateProduct(desktopComputer);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateProductFailedAllFieldsAreEmpty() {
        DesktopComputer desktopComputer = new DesktopComputer(0, null, 0, 0, null, null);
        ValidationResult expectedResult = ValidationResult.ALL_FIELDS_ARE_EMPTY;

        ValidationResult actualResult = ErrorHandler.validateProduct(desktopComputer);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateProductFailedSerialNumberIsEmpty() {
        DesktopComputer desktopComputer = new DesktopComputer(0, producer, price, numberProductUnitsInStock, null, formFactor);
        ValidationResult expectedResult = ValidationResult.SERIAL_NUMBER_IS_EMPTY;

        ValidationResult actualResult = ErrorHandler.validateProduct(desktopComputer);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateProductFailedProducerIsEmpty() {
        DesktopComputer desktopComputer = new DesktopComputer(serialNumber, null, price, numberProductUnitsInStock, null, formFactor);
        ValidationResult expectedResult = ValidationResult.PRODUCER_IS_EMPTY;

        ValidationResult actualResult = ErrorHandler.validateProduct(desktopComputer);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateProductFailedPriceIsEmpty() {
        DesktopComputer desktopComputer = new DesktopComputer(serialNumber, producer, 0, numberProductUnitsInStock, null, formFactor);
        ValidationResult expectedResult = ValidationResult.PRICE_IS_EMPTY;

        ValidationResult actualResult = ErrorHandler.validateProduct(desktopComputer);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateProductFailedPriceLessThanZero() {
        DesktopComputer desktopComputer = new DesktopComputer(serialNumber, producer, -1, numberProductUnitsInStock, null, formFactor);
        ValidationResult expectedResult = ValidationResult.PRICE_LESS_THAN_ZERO;

        ValidationResult actualResult = ErrorHandler.validateProduct(desktopComputer);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateProductFailedNumberProductUnitsInStockIsEmpty() {
        DesktopComputer desktopComputer = new DesktopComputer(serialNumber, producer, price, 0, null, formFactor);
        ValidationResult expectedResult = ValidationResult.NUMBER_PRODUCT_UNITS_IN_STOCK_IS_EMPTY;

        ValidationResult actualResult = ErrorHandler.validateProduct(desktopComputer);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateProductFailedNumberProductUnitsInStockLessThanZero() {
        DesktopComputer desktopComputer = new DesktopComputer(serialNumber, producer, price, -1, null, formFactor);
        ValidationResult expectedResult = ValidationResult.NUMBER_PRODUCT_UNITS_IN_STOCK_LESS_THAN_ZERO;

        ValidationResult actualResult = ErrorHandler.validateProduct(desktopComputer);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateDesktopComputerSuccessful() {
        DesktopComputer desktopComputer = new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, null, formFactor);
        ValidationResult expectedResult = ValidationResult.NO_ERROR;

        ValidationResult actualResult = ErrorHandler.validateDesktopComputer(desktopComputer);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateDesktopComputerFailedFormFactorIsEmpty() {
        DesktopComputer desktopComputer = new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, null, null);
        ValidationResult expectedResult = ValidationResult.FORM_FACTOR_IS_EMPTY;

        ValidationResult actualResult = ErrorHandler.validateDesktopComputer(desktopComputer);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateDesktopComputerFailedFormFactorIsWrong() {
        DesktopComputer desktopComputer = new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, null, "someFormFactor");
        ValidationResult expectedResult = ValidationResult.FORM_FACTOR_IS_WRONG;

        ValidationResult actualResult = ErrorHandler.validateDesktopComputer(desktopComputer);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateDesktopComputerFailedSomeFieldsAreEmpty() {
        DesktopComputer desktopComputer = new DesktopComputer(0, null, 0, 0, null, formFactor);
        ValidationResult expectedResult = ValidationResult.SOME_FIELDS_ARE_EMPTY;

        ValidationResult actualResult = ErrorHandler.validateDesktopComputer(desktopComputer);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateHardDiskDriveSuccessful() {
        HardDiskDrive hardDiskDrive = new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, null, memorySizeGB);
        ValidationResult expectedResult = ValidationResult.NO_ERROR;

        ValidationResult actualResult = ErrorHandler.validateHardDiskDrive(hardDiskDrive);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateHardDiskDriveFailedMemorySizeIsEmpty() {
        HardDiskDrive hardDiskDrive = new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, null, 0);
        ValidationResult expectedResult = ValidationResult.MEMORY_SIZE_IS_EMPTY;

        ValidationResult actualResult = ErrorHandler.validateHardDiskDrive(hardDiskDrive);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateHardDiskDriveFailedMemorySizeLessThanZero() {
        HardDiskDrive hardDiskDrive = new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, null, -1);
        ValidationResult expectedResult = ValidationResult.MEMORY_SIZE_LESS_THAN_ZERO;

        ValidationResult actualResult = ErrorHandler.validateHardDiskDrive(hardDiskDrive);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateHardDiskDriveFailedSomeFieldsAreEmpty() {
        HardDiskDrive hardDiskDrive = new HardDiskDrive(0, null, 0, 0, null, memorySizeGB);
        ValidationResult expectedResult = ValidationResult.SOME_FIELDS_ARE_EMPTY;

        ValidationResult actualResult = ErrorHandler.validateHardDiskDrive(hardDiskDrive);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateLaptopSuccessful() {
        Laptop laptop = new Laptop(serialNumber, producer, price, numberProductUnitsInStock, null, sizeInInches);
        ValidationResult expectedResult = ValidationResult.NO_ERROR;

        ValidationResult actualResult = ErrorHandler.validateLaptop(laptop);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateLaptopFailedSizeIsEmpty() {
        Laptop laptop = new Laptop(serialNumber, producer, price, numberProductUnitsInStock, null, 0);
        ValidationResult expectedResult = ValidationResult.LAPTOP_SIZE_IS_EMPTY;

        ValidationResult actualResult = ErrorHandler.validateLaptop(laptop);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateLaptopFailedSizeIsWrong() {
        Laptop laptop = new Laptop(serialNumber, producer, price, numberProductUnitsInStock, null, -1);
        ValidationResult expectedResult = ValidationResult.LAPTOP_SIZE_IS_WRONG;

        ValidationResult actualResult = ErrorHandler.validateLaptop(laptop);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateLaptopFailedSomeFieldsAreEmpty() {
        Laptop laptop = new Laptop(0, null, 0, 0, null, sizeInInches);
        ValidationResult expectedResult = ValidationResult.SOME_FIELDS_ARE_EMPTY;

        ValidationResult actualResult = ErrorHandler.validateLaptop(laptop);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateMonitorSuccessful() {
        Monitor monitor = new Monitor(serialNumber, producer, price, numberProductUnitsInStock, null, diagonal);
        ValidationResult expectedResult = ValidationResult.NO_ERROR;

        ValidationResult actualResult = ErrorHandler.validateMonitor(monitor);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateMonitorFailedDiagonalIsEmpty() {
        Monitor monitor = new Monitor(serialNumber, producer, price, numberProductUnitsInStock, null, 0);
        ValidationResult expectedResult = ValidationResult.DIAGONAL_IS_EMPTY;

        ValidationResult actualResult = ErrorHandler.validateMonitor(monitor);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateMonitorFailedDiagonalLessThanZero() {
        Monitor monitor = new Monitor(serialNumber, producer, price, numberProductUnitsInStock, null, -1);
        ValidationResult expectedResult = ValidationResult.DIAGONAL_LESS_THAN_ZERO;

        ValidationResult actualResult = ErrorHandler.validateMonitor(monitor);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void validateMonitorFailedSomeFieldsAreEmpty() {
        Monitor monitor = new Monitor(0, null, 0, 0, null, diagonal);
        ValidationResult expectedResult = ValidationResult.SOME_FIELDS_ARE_EMPTY;

        ValidationResult actualResult = ErrorHandler.validateMonitor(monitor);

        assertEquals(expectedResult, actualResult);
    }
}
