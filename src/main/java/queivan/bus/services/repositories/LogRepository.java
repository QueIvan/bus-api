package queivan.bus.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import queivan.bus.domains.log.Log;

import java.util.UUID;

@Repository
public interface LogRepository extends JpaRepository<Log, UUID> {
}
