package cft.task.shop.controller;

import cft.task.shop.model.HardDiskDrive;
import cft.task.shop.model.Laptop;
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
        return ResponseEntity.ok(monitorService.create(monitor));
    }

    @PostMapping("/monitor/{monitorId}")
    public ResponseEntity<?> update(@PathVariable(name = "monitorId") int monitorId,
                                    Monitor monitor) {
        return ResponseEntity.ok(monitorService.update(monitorId, monitor));
    }

    @GetMapping("/monitors")
    public List<Monitor> read() {
        return monitorService.getAllMonitors();
    }

    @GetMapping("/monitor/{monitorId}")
    public ResponseEntity<?> get(@PathVariable(name = "monitorId") int monitorId) {
        return ResponseEntity.ok(monitorService.getMonitor());
    }
}
