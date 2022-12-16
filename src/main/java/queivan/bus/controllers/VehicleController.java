package queivan.bus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import queivan.bus.domains.vehicle.VehicleDto;
import queivan.bus.facade.VehicleFacade;

import java.util.List;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleFacade facade;

    //region GETTERS

    /**
     *   Pobierz wszystkie pojazdy z bazy
     *   @param caller Identyfikator wykonującego polecenie
     *   @return       Lista obiektów z danymy pojazdów
     **/
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<VehicleDto> getAllVehicles(@RequestHeader("X-Identifier") String caller) {
        return facade.getAllVehicles(caller);
    }

    /**
     *   Pobierz dane pojazdu używając tablicy rejestracyjnej
     *   @param plate  Tablica rejestracyjna pojazdu
     *   @param caller Identyfikator wykonującego polecenie
     *   @return       Obiekt pojazdu z bazy
     **/
    @GetMapping(value = "/{plate}", produces = APPLICATION_JSON_VALUE)
    public VehicleDto getVehicleByPlate(@PathVariable String plate, @RequestHeader("X-Identifier") String caller) {
        return facade.getVehicleByPlate(plate, caller);
    }

    /**
     *   Pobierz dane pojazdu używając id
     *   @param id     Identyfikator pojazdu
     *   @param caller Identyfikator wykonującego polecenie
     *   @return       Obiekt pojazdu z bazy
     **/
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public VehicleDto getVehicleByID(@PathVariable UUID id, @RequestHeader("X-Identifier") String caller) {
        return facade.getVehicleByID(id, caller);
    }

    //endregion

    //region CREATE && UPDATE

    /**
     *   Stwórz nowy wpis w bazie na temat pojazdu
     *   @param dto    Obiekt JSON pojazdu
     *   @param caller Identyfikator wykonującego polecenie
     *   @return       Sworzony obiekt pojazdu
     **/
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public VehicleDto createVehicle(@RequestBody VehicleDto dto, @RequestHeader("X-Identifier") String caller) {
        return facade.createVehicle(dto, caller);
    }

    /**
     *   Zaktualizuj wpis w bazie na temat pojazdu
     *   @param dto    Obiekt JSON pojazdu
     *   @param caller Identyfikator wykonującego polecenie
     *   @return       Sworzony obiekt pojazdu
     **/
    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public VehicleDto updateVehicle(@RequestBody VehicleDto dto, @RequestHeader("X-Identifier") String caller) {
        return facade.updateVehicle(dto, caller);
    }

    //endregion

    //region DELETE

    /**
     *   Usuń wpis pojazdu o podanym id
     *   @param id    Identyfikator urządzenia
     *   @param caller Identyfikator wykonującego polecenie
     **/
    @DeleteMapping(value = "/{id}")
    public void deleteVehicle(@PathVariable UUID id, @RequestHeader("X-Identifier") String caller) {
        facade.deleteVehicle(id, caller);
    }

    //endregion

}
