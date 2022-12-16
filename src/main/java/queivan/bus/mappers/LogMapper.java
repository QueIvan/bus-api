package queivan.bus.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import queivan.bus.domains.log.Log;
import queivan.bus.domains.log.LogDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogMapper {
    @Mapping(target = "id", ignore = true)
    Log mapLogDtoToLog(LogDto dto);

    LogDto mapLogToLogDto(Log mapped);

    List<LogDto> mapLogListToLogDtoList(List<Log> fetched);
}
