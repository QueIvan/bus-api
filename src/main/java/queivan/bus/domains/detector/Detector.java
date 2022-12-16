package queivan.bus.domains.detector;

import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import queivan.bus.domains.reading.Reading;
import queivan.bus.domains.vehicle.Vehicle;

import java.util.List;
import java.util.UUID;

@Entity(name = "detectors")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Detector {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private String MAC;
    private String type;
    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;
    @OneToMany(mappedBy = "detector", cascade = CascadeType.ALL)
    private List<Reading> readings;
}
