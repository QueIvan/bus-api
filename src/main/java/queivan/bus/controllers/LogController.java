package queivan.bus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import queivan.bus.domains.log.LogDto;
import queivan.bus.facade.LogFacade;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogController {
    private final LogFacade facade;

    /**
     *   Pobierz wszystkie wpisy z dziennika zdarzeń
     *   @param caller  Identyfikator wykonującego polecenie
     *   @return        Zapisany obiekt wpisu
     **/
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<LogDto> getAllLogs(String caller) {
        return facade.getAllLogs(caller);
    }

    /**
     *   Stwórz nowy wpis do dziennika zdarzeń
     *   @param dto    Obiekt wpisu
     *   @param caller  Identyfikator wykonującego polecenie
     *   @return       Zapisany obiekt wpisu
     **/
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public LogDto createLog(LogDto dto, String caller) {
        return facade.createLog(dto, caller);
    }
}
