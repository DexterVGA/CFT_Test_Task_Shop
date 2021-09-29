package cft.task.shop.service;

import cft.task.shop.model.Laptop;
import cft.task.shop.repository.LaptopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopService {
    private final LaptopRepository laptopRepository;

    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    public Laptop create(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    public Laptop update(Laptop laptop, int laptopId) throws Exception {
        if(laptopRepository.existsById(laptopId)) {
            laptop.setId(laptopId);
            return laptopRepository.save(laptop);
        }
        throw new Exception();
    }

    public List<Laptop> getAllLaptops() {
        return laptopRepository.findAll();
    }

    public Laptop getLaptop(int laptopId) {
        return laptopRepository.findById(laptopId).orElseThrow();
    }
}
