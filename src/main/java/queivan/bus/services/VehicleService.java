package queivan.bus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import queivan.bus.domains.detector.Detector;
import queivan.bus.domains.detector.DetectorVehicleDto;
import queivan.bus.domains.log.LogDto;
import queivan.bus.domains.vehicle.Vehicle;
import queivan.bus.domains.vehicle.VehicleDto;
import queivan.bus.exceptions.DetectorNotFoundException;
import queivan.bus.exceptions.VehicleAlreadyExists;
import queivan.bus.exceptions.VehicleNotFoundException;
import queivan.bus.mappers.VehicleMapper;
import queivan.bus.services.repositories.DetectorRepository;
import queivan.bus.services.repositories.VehicleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository repository;
    private final DetectorRepository detectorRepository;
    private final VehicleMapper mapper;
    private final LogService logger;

    public List<VehicleDto> getAllVehicles(String caller) {
        List<Vehicle> feteched = repository.findAll();
        logger.info(
                LogDto.builder()
                        .content("Zwrócono wszystkie pojazdy")
                        .timestamp(LocalDateTime.now())
                        .caller(caller)
                        .build()
        );
        return mapper.mapVehicleListToVehicleDtoList(feteched);
    }

    public VehicleDto getVehicleByPlate(String plate, String caller) {
        Vehicle fetched = repository.findByPlate(plate).orElseThrow(VehicleNotFoundException::new);
        logger.info(
                LogDto.builder()
                        .content(String.format("Zwrócono pojazd z rejestracją: %s", plate))
                        .timestamp(LocalDateTime.now())
                        .caller(caller)
                        .build()
        );
        return mapper.mapVehicleToVehicleDto(fetched);
    }

    public VehicleDto getVehicleByID(UUID id, String caller) {
        Vehicle fetched = repository.findById(id).orElseThrow(VehicleNotFoundException::new);
        logger.info(
                LogDto.builder()
                        .content(String.format("Zwrócono pojazd o id: %s", id))
                        .timestamp(LocalDateTime.now())
                        .caller(caller)
                        .build()
        );
        return mapper.mapVehicleToVehicleDto(fetched);
    }

    public VehicleDto createVehicle(VehicleDto dto, String caller) {
        doesVehicleExist(dto.getPlate());
        Vehicle mapped = mapper.mapVehicleDtoToVehicle(dto);
        Vehicle saved = repository.save(mapped);
        logger.info(
                LogDto.builder()
                        .content(String.format("Wpisano nowy pojazd o id: %s", saved.getId()))
                        .timestamp(LocalDateTime.now())
                        .caller(caller)
                        .build()
        );
        return mapper.mapVehicleToVehicleDto(saved);
    }

    @Transactional
    public VehicleDto updateVehicle(VehicleDto dto, String caller) {
        doesVehicleNotExistById(dto.getId());
        Vehicle fetched = repository.findByIdAndPlate(dto.getId(), dto.getPlate()).orElseThrow(VehicleNotFoundException::new);
        fetched.setPlate(dto.getPlate());
        if(dto.getDetectors().size() > 0){
            for (DetectorVehicleDto ddto:dto.getDetectors()) {
                Detector d = detectorRepository.findById(ddto.getId()).orElseThrow(DetectorNotFoundException::new);
                d.setVehicle(fetched);
                detectorRepository.save(d);
            }
        }
        Vehicle saved = repository.save(fetched);
        logger.info(
                LogDto.builder()
                        .content(String.format("Zaktualizowano pojazd o id: %s", saved.getId()))
                        .timestamp(LocalDateTime.now())
                        .caller(caller)
                        .build()
        );
        return mapper.mapVehicleToVehicleDto(saved);
    }

    public void deleteVehicle(UUID id, String caller) {
        doesVehicleNotExistById(id);
        repository.deleteById(id);
        logger.info(
                LogDto.builder()
                        .content(String.format("Usunięto pojazd o id: %s", id))
                        .timestamp(LocalDateTime.now())
                        .caller(caller)
                        .build()
        );
    }

    //region UTILITIES

    /**
     *   Sprawdz czy podany tablica rejestracyjna istnieje w bazie, w przypadku gdy tak, rzucany jest wyjątek
     *   @param plate    Adres MAC urządzenia
     **/
    public void doesVehicleExist(String plate){
        if(repository.existsByPlate(plate)) throw new VehicleAlreadyExists();
    }

    /**
     *   Sprawdz czy podany identyfikator istnieje w bazie, w przypadku gdy nie, rzucany jest wyjątek
     *   @param id    Identyfikator urządzenia
     **/
    public void doesVehicleNotExistById(UUID id){
        if(!repository.existsById(id)) throw new VehicleNotFoundException();
    }

    //endregion
}
