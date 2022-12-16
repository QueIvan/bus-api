package queivan.bus.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import queivan.bus.domains.vehicle.Vehicle;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    Optional<Vehicle> findByPlate(String plate);

    boolean existsByPlate(String mac);

    Optional<Vehicle> findByIdAndPlate(UUID id, String plate);
}
