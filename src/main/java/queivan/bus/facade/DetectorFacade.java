package queivan.bus.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.bus.domains.detector.DetectorDto;
import queivan.bus.services.DetectorService;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DetectorFacade {
    private final DetectorService service;

    public List<DetectorDto> getAllDetectors(String caller) {
        return service.getAllDetectors(caller);
    }

    public DetectorDto getDetectorByMAC(String MAC, String caller) {
        return service.getDetectorByMAC(MAC, caller);
    }

    public DetectorDto getDetectorByID(UUID id, String caller) {
        return service.getDetectorByID(id, caller);
    }

    public DetectorDto createDetector(DetectorDto dto, String caller) {
        return service.createDetector(dto, caller);
    }

    public DetectorDto updateDetector(DetectorDto dto, String caller) {
        return service.updateDetector(dto, caller);
    }

    public void deleteDetector(UUID id, String caller) {
        service.deleteDetector(id, caller);
    }

}
