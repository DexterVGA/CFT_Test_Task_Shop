package cft.task.shop.service;

import cft.task.shop.model.Monitor;
import cft.task.shop.repository.MonitorRepository;
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
public class MonitorServiceTest {

    @Autowired
    private MonitorService monitorService;

    @MockBean
    private MonitorRepository monitorRepository;

    private final int serialNumber = 5567;
    private final String producer = "Dell";
    private final int price = 51;
    private final int numberProductUnitsInStock = 470;
    private final int id = 1;
    private final int diagonal = 27;

    @Test
    void createSuccessful() {
        Monitor passedMonitor = new Monitor(serialNumber, producer, price, numberProductUnitsInStock, null, diagonal);
        Monitor expectedMonitor = new Monitor(serialNumber, producer, price, numberProductUnitsInStock, id, diagonal);
        Mockito.when(monitorRepository.save(passedMonitor)).thenReturn(expectedMonitor);

        Monitor actualMonitor = monitorService.create(passedMonitor);

        assertEquals(expectedMonitor, actualMonitor);
    }

    @Test
    void updateSuccessful() {
        Monitor passedMonitor = new Monitor(serialNumber, producer, price, numberProductUnitsInStock, null, diagonal);
        Monitor expectedMonitor = new Monitor(serialNumber, producer, price, numberProductUnitsInStock, id, diagonal);
        Mockito.when(monitorRepository.existsById(id)).thenReturn(true);
        Mockito.when(monitorRepository.save(passedMonitor)).thenReturn(expectedMonitor);

        Monitor actualMonitor = monitorService.create(passedMonitor);

        assertEquals(expectedMonitor, actualMonitor);
    }

    @Test
    void updateFailedMonitorNotFound() {
        Monitor passedMonitor = new Monitor(serialNumber, producer, price, numberProductUnitsInStock, null, diagonal);
        Mockito.when(monitorRepository.existsById(id)).thenReturn(false);

        assertThrows(Exception.class, () -> monitorService.update(passedMonitor, id));
    }

    @Test
    void getAllMonitorsSuccessful() {
        List<Monitor> expectedList = new ArrayList<>();
        expectedList.add(new Monitor(serialNumber, producer, price, numberProductUnitsInStock, 1, diagonal));
        expectedList.add(new Monitor(serialNumber + 1, producer, price + 1, numberProductUnitsInStock, 2, diagonal));
        Mockito.when(monitorRepository.findAll()).thenReturn(expectedList);

        List<Monitor> actualList = monitorService.getAllMonitors();

        assertEquals(expectedList, actualList);
    }
}
