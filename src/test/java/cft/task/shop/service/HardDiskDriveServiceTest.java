package cft.task.shop.service;

import cft.task.shop.model.HardDiskDrive;
import cft.task.shop.repository.HardDiskDriveRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class HardDiskDriveServiceTest {

    @Autowired
    private HardDiskDriveService hardDiskDriveService;

    @MockBean
    private HardDiskDriveRepository hardDiskDriveRepository;

    private final int serialNumber = 241254;
    private final String producer = "Western Digital";
    private final int price = 12;
    private final int numberProductUnitsInStock = 2564;
    private final int id = 1;
    private final int memorySizeGB = 1000;

    @Test
    void createSuccessful() {
        HardDiskDrive passedHardDisk = new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, null, memorySizeGB);
        HardDiskDrive expectedHardDisk = new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, id, memorySizeGB);
        Mockito.when(hardDiskDriveRepository.save(passedHardDisk)).thenReturn(expectedHardDisk);

        HardDiskDrive actualHardDisk = hardDiskDriveService.create(passedHardDisk);

        assertEquals(expectedHardDisk, actualHardDisk);
    }

    @Test
    void updateSuccessful() {
        HardDiskDrive passedHardDisk = new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, null, memorySizeGB);
        HardDiskDrive expectedHardDisk = new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, id, memorySizeGB);
        Mockito.when(hardDiskDriveRepository.existsById(id)).thenReturn(true);
        Mockito.when(hardDiskDriveRepository.save(passedHardDisk)).thenReturn(expectedHardDisk);

        HardDiskDrive actualHardDisk = hardDiskDriveService.create(passedHardDisk);

        assertEquals(expectedHardDisk, actualHardDisk);
    }

    @Test
    void updateFailedUserNotFound() {
        HardDiskDrive passedHardDisk = new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, null, memorySizeGB);
        Mockito.when(hardDiskDriveRepository.existsById(id)).thenReturn(false);

        assertThrows(Exception.class, () -> hardDiskDriveService.update(passedHardDisk, id));
    }

    @Test
    void getAllDesktopComputersSuccessful() {
        List<HardDiskDrive> expectedList = new ArrayList<>();
        expectedList.add(new HardDiskDrive(serialNumber, producer, price, numberProductUnitsInStock, 1, memorySizeGB));
        expectedList.add(new HardDiskDrive(serialNumber + 1, producer, price + 1, numberProductUnitsInStock, 2, memorySizeGB));
        Mockito.when(hardDiskDriveRepository.findAll()).thenReturn(expectedList);

        List<HardDiskDrive> actualList = hardDiskDriveService.getAllHardDiskDrives();

        assertEquals(expectedList, actualList);
    }
}
