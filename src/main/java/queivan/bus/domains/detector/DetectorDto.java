package queivan.bus.domains.detector;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import queivan.bus.domains.reading.ReadingDetectorDto;
import queivan.bus.domains.vehicle.VehicleDetectorDto;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DetectorDto {
    private UUID id;
    private String MAC;
    private String type;
    private VehicleDetectorDto vehicle;
    private List<ReadingDetectorDto> readings;
}
