package queivan.bus.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import queivan.bus.domains.log.Log;
import queivan.bus.domains.log.LogDto;
import queivan.bus.mappers.LogMapper;
import queivan.bus.services.repositories.LogRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogService {
    private final LogRepository repository;
    private final LogMapper mapper;

    public List<LogDto> getAllLogs(String caller){
        List<Log> fetched = repository.findAll();
        info(
                LogDto.builder()
                        .content("Pobrano wszystkie wpisy z dziennika")
                        .timestamp(LocalDateTime.now())
                        .caller(caller)
                        .build()
        );
        return mapper.mapLogListToLogDtoList(fetched);
    }

    /**
     * Stwórz nowy wpis do dziennika zdarzeń
     *
     * @param dto Obiekt wpisu
     **/
    public void createLog(LogDto dto){
        Log mapped = mapper.mapLogDtoToLog(dto);
        Log saved = repository.save(mapped);
        mapper.mapLogToLogDto(saved);
    }

    public LogDto createLog(LogDto dto, String caller){
        dto.setCaller(caller);
        Log mapped = mapper.mapLogDtoToLog(dto);
        Log saved = repository.save(mapped);
        return mapper.mapLogToLogDto(saved);
    }

    /**
     *   Stwórz nowy wpis informacyjny do dziennika zdarzeń
     *   @param dto    Obiekt wpisu
     **/
    public void info(LogDto dto){
        dto.setType("INFO");
        log.info(String.format("%s (caller: %s)", dto.getContent(), dto.getCaller()));
        createLog(dto);
    }

    /**
     *   Stwórz nowy wpis błedu do dziennika zdarzeń
     *   @param dto    Obiekt wpisu
     **/
    public void error(LogDto dto){
        dto.setType("ERROR");
        log.info(String.format("%s (caller: %s)", dto.getContent(), dto.getCaller()));
        createLog(dto);
    }

}
