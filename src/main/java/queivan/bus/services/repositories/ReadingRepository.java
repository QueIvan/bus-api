package queivan.bus.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import queivan.bus.domains.reading.Reading;

import java.util.UUID;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, UUID> {
}
