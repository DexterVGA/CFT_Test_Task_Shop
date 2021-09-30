package cft.task.shop.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ValidationResult {
    ALL_FIELDS_ARE_EMPTY,
    SOME_FIELDS_ARE_EMPTY,
    SERIAL_NUMBER_IS_EMPTY,
    PRODUCER_IS_EMPTY,
    PRICE_IS_EMPTY,
    NUMBER_PRODUCT_UNITS_IN_STOCK_IS_EMPTY,
    FORM_FACTOR_IS_EMPTY,
    FORM_FACTOR_IS_WRONG,
    MEMORY_SIZE_IS_EMPTY,
    MEMORY_SIZE_LESS_THAN_ZERO,
    LAPTOP_SIZE_IS_EMPTY,
    LAPTOP_SIZE_IS_WRONG,
    DIAGONAL_IS_EMPTY,
    DIAGONAL_LESS_THAN_ZERO,
    DESKTOP_COMPUTER_NOT_FOUND,
    HARD_DISK_DRIVE_NOT_FOUND,
    LAPTOP_NOT_FOUND,
    MONITOR_NOT_FOUND,
    UNKNOWN_ERROR,
    NO_ERROR,
    ;

    @JsonSerialize
    String getCode() {
        return name();
    }
}
