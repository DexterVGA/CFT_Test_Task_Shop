package cft.task.shop.controller;

import cft.task.shop.error.ErrorHandler;
import cft.task.shop.error.ValidationResult;
import cft.task.shop.model.DesktopComputer;
import cft.task.shop.service.DesktopComputerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DesktopComputerController {
    private final DesktopComputerService desktopComputerService;

    public DesktopComputerController(DesktopComputerService desktopComputerService) {
        this.desktopComputerService = desktopComputerService;
    }

    @PostMapping("/desktop")
    public ResponseEntity<?> create(@RequestBody DesktopComputer desktopComputer) {
        ValidationResult returnedRequestStatus = ErrorHandler.validateDesktopComputer(desktopComputer);
        if (returnedRequestStatus != ValidationResult.NO_ERROR) {
            return ResponseEntity.internalServerError().body(returnedRequestStatus);
        }
        return ResponseEntity.ok(desktopComputerService.create(desktopComputer));
    }

    @PutMapping("/desktop/{desktopId}")
    public ResponseEntity<?> update(@PathVariable(name = "desktopId") int desktopId,
                                    @RequestBody DesktopComputer desktopComputer) {
        ResponseEntity<?> response;
        ValidationResult returnedRequestStatus = ErrorHandler.validateDesktopComputer(desktopComputer);
        if (returnedRequestStatus == ValidationResult.NO_ERROR) {
            DesktopComputer returnedComputer;
            try {
                returnedComputer = desktopComputerService.update(desktopComputer, desktopId);
                response = ResponseEntity.ok(returnedComputer);
            } catch (Exception e) {
                response = ResponseEntity.internalServerError().body(ValidationResult.DESKTOP_COMPUTER_NOT_FOUND);
            }
        } else {
            response = ResponseEntity.internalServerError().body(returnedRequestStatus);
        }
        return response;
    }

    @GetMapping("/desktops")
    public List<DesktopComputer> read() {
        return desktopComputerService.getAllDesktopComputers();
    }

    @GetMapping("/desktop/{desktopId}")
    public ResponseEntity<?> get(@PathVariable(name = "desktopId") int desktopId) {
        DesktopComputer returnedComputer;
        try {
            returnedComputer = desktopComputerService.getDesktopComputer(desktopId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ValidationResult.DESKTOP_COMPUTER_NOT_FOUND);
        }
        return ResponseEntity.ok(returnedComputer);
    }
}
