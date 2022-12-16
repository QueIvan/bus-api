package queivan.bus.domains.detector;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import queivan.bus.domains.reading.ReadingDetectorDto;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DetectorVehicleDto {
    private UUID id;
    private String MAC;
    private String type;
    private List<ReadingDetectorDto> readings;
}
