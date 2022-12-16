package queivan.bus.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import queivan.bus.domains.detector.Detector;
import queivan.bus.domains.detector.DetectorDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DetectorMapper {
    DetectorDto mapDetectorToDetectorDto(Detector detector);

    List<DetectorDto> mapDetectorListToDetectorDtoList(List<Detector> detectorList);

    @Mapping(target = "id", ignore = true)
    Detector mapDetectorDtoToDetector(DetectorDto detectorDto);
}
