package queivan.bus.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import queivan.bus.domains.vehicle.VehicleDto;
import queivan.bus.services.VehicleService;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VehicleFacade {
    private final VehicleService service;

    public List<VehicleDto> getAllVehicles(String caller) {
        return service.getAllVehicles(caller);
    }

    public VehicleDto getVehicleByPlate(String plate, String caller) {
        return service.getVehicleByPlate(plate, caller);
    }

    public VehicleDto getVehicleByID(UUID id, String caller) {
        return service.getVehicleByID(id, caller);
    }

    public VehicleDto createVehicle(VehicleDto dto, String caller) {
        return service.createVehicle(dto, caller);
    }

    public VehicleDto updateVehicle(VehicleDto dto, String caller) {
        return service.updateVehicle(dto, caller);
    }

    public void deleteVehicle(UUID id, String caller) {
        service.deleteVehicle(id, caller);
    }
}
