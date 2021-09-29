package cft.task.shop.repository;

import cft.task.shop.model.HardDiskDrive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardDiskDriveRepository extends JpaRepository<HardDiskDrive, Integer> {
}
