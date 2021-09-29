package cft.task.shop.controller;

import cft.task.shop.model.HardDiskDrive;
import cft.task.shop.service.HardDiskDriveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HardDiskDriveController {
    private final HardDiskDriveService hardDiskDriveService;

    public HardDiskDriveController(HardDiskDriveService hardDiskDriveService) {
        this.hardDiskDriveService = hardDiskDriveService;
    }

    @PostMapping("/disk")
    public ResponseEntity<?> create(@RequestBody HardDiskDrive hdd) {
        return ResponseEntity.ok(hardDiskDriveService.create(hdd));
    }

    @PostMapping("/disk/{diskId}")
    public ResponseEntity<?> update(@PathVariable(name = "diskId") int diskId,
                                    HardDiskDrive disk) {
        return ResponseEntity.ok(hardDiskDriveService.update(diskId, disk));
    }

    @GetMapping("/disks")
    public List<HardDiskDrive> read() {
        return hardDiskDriveService.getAllHardDiskDrives();
    }

    @GetMapping("/disk/{diskId}")
    public ResponseEntity<?> get(@PathVariable(name = "diskId") int diskId) {
        return ResponseEntity.ok(hardDiskDriveService.getDesktopComputer());
    }
}
