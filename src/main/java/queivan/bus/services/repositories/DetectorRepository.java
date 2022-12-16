package queivan.bus.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import queivan.bus.domains.detector.Detector;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DetectorRepository extends JpaRepository<Detector, UUID> {
    Optional<Detector> findByMAC(String mac);

    boolean existsByMAC(String mac);
}
