package queivan.bus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import queivan.bus.domains.reading.ReadingDto;
import queivan.bus.facade.ReadingFacade;

import java.util.List;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/readings")
@RequiredArgsConstructor
public class ReadingController {
    private final ReadingFacade facade;

    //region GETTERS

    /**
     *   Pobierz wszystkie oczyty z bazy
     *   @param caller Identyfikator wykonującego polecenie
     *   @return       Lista obiektów z danymy oczytów
     **/
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<ReadingDto> getAllReadings(@RequestHeader("X-Identifier") String caller) {
        return facade.getAllReadings(caller);
    }

    /**
     *   Pobierz dane oczytu używając id
     *   @param id     Identyfikator oczytu
     *   @param caller Identyfikator wykonującego polecenie
     *   @return       Obiekt oczytu z bazy
     **/
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ReadingDto getReadingByID(@PathVariable UUID id, @RequestHeader("X-Identifier") String caller) {
        return facade.getReadingByID(id, caller);
    }

    //endregion

    //region CREATE && UPDATE

    /**
     *   Stwórz nowy wpis w bazie na temat oczytu
     *   @param dto    Obiekt JSON oczytu
     *   @param caller Identyfikator wykonującego polecenie
     *   @return       Sworzony obiekt oczytu
     **/
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ReadingDto createReading(@RequestBody ReadingDto dto, @RequestHeader("X-Identifier") String caller) {
        return facade.createReading(dto, caller);
    }

    //endregion

    //region DELETE

    /**
     *   Usuń wpis oczytu o podanym id
     *   @param id    Identyfikator urządzenia
     *   @param caller Identyfikator wykonującego polecenie
     **/
    @DeleteMapping(value = "/{id}")
    public void deleteReading(@PathVariable UUID id, @RequestHeader("X-Identifier") String caller) {
        facade.deleteReading(id, caller);
    }

    //endregion
}
