package cft.task.shop.controller;

import cft.task.shop.model.HardDiskDrive;
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
        return ResponseEntity.ok(laptopService.create(laptop));
    }

    @PostMapping("/laptop/{laptopId}")
    public ResponseEntity<?> update(@PathVariable(name = "laptopId") int laptopId,
                                    Laptop laptop) {
        return ResponseEntity.ok(laptopService.update(laptopId, laptop));
    }

    @GetMapping("/laptops")
    public List<HardDiskDrive> read() {
        return laptopService.getAllLaptops();
    }

    @GetMapping("/laptop/{laptopId}")
    public ResponseEntity<?> get(@PathVariable(name = "laptopId") int laptopId) {
        return ResponseEntity.ok(laptopService.getLaptop());
    }
}
