package cft.task.shop.service;

import cft.task.shop.model.Monitor;
import cft.task.shop.repository.MonitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorService {
    private final MonitorRepository monitorRepository;

    public MonitorService(MonitorRepository monitorRepository) {
        this.monitorRepository = monitorRepository;
    }

    public Monitor create(Monitor monitor) {
        return monitorRepository.save(monitor);
    }

    public Monitor update(Monitor monitor, int monitorId) throws Exception {
        if(monitorRepository.existsById(monitorId)) {
            monitor.setId(monitorId);
            return monitorRepository.save(monitor);
        }
        throw new Exception();
    }

    public List<Monitor> getAllMonitors() {
        return monitorRepository.findAll();
    }

    public Monitor getMonitor(int monitorId) {
        return monitorRepository.findById(monitorId).orElseThrow();
    }
}
