package cft.task.shop.controller;

import cft.task.shop.error.ErrorHandler;
import cft.task.shop.error.ValidationResult;
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
    public ResponseEntity<?> create(@RequestBody HardDiskDrive hardDisk) {
        ValidationResult returnedRequestStatus = ErrorHandler.validateHardDiskDrive(hardDisk);
        if (returnedRequestStatus != ValidationResult.NO_ERROR) {
            return ResponseEntity.internalServerError().body(returnedRequestStatus);
        }
        return ResponseEntity.ok(hardDiskDriveService.create(hardDisk));
    }

    @PutMapping("/disk/{diskId}")
    public ResponseEntity<?> update(@PathVariable(name = "diskId") int diskId,
                                    @RequestBody HardDiskDrive hardDisk) {
        ResponseEntity<?> response;
        ValidationResult returnedRequestStatus = ErrorHandler.validateHardDiskDrive(hardDisk);
        if(returnedRequestStatus == ValidationResult.NO_ERROR) {
            HardDiskDrive returnedHardDisk;
            try {
                returnedHardDisk = hardDiskDriveService.update(hardDisk, diskId);
                response = ResponseEntity.ok(returnedHardDisk);
            } catch (Exception e) {
                response = ResponseEntity.internalServerError().body(ValidationResult.HARD_DISK_DRIVE_NOT_FOUND);
            }
        } else {
            response = ResponseEntity.internalServerError().body(returnedRequestStatus);
        }
        return response;
    }

    @GetMapping("/disks")
    public List<HardDiskDrive> read() {
        return hardDiskDriveService.getAllHardDiskDrives();
    }

    @GetMapping("/disk/{diskId}")
    public ResponseEntity<?> get(@PathVariable(name = "diskId") int diskId) {
        HardDiskDrive returnedHardDisk;
        try {
            returnedHardDisk = hardDiskDriveService.getHardDiskDrive(diskId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ValidationResult.HARD_DISK_DRIVE_NOT_FOUND);
        }
        return ResponseEntity.ok(returnedHardDisk);
    }
}
