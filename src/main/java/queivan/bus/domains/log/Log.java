package queivan.bus.domains.log;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "logs")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private String type;
    @NotNull
    private String content;
    private String caller;
    @NotNull
    private LocalDateTime timestamp;
}
