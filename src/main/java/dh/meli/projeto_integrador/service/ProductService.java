package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dtos.dtoOutput.BatchDto;
import dh.meli.projeto_integrador.dtos.dtoOutput.BatchPropertiesDto;
import dh.meli.projeto_integrador.exception.NotFoundEx;
import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.model.Product;
import dh.meli.projeto_integrador.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public BatchPropertiesDto getProductBatchProps(Long id) {
        Product product = productRepository.findById(id).get();
        Set<Batch> batches = product.getBatches();

        if (batches.isEmpty()) {
            throw new NotFoundEx("No available batch found for this product.");
        }

        BatchPropertiesDto batchPropertiesDto = new BatchPropertiesDto(product, batches);
        return batchPropertiesDto;
    }

    @Override
    public Set<BatchPropertiesDto> getOrderedProductBatchProps(Long id, Character c) {
        Set<BatchPropertiesDto> initialBatchPropertiesDto = null;
        initialBatchPropertiesDto.add(getProductBatchProps(id));

        Set<BatchPropertiesDto> orderedBatchPropertiesDto = null;


        return orderedBatchPropertiesDto;
    }

    private static Set<BatchDto> sortByOrder(Set<BatchDto> batchList, Character order) {
        switch (order) {
            case 'L':
                return batchList.stream()
                        .sorted(Comparator.comparing(BatchDto::getBatchNumber))
                        .collect(Collectors.toSet());
            case 'Q':
                return batchList.stream()
                        .sorted(Comparator.comparing(BatchDto::getCurrentQuantity))
                        .collect(Collectors.toSet());
            case 'V':
                return batchList.stream()
                        .sorted(Comparator.comparing(BatchDto::getDueDate))
                        .collect(Collectors.toSet());
            default:
                return batchList;
        }
    }

}