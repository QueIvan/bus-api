package queivan.bus.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import queivan.bus.domains.vehicle.Vehicle;
import queivan.bus.domains.vehicle.VehicleDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VehicleMapper {
    List<VehicleDto> mapVehicleListToVehicleDtoList(List<Vehicle> vehicleList);

    VehicleDto mapVehicleToVehicleDto(Vehicle fetched);

    Vehicle mapVehicleDtoToVehicle(VehicleDto dto);
}
