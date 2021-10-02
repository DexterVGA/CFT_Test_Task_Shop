package cft.task.shop.service;

import cft.task.shop.model.HardDiskDrive;
import cft.task.shop.repository.HardDiskDriveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HardDiskDriveService {
    private final HardDiskDriveRepository HardDiskDriveRepository;

    public HardDiskDriveService(HardDiskDriveRepository HardDiskDriveRepository) {
        this.HardDiskDriveRepository = HardDiskDriveRepository;
    }

    public HardDiskDrive create(HardDiskDrive hardDisk) {
        return HardDiskDriveRepository.save(hardDisk);
    }

    public HardDiskDrive update(HardDiskDrive hardDisk, int diskId) throws Exception {
        if (HardDiskDriveRepository.existsById(diskId)) {
            hardDisk.setId(diskId);
            return HardDiskDriveRepository.save(hardDisk);
        }
        throw new Exception();
    }

    public List<HardDiskDrive> getAllHardDiskDrives() {
        return HardDiskDriveRepository.findAll();
    }

    public HardDiskDrive getHardDiskDrive(int diskId) {
        return HardDiskDriveRepository.findById(diskId).orElseThrow();
    }
}
