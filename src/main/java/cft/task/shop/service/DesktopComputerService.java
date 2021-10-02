package cft.task.shop.service;

import cft.task.shop.model.DesktopComputer;
import cft.task.shop.repository.DesktopComputerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesktopComputerService {
    private final DesktopComputerRepository desktopComputerRepository;

    public DesktopComputerService(DesktopComputerRepository desktopComputerRepository) {
        this.desktopComputerRepository = desktopComputerRepository;
    }

    public DesktopComputer create(DesktopComputer desktopComputer) {
        return desktopComputerRepository.save(desktopComputer);
    }

    public DesktopComputer update(DesktopComputer desktopComputer, int desktopId) throws Exception {
        if (desktopComputerRepository.existsById(desktopId)) {
            desktopComputer.setId(desktopId);
            return desktopComputerRepository.save(desktopComputer);
        }
        throw new Exception();
    }

    public List<DesktopComputer> getAllDesktopComputers() {
        return desktopComputerRepository.findAll();
    }

    public DesktopComputer getDesktopComputer(int desktopId) {
        return desktopComputerRepository.findById(desktopId).orElseThrow();
    }
}
