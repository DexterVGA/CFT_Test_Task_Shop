package cft.task.shop.controller;

import cft.task.shop.error.ErrorHandler;
import cft.task.shop.error.ValidationResult;
import cft.task.shop.model.Monitor;
import cft.task.shop.service.MonitorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MonitorController {
    private final MonitorService monitorService;

    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @PostMapping("/monitor")
    public ResponseEntity<?> create(@RequestBody Monitor monitor) {
        ValidationResult returnedRequestStatus = ErrorHandler.validateMonitor(monitor);
        if (returnedRequestStatus != ValidationResult.NO_ERROR) {
            return ResponseEntity.internalServerError().body(returnedRequestStatus);
        }
        return ResponseEntity.ok(monitorService.create(monitor));
    }

    @PutMapping("/monitor/{monitorId}")
    public ResponseEntity<?> update(@PathVariable(name = "monitorId") int monitorId,
                                    @RequestBody Monitor monitor) {
        ResponseEntity<?> response;
        ValidationResult returnedRequestStatus = ErrorHandler.validateMonitor(monitor);
        if (returnedRequestStatus == ValidationResult.NO_ERROR) {
            Monitor returnedMonitor;
            try {
                returnedMonitor = monitorService.update(monitor, monitorId);
                response = ResponseEntity.ok(returnedMonitor);
            } catch (Exception e) {
                response = ResponseEntity.internalServerError().body(ValidationResult.MONITOR_NOT_FOUND);
            }
        } else {
            response = ResponseEntity.internalServerError().body(returnedRequestStatus);
        }
        return response;
    }

    @GetMapping("/monitors")
    public List<Monitor> read() {
        return monitorService.getAllMonitors();
    }

    @GetMapping("/monitor/{monitorId}")
    public ResponseEntity<?> get(@PathVariable(name = "monitorId") int monitorId) {
        Monitor returnedMonitor;
        try {
            returnedMonitor = monitorService.getMonitor(monitorId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ValidationResult.MONITOR_NOT_FOUND);
        }
        return ResponseEntity.ok(returnedMonitor);
    }
}
