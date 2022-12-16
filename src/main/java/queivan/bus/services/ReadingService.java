package queivan.bus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import queivan.bus.domains.detector.Detector;
import queivan.bus.domains.log.LogDto;
import queivan.bus.domains.reading.Reading;
import queivan.bus.domains.reading.ReadingDto;
import queivan.bus.exceptions.DetectorNotFoundException;
import queivan.bus.exceptions.ReadingNotFoundException;
import queivan.bus.mappers.ReadingMapper;
import queivan.bus.services.repositories.DetectorRepository;
import queivan.bus.services.repositories.ReadingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReadingService {
    private final ReadingRepository repository;
    private final DetectorRepository detectorRepository;
    private final ReadingMapper mapper;
    private final LogService logger;

    public List<ReadingDto> getAllReadings(String caller) {
        List<Reading> feteched = repository.findAll();
        logger.info(
                LogDto.builder()
                        .content("Zwrócono wszystkie oczyty")
                        .timestamp(LocalDateTime.now())
                        .caller(caller)
                        .build()
        );
        return mapper.mapReadingListToReadingDtoList(feteched);
    }

    public ReadingDto getReadingByID(UUID id, String caller) {
        Reading fetched = repository.findById(id).orElseThrow(ReadingNotFoundException::new);
        logger.info(
                LogDto.builder()
                        .content(String.format("Zwrócono oczyt o id: %s", id))
                        .timestamp(LocalDateTime.now())
                        .caller(caller)
                        .build()
        );
        return mapper.mapReadingToReadingDto(fetched);
    }

    public ReadingDto createReading(ReadingDto dto, String caller) {
        Reading mapped = mapper.mapReadingDtoToReading(dto);
        Detector d = detectorRepository.findByMAC(dto.getDetector().getMAC()).orElseThrow(DetectorNotFoundException::new);
        mapped.setDetector(d);
        mapped.setTimestamp(LocalDateTime.now());
        Reading saved = repository.save(mapped);
        logger.info(
                LogDto.builder()
                        .content(String.format("Wpisano nowy oczyt o id: %s", saved.getId()))
                        .timestamp(LocalDateTime.now())
                        .caller(caller)
                        .build()
        );
        return mapper.mapReadingToReadingDto(saved);
    }

    public void deleteReading(UUID id, String caller) {
        doesReadingNotExist(id);
        repository.deleteById(id);
        logger.info(
                LogDto.builder()
                        .content(String.format("Usunięto oczyt o id: %s", id))
                        .timestamp(LocalDateTime.now())
                        .caller(caller)
                        .build()
        );
    }

    //region UTILITIES

    /**
     *   Sprawdz czy podany identyfikator istnieje w bazie, w przypadku gdy nie, rzucany jest wyjątek
     *   @param id    Identyfikator urządzenia
     **/
    public void doesReadingNotExist(UUID id){
        if(!repository.existsById(id)) throw new ReadingNotFoundException();
    }

    //endregion
}
