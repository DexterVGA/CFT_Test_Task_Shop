package cft.task.shop.error;

import cft.task.shop.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ValidationResult handleAnyException(Throwable exception) {
        return ValidationResult.UNKNOWN_ERROR;
    }

    public static ValidationResult validateProduct(Product someProduct) {
        if (someProduct.getSerialNumber() == 0 && someProduct.getProducer() == null &&
                someProduct.getPrice() == 0 && someProduct.getNumberProductUnitsInStock() == 0) {
            return ValidationResult.ALL_FIELDS_ARE_EMPTY;
        } else if (someProduct.getSerialNumber() == 0) {
            return ValidationResult.SERIAL_NUMBER_IS_EMPTY;
        } else if (someProduct.getProducer() == null) {
            return ValidationResult.PRODUCER_IS_EMPTY;
        } else if (someProduct.getPrice() == 0) {
            return ValidationResult.PRICE_IS_EMPTY;
        } else if (someProduct.getPrice() < 0) {
            return ValidationResult.PRICE_LESS_THAN_ZERO;
        } else if (someProduct.getNumberProductUnitsInStock() == 0) {
            return ValidationResult.NUMBER_PRODUCT_UNITS_IN_STOCK_IS_EMPTY;
        } else if (someProduct.getNumberProductUnitsInStock() < 0) {
            return ValidationResult.NUMBER_PRODUCT_UNITS_IN_STOCK_LESS_THAN_ZERO;
        }
        return ValidationResult.NO_ERROR;
    }

    public static ValidationResult validateDesktopComputer(DesktopComputer desktopComputer) {
        ValidationResult validatePersonStatus = validateProduct(desktopComputer);
        if (validatePersonStatus == ValidationResult.NO_ERROR) {
            if (desktopComputer.getFormFactor() == null) {
                return ValidationResult.FORM_FACTOR_IS_EMPTY;
            } else if (!desktopComputer.getFormFactor().equals("десктоп") &&
                    !desktopComputer.getFormFactor().equals("неттоп") &&
                    !desktopComputer.getFormFactor().equals("моноблок")) {
                return ValidationResult.FORM_FACTOR_IS_WRONG;
            }
        } else if (validatePersonStatus == ValidationResult.ALL_FIELDS_ARE_EMPTY) {
            if (desktopComputer.getFormFactor() == null) {
                return ValidationResult.ALL_FIELDS_ARE_EMPTY;
            } else {
                return ValidationResult.SOME_FIELDS_ARE_EMPTY;
            }
        } else {
            return validatePersonStatus;
        }
        return ValidationResult.NO_ERROR;
    }

    public static ValidationResult validateHardDiskDrive(HardDiskDrive hardDisk) {
        ValidationResult validatePersonStatus = validateProduct(hardDisk);
        if (validatePersonStatus == ValidationResult.NO_ERROR) {
            if (hardDisk.getMemorySizeGB() == 0) {
                return ValidationResult.MEMORY_SIZE_IS_EMPTY;
            } else if (hardDisk.getMemorySizeGB() < 0) {
                return ValidationResult.MEMORY_SIZE_LESS_THAN_ZERO;
            }
        } else if (validatePersonStatus == ValidationResult.ALL_FIELDS_ARE_EMPTY) {
            if (hardDisk.getMemorySizeGB() == 0) {
                return ValidationResult.ALL_FIELDS_ARE_EMPTY;
            } else {
                return ValidationResult.SOME_FIELDS_ARE_EMPTY;
            }
        } else {
            return validatePersonStatus;
        }
        return ValidationResult.NO_ERROR;
    }

    public static ValidationResult validateLaptop(Laptop laptop) {
        ValidationResult validatePersonStatus = validateProduct(laptop);
        if (validatePersonStatus == ValidationResult.NO_ERROR) {
            if (laptop.getSizeInInches() == 0) {
                return ValidationResult.LAPTOP_SIZE_IS_EMPTY;
            } else if (laptop.getSizeInInches() != 13 && laptop.getSizeInInches() != 14 &&
                    laptop.getSizeInInches() != 15 && laptop.getSizeInInches() != 17) {
                return ValidationResult.LAPTOP_SIZE_IS_WRONG;
            }
        } else if (validatePersonStatus == ValidationResult.ALL_FIELDS_ARE_EMPTY) {
            if (laptop.getSizeInInches() == 0) {
                return ValidationResult.ALL_FIELDS_ARE_EMPTY;
            } else {
                return ValidationResult.SOME_FIELDS_ARE_EMPTY;
            }
        } else {
            return validatePersonStatus;
        }
        return ValidationResult.NO_ERROR;
    }

    public static ValidationResult validateMonitor(Monitor monitor) {
        ValidationResult validatePersonStatus = validateProduct(monitor);
        if (validatePersonStatus == ValidationResult.NO_ERROR) {
            if (monitor.getDiagonal() == 0) {
                return ValidationResult.DIAGONAL_IS_EMPTY;
            } else if (monitor.getDiagonal() < 0) {
                return ValidationResult.DIAGONAL_LESS_THAN_ZERO;
            }
        } else if (validatePersonStatus == ValidationResult.ALL_FIELDS_ARE_EMPTY) {
            if (monitor.getDiagonal() == 0) {
                return ValidationResult.ALL_FIELDS_ARE_EMPTY;
            } else {
                return ValidationResult.SOME_FIELDS_ARE_EMPTY;
            }
        } else {
            return validatePersonStatus;
        }
        return ValidationResult.NO_ERROR;
    }
}
