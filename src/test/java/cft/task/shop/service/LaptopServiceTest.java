package cft.task.shop.service;

import cft.task.shop.model.Laptop;
import cft.task.shop.repository.LaptopRepository;
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
public class LaptopServiceTest {

    @Autowired
    private LaptopService laptopService;

    @MockBean
    private LaptopRepository laptopRepository;

    private final int serialNumber = 33562;
    private final String producer = "Asus";
    private final int price = 40;
    private final int numberProductUnitsInStock = 120;
    private final int id = 1;
    private final int sizeInInches = 15;

    @Test
    void createSuccessful() {
        Laptop passedLaptop = new Laptop(serialNumber, producer, price, numberProductUnitsInStock, null, sizeInInches);
        Laptop expectedLaptop = new Laptop(serialNumber, producer, price, numberProductUnitsInStock, id, sizeInInches);
        Mockito.when(laptopRepository.save(passedLaptop)).thenReturn(expectedLaptop);

        Laptop actualLaptop = laptopService.create(passedLaptop);

        assertEquals(expectedLaptop, actualLaptop);
    }

    @Test
    void updateSuccessful() {
        Laptop passedLaptop = new Laptop(serialNumber, producer, price, numberProductUnitsInStock, null, sizeInInches);
        Laptop expectedLaptop = new Laptop(serialNumber, producer, price, numberProductUnitsInStock, id, sizeInInches);
        Mockito.when(laptopRepository.existsById(id)).thenReturn(true);
        Mockito.when(laptopRepository.save(passedLaptop)).thenReturn(expectedLaptop);

        Laptop actualLaptop = laptopService.create(passedLaptop);

        assertEquals(expectedLaptop, actualLaptop);
    }

    @Test
    void updateFailedLaptopNotFound() {
        Laptop passedLaptop = new Laptop(serialNumber, producer, price, numberProductUnitsInStock, null, sizeInInches);
        Mockito.when(laptopRepository.existsById(id)).thenReturn(false);

        assertThrows(Exception.class, () -> laptopService.update(passedLaptop, id));
    }

    @Test
    void getAllLaptopsSuccessful() {
        List<Laptop> expectedList = new ArrayList<>();
        expectedList.add(new Laptop(serialNumber, producer, price, numberProductUnitsInStock, 1, sizeInInches));
        expectedList.add(new Laptop(serialNumber + 1, producer, price + 1, numberProductUnitsInStock, 2, sizeInInches));
        Mockito.when(laptopRepository.findAll()).thenReturn(expectedList);

        List<Laptop> actualList = laptopService.getAllLaptops();

        assertEquals(expectedList, actualList);
    }
}
