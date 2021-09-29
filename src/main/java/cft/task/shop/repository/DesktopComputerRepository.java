package cft.task.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cft.task.shop.model.DesktopComputer;
import org.springframework.stereotype.Repository;

@Repository
public interface DesktopComputerRepository extends JpaRepository<DesktopComputer, Integer> {
}
