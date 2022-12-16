package queivan.bus.domains.reading;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import queivan.bus.domains.detector.DetectorReadingDto;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReadingDto {
    private UUID id;
    private String payload;
    private LocalDateTime timestamp;
    private DetectorReadingDto detector;
}
