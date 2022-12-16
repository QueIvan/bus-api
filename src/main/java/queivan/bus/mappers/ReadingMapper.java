package queivan.bus.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import queivan.bus.domains.reading.Reading;
import queivan.bus.domains.reading.ReadingDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReadingMapper {
    List<ReadingDto> mapReadingListToReadingDtoList(List<Reading> feteched);

    ReadingDto mapReadingToReadingDto(Reading fetched);

    Reading mapReadingDtoToReading(ReadingDto dto);
}
