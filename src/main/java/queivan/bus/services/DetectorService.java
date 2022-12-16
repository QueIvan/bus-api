package queivan.bus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import queivan.bus.domains.detector.Detector;
import queivan.bus.domains.detector.DetectorDto;
import queivan.bus.domains.log.LogDto;
import queivan.bus.domains.vehicle.Vehicle;
import queivan.bus.exceptions.DetectorAlreadyExists;
import queivan.bus.exceptions.DetectorNotFoundException;
import queivan.bus.exceptions.VehicleNotFoundException;
import queivan.bus.mappers.DetectorMapper;
import queivan.bus.services.repositories.DetectorRepository;
import queivan.bus.services.repositories.VehicleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DetectorService {
    private final DetectorRepository repository;
    private final VehicleRepository vehicleRepository;
    private final DetectorMapper mapper;
    private final LogService logger;

    public List<DetectorDto> getAllDetectors(String caller) {
        List<Detector> feteched = repository.findAll();
        logger.info(
                LogDto.builder()
                        .content("Zwrócono wszystkie czujniki")
                        .timestamp(LocalDateTime.now())
                        .caller(caller)
                        .build()
        );
        return mapper.mapDetectorListToDetectorDtoList(feteched);
    }

    public DetectorDto getDetectorByMAC(String MAC, String caller) {
        Detector fetched = repository.findByMAC(MAC).orElseThrow(DetectorNotFoundException::new);
        logger.info(
                LogDto.builder()
                        .content(String.format("Zwrócono czujnik o adresie MAC: %s", MAC))
                        .timestamp(LocalDateTime.now())
                        .caller(caller)
                        .build()
        );
        return mapper.mapDetectorToDetectorDto(fetched);
    }

    public DetectorDto getDetectorByID(UUID id, String caller) {
        Detector fetched = repository.findById(id).orElseThrow(DetectorNotFoundException::new);
        logger.info(
                LogDto.builder()
                        .content(String.format("Zwrócono czujnik o id: %s", id))
                        .timestamp(LocalDateTime.now())
                        .caller(caller)
                        .build()
        );
        return mapper.mapDetectorToDetectorDto(fetched);
    }

    public DetectorDto createDetector(DetectorDto dto, String caller) {
        doesDetectorExist(dto.getMAC());
        Detector mapped = mapper.mapDetectorDtoToDetector(dto);
        Detector saved = repository.save(mapped);
        logger.info(
                LogDto.builder()
                        .content(String.format("Wpisano nowy czujnik o id: %s", saved.getId()))
                        .timestamp(LocalDateTime.now())
                        .caller(caller)
                        .build()
        );
        return mapper.mapDetectorToDetectorDto(saved);
    }

    public DetectorDto updateDetector(DetectorDto dto, String caller) {
        doesDetectorNotExistById(dto.getId());
        Detector fetched = repository.findById(dto.getId()).orElseThrow(DetectorNotFoundException::new);
        fetched.setMAC(dto.getMAC());
        fetched.setType(dto.getType());
        if(dto.getVehicle() != null){
            Vehicle v = vehicleRepository.findById(dto.getVehicle().getId()).orElseThrow(VehicleNotFoundException::new);
            fetched.setVehicle(v);
        }
        Detector saved = repository.save(fetched);
        logger.info(
                LogDto.builder()
                        .content(String.format("Zaktualizowano czujnik o id: %s", dto.getId()))
                        .timestamp(LocalDateTime.now())
                        .caller(caller)
                        .build()
        );
        return mapper.mapDetectorToDetectorDto(saved);
    }

    public void deleteDetector(UUID id, String caller) {
        doesDetectorNotExistById(id);
        repository.deleteById(id);
        logger.info(
                LogDto.builder()
                        .content(String.format("Usunięto czujnik o id: %s", id))
                        .timestamp(LocalDateTime.now())
                        .caller(caller)
                        .build()
        );
    }

    //region UTILITIES

    /**
     *   Sprawdz czy podany adres MAC istnieje w bazie, w przypadku gdy tak, rzucany jest wyjątek
     *   @param MAC    Adres MAC urządzenia
     **/
    public void doesDetectorExist(String MAC){
        if(repository.existsByMAC(MAC)) throw new DetectorAlreadyExists();
    }

    /**
     *   Sprawdz czy podany identyfikator istnieje w bazie, w przypadku gdy nie, rzucany jest wyjątek
     *   @param id    Identyfikator urządzenia
     **/
    public void doesDetectorNotExistById(UUID id){
        if(!repository.existsById(id)) throw new DetectorNotFoundException();
    }

    //endregion
}
