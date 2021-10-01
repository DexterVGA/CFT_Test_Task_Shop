package cft.task.shop.service;

import cft.task.shop.model.DesktopComputer;
import cft.task.shop.repository.DesktopComputerRepository;
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
public class DesktopComputerServiceTest {

    @Autowired
    private DesktopComputerService desktopComputerService;

    @MockBean
    private DesktopComputerRepository desktopComputerRepository;

    private final int serialNumber = 290921;
    private final String producer = "Apple";
    private final int price = 999;
    private final int numberProductUnitsInStock = 521;
    private final int id = 1;
    private final String formFactor = "моноблок";

    @Test
    void createSuccessful() {
        DesktopComputer passedComputer = new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, null, formFactor);
        DesktopComputer expectedComputer = new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, id, formFactor);
        Mockito.when(desktopComputerRepository.save(passedComputer)).thenReturn(expectedComputer);

        DesktopComputer actualComputer = desktopComputerService.create(passedComputer);

        assertEquals(expectedComputer, actualComputer);
    }

    @Test
    void updateSuccessful() {
        DesktopComputer passedComputer = new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, null, formFactor);
        DesktopComputer expectedComputer = new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, id, formFactor);
        Mockito.when(desktopComputerRepository.existsById(id)).thenReturn(true);
        Mockito.when(desktopComputerRepository.save(passedComputer)).thenReturn(expectedComputer);

        DesktopComputer actualComputer = desktopComputerService.create(passedComputer);

        assertEquals(expectedComputer, actualComputer);
    }

    @Test
    void updateFailedDesktopComputerNotFound() {
        DesktopComputer passedComputer = new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, null, formFactor);
        Mockito.when(desktopComputerRepository.existsById(id)).thenReturn(false);

        assertThrows(Exception.class, () -> desktopComputerService.update(passedComputer, id));
    }

    @Test
    void getAllDesktopComputersSuccessful() {
        List<DesktopComputer> expectedList = new ArrayList<>();
        expectedList.add(new DesktopComputer(serialNumber, producer, price, numberProductUnitsInStock, 1, formFactor));
        expectedList.add(new DesktopComputer(serialNumber + 1, producer, price + 1, numberProductUnitsInStock, 2, formFactor));
        Mockito.when(desktopComputerRepository.findAll()).thenReturn(expectedList);

        List<DesktopComputer> actualList = desktopComputerService.getAllDesktopComputers();

        assertEquals(expectedList, actualList);
    }
}
