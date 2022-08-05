package dh.meli.projeto_integrador.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntryDto {
    @Valid
    private Set<BatchDto> batches;

    @Valid
    private SectionDto section;

    @NotNull
    private long agentId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate orderDate;
}
