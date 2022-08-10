package dh.meli.projeto_integrador.dto.dtoOutput;

import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.model.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductStockDto {
    private String section;
    private String name;
    private Long id;
    private Set<BatchDto> batchStockDto;

    public ProductStockDto(Product product, Set<Batch> batches) {
        this.name = product.getName();
        this.id = product.getId();
        this.section = product.getType();

        List<Batch> batchList = new ArrayList<>(batches);
        Set<BatchDto> batchDtos = new HashSet<>();
        BatchDto batchDto;

        for (Batch b:batchList) {
            long idWarehouse = (b.getOrderEntry().getSection().getWarehouse().getId());
            batchDto = new BatchDto(idWarehouse,b.getId(), b.getCurrentQuantity(), b.getDueDate());
            batchDtos.add(batchDto);
        }
        this.batchStockDto = batchDtos;
    }
}

