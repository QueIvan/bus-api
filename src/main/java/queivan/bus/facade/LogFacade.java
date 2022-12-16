package queivan.bus.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.bus.domains.log.LogDto;
import queivan.bus.services.LogService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LogFacade {
    private final LogService service;

    public List<LogDto> getAllLogs(String caller) {
        return service.getAllLogs(caller);
    }

    public LogDto createLog(LogDto dto, String caller) {
        return service.createLog(dto, caller);
    }
}
