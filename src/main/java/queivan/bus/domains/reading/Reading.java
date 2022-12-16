package queivan.bus.domains.reading;

import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import queivan.bus.domains.detector.Detector;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 <p>Podstawowa struktura JSON payload</p>
 <p>{</p>
 <p>&nbsp;&nbsp;&nbsp;&nbsp;status: "on" | "waiting" | "off"</p>
 <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(on - odczyt jest wykonany poprawnie,</p>
 <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;waiting - urządenie jest aktywne, ale nie skofigurowane,</p>
 <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;off - urządzenie jest wyłączone Z ODCZYTU)</p>
 <p>&nbsp;&nbsp;&nbsp;&nbsp;value: int | float (w zależności od status - wartość | null</p>
 <p>}</p>
**/
@Entity(name = "readings")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private String payload;
    private LocalDateTime timestamp;
    @ManyToOne(fetch = FetchType.LAZY)
    private Detector detector;
}
