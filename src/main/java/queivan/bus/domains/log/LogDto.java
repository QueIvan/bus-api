package queivan.bus.domains.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LogDto {
    private String type;
    private String content;
    private String caller;
    private LocalDateTime timestamp;
}
