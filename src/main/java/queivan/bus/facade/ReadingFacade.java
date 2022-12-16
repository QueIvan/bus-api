package queivan.bus.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.bus.domains.reading.ReadingDto;
import queivan.bus.services.ReadingService;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ReadingFacade {
    private final ReadingService service;

    public List<ReadingDto> getAllReadings(String caller) {
        return service.getAllReadings(caller);
    }

    public ReadingDto getReadingByID(UUID id, String caller) {
        return service.getReadingByID(id, caller);
    }

    public ReadingDto createReading(ReadingDto dto, String caller) {
        return service.createReading(dto, caller);
    }

    public void deleteReading(UUID id, String caller) {
        service.deleteReading(id, caller);
    }
}
