package queivan.bus.domains.reading;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReadingDetectorDto {
    private UUID id;
    private String payload;
    private LocalDateTime timestamp;
}
