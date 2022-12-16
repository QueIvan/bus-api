package queivan.bus.domains.vehicle;

import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import queivan.bus.domains.detector.Detector;

import java.util.List;
import java.util.UUID;

@Entity(name = "vehicles")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private String plate;
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Detector> detectors;
}
