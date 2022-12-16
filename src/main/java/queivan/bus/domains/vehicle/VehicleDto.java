package queivan.bus.domains.vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import queivan.bus.domains.detector.DetectorVehicleDto;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VehicleDto {
    private UUID id;
    private String plate;
    private List<DetectorVehicleDto> detectors;
}
