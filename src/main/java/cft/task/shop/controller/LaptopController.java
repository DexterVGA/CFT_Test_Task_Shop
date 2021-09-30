package cft.task.shop.controller;

import cft.task.shop.error.ErrorHandler;
import cft.task.shop.error.ValidationResult;
import cft.task.shop.model.Laptop;
import cft.task.shop.service.LaptopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LaptopController {
    private final LaptopService laptopService;

    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @PostMapping("/laptop")
    public ResponseEntity<?> create(@RequestBody Laptop laptop) {
        ValidationResult returnedRequestStatus = ErrorHandler.validateLaptop(laptop);
        if (returnedRequestStatus != ValidationResult.NO_ERROR) {
            return ResponseEntity.internalServerError().body(returnedRequestStatus);
        }
        return ResponseEntity.ok(laptopService.create(laptop));
    }

    @PutMapping("/laptop/{laptopId}")
    public ResponseEntity<?> update(@PathVariable(name = "laptopId") int laptopId,
                                    @RequestBody Laptop laptop) {
        ResponseEntity<?> response;
        ValidationResult returnedRequestStatus = ErrorHandler.validateLaptop(laptop);
        if (returnedRequestStatus == ValidationResult.NO_ERROR) {
            Laptop returnedLaptop;
            try {
                returnedLaptop = laptopService.update(laptop, laptopId);
                response = ResponseEntity.ok(returnedLaptop);
            } catch (Exception e) {
                response = ResponseEntity.internalServerError().body(ValidationResult.LAPTOP_NOT_FOUND);
            }
        } else {
            response = ResponseEntity.internalServerError().body(returnedRequestStatus);
        }
        return response;
    }

    @GetMapping("/laptops")
    public List<Laptop> read() {
        return laptopService.getAllLaptops();
    }

    @GetMapping("/laptop/{laptopId}")
    public ResponseEntity<?> get(@PathVariable(name = "laptopId") int laptopId) {
        Laptop returnedLaptop;
        try {
            returnedLaptop = laptopService.getLaptop(laptopId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ValidationResult.LAPTOP_NOT_FOUND);
        }
        return ResponseEntity.ok(returnedLaptop);
    }
}
