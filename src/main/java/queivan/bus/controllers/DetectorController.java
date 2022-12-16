package queivan.bus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import queivan.bus.domains.detector.DetectorDto;
import queivan.bus.facade.DetectorFacade;

import java.util.List;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/detectors")
@RequiredArgsConstructor
public class DetectorController {
    private final DetectorFacade facade;

    //region GETTERS

    /**
     *   Pobierz wszystkie czujniki z bazy
     *   @param caller Identyfikator wykonującego polecenie
     *   @return       Lista obiektów z danymy czujników
     **/
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<DetectorDto> getAllDetectors(@RequestHeader("X-Identifier") String caller) {
        return facade.getAllDetectors(caller);
    }

    /**
     *   Pobierz dane czujnika używając adresu MAC
     *   @param MAC    Adres MAC urządzenia
     *   @param caller Identyfikator wykonującego polecenie
     *   @return       Obiekt czujnika z bazy
     **/
    @GetMapping(value = "/{MAC}", produces = APPLICATION_JSON_VALUE)
    public DetectorDto getDetectorByMAC(@PathVariable String MAC, @RequestHeader("X-Identifier") String caller) {
        return facade.getDetectorByMAC(MAC, caller);
    }

    /**
     *   Pobierz dane czujnika używając id
     *   @param id     Identyfikator urządzenia
     *   @param caller Identyfikator wykonującego polecenie
     *   @return       Obiekt czujnika z bazy
     **/
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public DetectorDto getDetectorByID(@PathVariable UUID id, @RequestHeader("X-Identifier") String caller) {
        return facade.getDetectorByID(id, caller);
    }

    //endregion

    //region CREATE && UPDATE

    /**
     *   Stwórz nowy wpis w bazie na temat czujnika
     *   @param dto    Obiekt JSON czujnika
     *   @param caller Identyfikator wykonującego polecenie
     *   @return       Sworzony obiekt czujnika
     **/
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public DetectorDto createDetector(@RequestBody DetectorDto dto, @RequestHeader("X-Identifier") String caller) {
        return facade.createDetector(dto, caller);
    }

    /**
     *   Zaktualizuj wpis w bazie na temat czujnika
     *   @param dto    Obiekt JSON czujnika
     *   @param caller Identyfikator wykonującego polecenie
     *   @return       Sworzony obiekt czujnika
     **/
    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public DetectorDto updateDetector(@RequestBody DetectorDto dto, @RequestHeader("X-Identifier") String caller) {
        return facade.updateDetector(dto, caller);
    }

    //endregion

    //region DELETE

    /**
     *   Usuń wpis czujnika o podanym id
     *   @param id    Identyfikator urządzenia
     *   @param caller Identyfikator wykonującego polecenie
     **/
    @DeleteMapping(value = "/{id}")
    public void deleteDetector(@PathVariable UUID id, @RequestHeader("X-Identifier") String caller) {
        facade.deleteDetector(id, caller);
    }

    //endregion
}
