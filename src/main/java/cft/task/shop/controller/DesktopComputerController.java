package cft.task.shop.controller;

import cft.task.shop.model.DesktopComputer;
import cft.task.shop.service.DesktopComputerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Добавить по 4 метода:
 * 1. Добавить товар.
 * 2. Обновить товар.
 * 3. Получить все товары.
 * 4. Получить товар по Id.
 */
@RestController
public class DesktopComputerController {
    private final DesktopComputerService desktopComputerService;

    public DesktopComputerController(DesktopComputerService desktopComputerService) {
        this.desktopComputerService = desktopComputerService;
    }

    @PostMapping("/desktop")
    public ResponseEntity<?> create(@RequestBody DesktopComputer desktopComputer) {
        return ResponseEntity.ok(desktopComputerService.create(desktopComputer));
    }

    @PostMapping("/desktop/{desktopId}")
    public ResponseEntity<?> update(@PathVariable(name = "desktopId") int desktopId,
                                    DesktopComputer desktopComputer) throws Exception {
        return ResponseEntity.ok(desktopComputerService.update(desktopComputer, desktopId));
    }

    @GetMapping("/desktops")
    public List<DesktopComputer> read() {
        return desktopComputerService.getAllDesktopComputers();
    }

    @GetMapping("/desktop/{desktopId}")
    public ResponseEntity<?> get(@PathVariable(name = "desktopId") int desktopId) {
        return ResponseEntity.ok(desktopComputerService.getDesktopComputer(desktopId));
    }
}
