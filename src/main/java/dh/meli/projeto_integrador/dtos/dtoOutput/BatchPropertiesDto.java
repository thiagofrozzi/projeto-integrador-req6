package dh.meli.projeto_integrador.dtos.dtoOutput;

import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.model.Product;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BatchPropertiesDto {
    private Long warehouseCode;
    private String section;
    private String name;
    private Long id;
    private Set<BatchDto> batchStock;

    public BatchPropertiesDto(Product product, Set<Batch> batchStock) {
        this.name = product.getName();
        this.id = product.getId();
        this.section = product.getType();
        batchStock.forEach(batch -> this.batchStock.add((new BatchDto(batch))));
    }
}
